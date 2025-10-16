package dev2426.itsprojectwork.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev2426.itsprojectwork.Models.annunci;

@Repository
public interface annunciRepository extends JpaRepository<annunci, Long>{

}
