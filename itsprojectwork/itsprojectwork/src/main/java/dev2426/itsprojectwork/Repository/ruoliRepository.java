package dev2426.itsprojectwork.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev2426.itsprojectwork.Models.ruoli;

@Repository
public interface ruoliRepository extends JpaRepository<ruoli, Long>{

}
