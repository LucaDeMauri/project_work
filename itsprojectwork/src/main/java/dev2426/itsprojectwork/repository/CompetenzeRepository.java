package dev2426.itsprojectwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev2426.itsprojectwork.models.Competenza;

@Repository
public interface CompetenzeRepository extends JpaRepository<Competenza, Long>{

}
