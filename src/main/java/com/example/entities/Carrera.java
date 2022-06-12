package com.example.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="carreras")
@Getter @Setter @NoArgsConstructor
public class Carrera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_carrera",unique=true)
	private int idCarrera ;
	
	@Column(name="nombre_carrera",length=150, nullable = false)
	private String carrera;
	
	@ManyToOne
	@JoinColumn(name = "id_dpto")
	private Departamento departamento;
	
	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL ,  orphanRemoval = true)
	private List<Materia> materias;

	public Carrera(int idCarrera, String carrera, Departamento departamento) {
		super();
		this.idCarrera = idCarrera;
		this.carrera = carrera;
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Carrera [idCarrera=" + idCarrera + ", carrera=" + carrera + ", departamento=" + departamento + "]";
	}
	
	
	
	

}
