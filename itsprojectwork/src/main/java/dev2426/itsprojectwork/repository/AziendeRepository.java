package dev2426.itsprojectwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev2426.itsprojectwork.models.Azienda;

@Repository
public interface AziendeRepository extends JpaRepository<Azienda, Long> {

}
