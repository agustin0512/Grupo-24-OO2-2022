package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Aula;

@Repository("aulaRepository")
public interface IAulaRepository extends JpaRepository<Aula, Integer> {

}
