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
	
	@Query(value = "SELECT * FROM ESPACIO INNER JOIN AULA WHERE ESPACIO.FECHA=:fecha and ESPACIO.TURNO=:turno and AULA.ID=:#{#aula.id}", nativeQuery=true)
	public abstract Espacio traer(LocalDate fecha, char turno, @Param("aula") Aula aula); 

}
