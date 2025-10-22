package dev2426.itsprojectwork.services;


import dev2426.itsprojectwork.models.Utente;
import dev2426.itsprojectwork.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.util.Objects;

@Service
public class AvatarFsService {

    private static final long MAX_SIZE = 2L * 1024 * 1024; // 2MB

    @Value("${app.upload.base-dir}")
    private String baseDir;

    private final UtenteRepository utenteRepository;

    public AvatarFsService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public void uploadAvatar(Long utenteId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Nessun file caricato.");
        }
        if (file.getSize() > MAX_SIZE) {
            throw new IllegalArgumentException("File troppo grande (max 2MB).");
        }

        String ct = Objects.toString(file.getContentType(), "");
        // Supportiamo solo PNG/JPEG (WEBP richiede plugin)
        boolean ok = ct.equalsIgnoreCase("image/jpeg") || ct.equalsIgnoreCase("image/png");
        if (!ok) throw new IllegalArgumentException("Formato non consentito (usa JPG o PNG).");

        // Percorso sicuro: <baseDir>/avatars/<id>/avatar.jpg
        Path userDir = Paths.get(baseDir, "avatars", String.valueOf(utenteId)).normalize().toAbsolutePath();
        Path base = Paths.get(baseDir).normalize().toAbsolutePath();
        if (!userDir.startsWith(base)) throw new SecurityException("Percorso non valido.");
        try {
            Files.createDirectories(userDir);
        } catch (IOException e) {
            throw new RuntimeException("Impossibile creare la cartella utente.", e);
        }

        Path target = userDir.resolve("avatar.jpg");

        // Leggi immagine e converti a JPEG (ridimensionamento facoltativo)
        try (InputStream in = file.getInputStream()) {
            BufferedImage src = ImageIO.read(in);
            if (src == null) throw new IllegalArgumentException("File non è un'immagine valida.");

            // Opzionale: ridimensiona per limite max (es. 512px lato lungo)
            int w = src.getWidth(), h = src.getHeight();
            int max = 512;
            double scale = Math.min(1.0, (double) max / Math.max(w, h));
            int tw = (int) Math.round(w * scale);
            int th = (int) Math.round(h * scale);

            BufferedImage out = new BufferedImage(tw, th, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = out.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE); // background per PNG con trasparenza
            g.fillRect(0, 0, tw, th);
            g.drawImage(src, 0, 0, tw, th, null);
            g.dispose();

            try (OutputStream os = Files.newOutputStream(target, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                ImageIO.write(out, "jpg", os);
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore nel salvataggio dell'immagine.", e);
        }

        // Aggiorna path in DB
        Utente u = utenteRepository.findById(utenteId).orElseThrow();
        u.setImmagine("/media/avatars/" + utenteId + "/avatar.jpg");
        utenteRepository.save(u);
    }

    public void deleteAvatar(Long utenteId) {
        Utente u = utenteRepository.findById(utenteId).orElseThrow();
        if (u.getImmagine() != null && !u.getImmagine().isBlank()) {
            String relative = u.getImmagine().replaceFirst("^/media/", ""); // avatars/<id>/avatar.jpg
            Path file = Paths.get(baseDir, relative).normalize().toAbsolutePath();
            try { if (Files.exists(file)) Files.delete(file); } catch (IOException ignored) {}
        }
        u.setImmagine(null);
        utenteRepository.save(u);
    }
}

