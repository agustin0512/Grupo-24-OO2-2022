package com.example.entities;

 

import java.time.LocalDateTime;

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

	public Curso(int idNotaPedido, LocalDateTime fecha, char turno, Aula aula, Materia materia, String observaciones,
			String codCurso) {
		super(idNotaPedido, fecha, turno, aula, materia, observaciones);
		this.codCurso = codCurso;
	}

	@Override
	public String toString() {
		return "Curso [codCurso=" + codCurso + "]";
	}
	
	

}
