package com.example.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Espacio {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_espacio",unique=true)
	private int id;
	
	@Column(name="fecha",nullable = false)
	private LocalDate fecha;
	
	@Column(name="turno",nullable = false)
	private char turno;
	
	@ManyToOne
	private Aula aula;
	
	@Column(name="libre",nullable = false)
	private boolean libre;

	public Espacio(LocalDate fecha, char turno, Aula aula, boolean libre) {
		super();
		this.fecha = fecha;
		this.turno = turno;
		this.aula = aula;
		this.libre = libre;
	}
	
	
	
}
