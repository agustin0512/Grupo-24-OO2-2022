package com.example.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="edificios")
@Getter @Setter @NoArgsConstructor

public class Edificio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_edificio",unique=true)
	private int idEdificio ;
	
	@Column(name="nombre_edificio",length=150)
	private String edificio;
	
	@OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<Aula> aulas= new ArrayList<>();

	
	public Edificio(int idEdificio, String edificio  ) {
		super();
		this.idEdificio = idEdificio;
		this.edificio = edificio;
		 
	}


	@Override
	public String toString() {
		return "Edificio [idEdificio=" + idEdificio + ", edificio=" + edificio + ", aulas=" + aulas + "]";
	}
	
	
	
	
}
