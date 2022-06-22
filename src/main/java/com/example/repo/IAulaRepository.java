package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.Aula;


@Repository("aulaRepository")
public interface IAulaRepository extends JpaRepository<Aula, Integer> {
	
	@Query("select a from Aula a where a.edificio.idEdificio = :id_edificio")
	List<Aula> getAulasByEdificio(@Param("id_edificio") int idEdificio);

}
