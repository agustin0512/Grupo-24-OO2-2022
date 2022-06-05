package com.example.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.Edificio;
import com.example.entities.User;

 
	@Repository("edificioRepository")
	public interface IEdificioRepository extends CrudRepository <Edificio, Serializable>{
		//Edificio findByName(String nombreEdificio);
		
		@Query("SELECT u FROM Edificio u  WHERE u.edificio = (:nombreEdificio)")
		
		public abstract Edificio findByEdificio(@Param("nombreEdificio") String edificio);

}
