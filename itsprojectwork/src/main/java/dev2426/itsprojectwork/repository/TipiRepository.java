package dev2426.itsprojectwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev2426.itsprojectwork.models.Tipo;

@Repository
public interface TipiRepository extends JpaRepository<Tipo, Long> {

}
