// dev2426.itsprojectwork.Repository.UtenteRepository.java
package dev2426.itsprojectwork.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import dev2426.itsprojectwork.Models.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByEmail(String email);
    Optional<Utente> findByToken(String token);
    boolean existsByEmail(String email);
}
