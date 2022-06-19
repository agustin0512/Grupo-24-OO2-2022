package com.example.entities;

 

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="nota_pedido_curso")
@Getter @Setter @NoArgsConstructor 
@PrimaryKeyJoinColumn(name="id_nota_pedido")	

public class Curso extends NotaPedido{
	
	@Column(name="cod_curso",length=100, nullable = false)
	private String codCurso;

	public Curso(LocalDate fecha, char turno, String aula, Materia materia, int cantEstudiantes, String observaciones,
			String codCurso) {
		super(fecha, turno, aula, materia, cantEstudiantes, observaciones);
		this.codCurso = codCurso;
	}

	@Override
	public String toString() {
		return "Curso [codCurso=" + codCurso + "]";
	}
	
	

}
