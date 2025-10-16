package dev2426.itsprojectwork.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev2426.itsprojectwork.Models.aziende;

@Repository
public interface aziendeRepository extends JpaRepository<aziende, Long> {

}
