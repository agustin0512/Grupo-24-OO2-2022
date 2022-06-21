package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Carrera;
@Repository("carreraRepository")
public interface ICarreraRepository extends JpaRepository<Carrera,Integer> {
	
}
