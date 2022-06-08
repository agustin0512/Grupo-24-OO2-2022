package com.example.repo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.Aula;
import com.example.entities.Espacio;

@Repository("espacioRepository")
public interface IEspacioRepository extends JpaRepository<Espacio,Serializable> {
	
	@Query("SELECT e from Espacio e JOIN FETCH e.aula a WHERE e.fecha = (:fecha) "
			+ "AND e.turno = (:turno) AND a.id = :#{#aula.id}")
	public abstract List<Espacio> findAllByFechaAndTurnoAndAula(LocalDate fecha, char turno, @Param("aula") Aula aula);

}
