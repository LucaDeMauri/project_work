package dev2426.itsprojectwork.mapper;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dev2426.itsprojectwork.dto.AnnuncioDTO;
import dev2426.itsprojectwork.dto.AziendaDTO;
import dev2426.itsprojectwork.dto.CandidaturaDTO;
import dev2426.itsprojectwork.dto.UtenteDTO;
import dev2426.itsprojectwork.models.Annuncio;
import dev2426.itsprojectwork.models.Azienda;
import dev2426.itsprojectwork.models.Candidatura;
import dev2426.itsprojectwork.models.Competenza;
import dev2426.itsprojectwork.models.Utente;

/**
 * Mapper centralizzato DTO <-> Entity.
 * - Null-safe (ritorna null se input null).
 * - Evita ricorsioni su Azienda <-> Annuncio (flag withAnnunci).
 * - Rispetta gli stessi tipi che hai definito nei DTO.
 */
public final class DtoMapper {

    private DtoMapper() {}

    /* =========================================================
     * AZIENDA
     * ========================================================= */

    public static AziendaDTO toAziendaDTO(Azienda az) {
        if (az == null) return null;
        AziendaDTO dto = new AziendaDTO();
        dto.setNome(az.getNome());
        // Per evitare ricorsioni NON mappo la lista annunci qui.
        // Se mai ti serve: usa toAziendaDTO(az, true)
        return dto;
    }

    /** Versione con controllo opzionale degli annunci (ATTENZIONE a possibili ricorsioni) */
    public static AziendaDTO toAziendaDTO(Azienda az, boolean withAnnunci) {
        if (az == null) return null;
        AziendaDTO dto = new AziendaDTO();
        dto.setNome(az.getNome());
        if (withAnnunci && az.getAnnunci() != null) {
            List<AnnuncioDTO> annDTO = new ArrayList<>();
            for (Annuncio a : az.getAnnunci()) {
                // Qui NON rimappo az->dto con annunci per evitare loop,
                // quindi uso la versione base di toAnnuncioDTO (che mappa solo azienda "base")
                annDTO.add(toAnnuncioDTO(a, /*withAzienda*/ false));
            }
            dto.setAnnunci(annDTO);
        }
        return dto;
    }

    public static Azienda toAziendaEntity(AziendaDTO dto) {
        if (dto == null) return null;
        Azienda az = new Azienda();
        // ATTENZIONE: AziendaDTO non ha id. Se dovessi aggiornare, setta altrove az.setId(...)
        az.setNome(dto.getNome());
        // Non setto annunci → li governi altrove per evitare ricorsioni
        return az;
    }

    /* =========================================================
     * ANNUNCIO
     * ========================================================= */

    /** Mappa Annuncio -> DTO, includendo azienda "base" (senza annunci per evitare loop). */
    public static AnnuncioDTO toAnnuncioDTO(Annuncio a) {
        return toAnnuncioDTO(a, /*withAzienda*/ true);
    }

    /** Mappa Annuncio -> DTO, opzionalmente includendo azienda. */
    public static AnnuncioDTO toAnnuncioDTO(Annuncio a, boolean withAzienda) {
        if (a == null) return null;

        AnnuncioDTO dto = new AnnuncioDTO();
        dto.setId(a.getId());
        dto.setTitolo(a.getTitolo());
        dto.setTipoMansione(a.getTipoMansione());
        dto.setLocation(a.getLocation());
        dto.setDescrizione(a.getDescrizione());
        dto.setOrari(a.getOrari());
        // camelCase -> snakeCase nei tuoi DTO
        dto.setData_inizio(a.getDataInizio());
        dto.setData_fine(a.getDataFine());

        if (withAzienda) {
            dto.setAzienda(toAziendaDTO(a.getAzienda()));
        }

        // I tuoi DTO usano Set<Competenza> entity -> pass-through/copio la collezione
        dto.setCompetenzeRichieste(copyCompetenzaSet(a.getCompetenzeRichieste()));
        dto.setCompetenzeAcquisite(copyCompetenzaSet(a.getCompetenzeAcquisite()));
        return dto;
    }

