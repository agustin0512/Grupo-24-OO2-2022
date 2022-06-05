package com.example.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.Edificio;
 
	@Repository("edificioRepository")
	public interface IEdificioRepository extends CrudRepository <Edificio, Serializable>{
		//Edificio findByName(String nombreEdificio);
		@Query("SELECT e FROM Edificio e JOIN FETCH e.aulas WHERE e.id = (:id)")
		 Edificio findByIdAndFetchAulas(@Param("id") int id);
		
		 
		@Query("SELECT u FROM Edificio u  WHERE u.edificio = (:nombreEdificio)")
		public abstract Edificio findByEdificio(@Param("nombreEdificio") String edificio);

}
