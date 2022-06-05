package com.example.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="aulas")
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
public class Aula {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_aula",nullable = false , unique=true)
	private int id ;
	
	
	@Column(name="num_aula",nullable = false , unique=true)
	private int numero ;
	
	@ManyToOne
	private Edificio edificio;
 
	
	public Aula(int id, int numero, Edificio edificio) {
		super();
		this.id = id;
		this.numero = numero;
		this.edificio = edificio;
	}


	@Override
	public String toString() {
		return "Aula [id=" + id + ", numero=" + numero + ", edificio=" + edificio + "]";
	}
	
 	

}
