package dev2426.itsprojectwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev2426.itsprojectwork.models.Annuncio;
import lombok.Data;

@Repository
public interface AnnunciRepository extends JpaRepository<Annuncio, Long>{
	

}
