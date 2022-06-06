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
@Table(name="materias")
@Getter @Setter @NoArgsConstructor

public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_materia",unique=true,nullable = false)
	private int idMateria ;
	
	@Column(name="cod_materia",unique=true,nullable = false)
	private int codMateria ;
	
	@Column(name="nombre_materia",length=150, nullable = false)
	private String materia;
	
	@ManyToOne
	@JoinColumn(name = "id_carrera")
	private Carrera carrera;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_nota_pedido")
    private List<NotaPedido> notasPedidos;

	
	public Materia(int idMateria, int codMateria, String materia, Carrera carrera) {
		super();
		this.idMateria = idMateria;
		this.codMateria = codMateria;
		this.materia = materia;
		this.carrera = carrera;
	}

	@Override
	public String toString() {
		return "Materia [idMateria=" + idMateria + ", codMateria=" + codMateria + ", materia=" + materia + ", carrera="
				+ carrera + "]";
	}
	
	
	
}
