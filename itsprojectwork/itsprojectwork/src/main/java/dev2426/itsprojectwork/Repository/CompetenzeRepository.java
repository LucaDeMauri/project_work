package dev2426.itsprojectwork.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev2426.itsprojectwork.Models.Competenza;

@Repository
public interface CompetenzeRepository extends JpaRepository<Competenza, Long>{

}
