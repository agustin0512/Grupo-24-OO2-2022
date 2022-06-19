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
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="departamentos")
@Getter @Setter @NoArgsConstructor

public class Departamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_dpto",unique=true)
	private int idDepartamento ;
	
	@Column(name="nombre_depto",length=150)
	private String departamento;
	
	@OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<Carrera> carreras= new ArrayList<>();

	public Departamento(int idDepartamento, String departamento, List<Carrera> carreras) {
		super();
		this.idDepartamento = idDepartamento;
		this.departamento = departamento;
		this.carreras = carreras;
	}

	@Override
	public String toString() {
		return "Departamento [idDepartamento=" + idDepartamento + ", departamento=" + departamento + "]";
	}
	
	

}
