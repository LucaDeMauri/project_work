package dev2426.itsprojectwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev2426.itsprojectwork.models.Ruolo;

@Repository
public interface RuoliRepository extends JpaRepository<Ruolo, Long>{

}