    /** Mappa DTO -> Annuncio (non tocca l'id; non setta isActive se non vuoi). */
    public static Annuncio toAnnuncioEntity(AnnuncioDTO dto) {
        if (dto == null) return null;

        Annuncio a = new Annuncio();
        a.setId(dto.getId()); // se null, lo genera il DB
        a.setTitolo(dto.getTitolo());
        a.setTipoMansione(dto.getTipoMansione());
        a.setLocation(dto.getLocation());
        a.setDescrizione(dto.getDescrizione());
        a.setOrari(dto.getOrari());
        a.setDataInizio(dto.getData_inizio());
        a.setDataFine(dto.getData_fine());

        // Se vuoi forzare default:
        // a.setActive(true);

        // AziendaDTO -> Azienda (senza id, perché DTO non ce l’ha)
        a.setAzienda(toAziendaEntity(dto.getAzienda()));

        // Set<Competenza> pass-through/copied
        a.setCompetenzeRichieste(copyCompetenzaSet(dto.getCompetenzeRichieste()));
        a.setCompetenzeAcquisite(copyCompetenzaSet(dto.getCompetenzeAcquisite()));
        return a;
    }

    /* =========================================================
     * CANDIDATURA
     * ========================================================= */

    public static CandidaturaDTO toCandidaturaDTO(Candidatura c) {
        if (c == null) return null;
        CandidaturaDTO dto = new CandidaturaDTO();
        dto.setId(c.getId());
        dto.setStato(c.getStato());
        dto.setAnnuncio(toAnnuncioDTO(c.getAnnuncio(), /*withAzienda*/ true));
        dto.setUtente(toUtenteDTO(c.getUtente()));
        return dto;
    }

    public static Candidatura toCandidaturaEntity(CandidaturaDTO dto) {
        if (dto == null) return null;
        Candidatura c = new Candidatura();
        c.setId(dto.getId());
        c.setStato(dto.getStato());
        c.setAnnuncio(toAnnuncioEntity(dto.getAnnuncio()));
        c.setUtente(toUtenteEntity(dto.getUtente()));
        // dataCandidatura e is_active li setti dove crei la candidatura (service)
        return c;
    }

    /* =========================================================
     * UTENTE
     * ========================================================= */

    public static UtenteDTO toUtenteDTO(Utente u) {
        if (u == null) return null;
        UtenteDTO dto = new UtenteDTO();
        dto.setId(u.getId());
        dto.setImmagine(u.getImmagine()); // se ce l’hai nel model
        dto.setBio(u.getBio());
        dto.setNome(u.getNome());
        dto.setCognome(u.getCognome());
        dto.setEmail(u.getEmail());
        dto.setRuolo(u.getRuolo());
        return dto;
    }

    public static Utente toUtenteEntity(UtenteDTO dto) {
        if (dto == null) return null;
        Utente u = new Utente();
        u.setId(dto.getId());
        u.setImmagine(dto.getImmagine());
        u.setBio(dto.getBio());
        u.setNome(dto.getNome());
        u.setCognome(dto.getCognome());
        u.setEmail(dto.getEmail());
        u.setRuolo(dto.getRuolo());
        return u;
    }

    /* =========================================================
     * COLLECTION HELPERS
     * ========================================================= */

    public static <T> List<T> copyList(List<T> src) {
        return (src == null) ? null : new ArrayList<>(src);
    }

    public static <T> Set<T> copySet(Set<T> src) {
        return (src == null) ? null : new HashSet<>(src);
    }

    private static Set<Competenza> copyCompetenzaSet(Set<Competenza> src) {
        return (src == null) ? null : new HashSet<>(src);
    }
}
