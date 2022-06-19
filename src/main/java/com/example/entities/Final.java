package com.example.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="nota_pedido_finales")
@Getter @Setter @NoArgsConstructor 
@PrimaryKeyJoinColumn(name="id_nota_pedido")

public class Final extends NotaPedido{
	
	
	@Column(name="fecha_examen")
	@UpdateTimestamp
	private LocalDate fechaExamen;

	public Final(LocalDate fecha, char turno, String aula, Materia materia, int cantEstudiantes, String observaciones,
			LocalDate fechaExamen) {
		super(fecha, turno, aula, materia, cantEstudiantes, observaciones);
		this.fechaExamen = fechaExamen;
	}

	@Override
	public String toString() {
		return "Final [fechaExamen=" + fechaExamen + "]";
	}
	
	
}
