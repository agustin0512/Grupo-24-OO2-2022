package com.example.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="notas_pedidos")
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)

public class NotaPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_nota_pedido",unique=true,nullable = false)
	private int idNotaPedido ;
	
	
	@Column(name="fecha_pedido")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	
	@Column(name="turno" ,length=1, nullable = false)
	private char  turno;
	
	
	@JoinColumn(name = "aula")
	private String aula;
	
	@ManyToOne
	@JoinColumn(name = "id_materia")
	private Materia materia;
	
	@Column(name="cant_estudiantes" ,length=150)
	private int cantEstudiantes;
	
	@Column(name="observaciones" ,length=150)
	private String  observaciones;

	public NotaPedido(LocalDate fecha, char turno, String aula, Materia materia,
			int cantEstudiantes, String observaciones) {
		super();
		this.fecha = fecha;
		this.turno = turno;
		this.aula = aula;
		this.materia = materia;
		this.cantEstudiantes = cantEstudiantes;
		this.observaciones = observaciones;
	}

	@Override
	public String toString() {
		return "NotaPedido [idNotaPedido=" + idNotaPedido + ", fecha=" + fecha + ", turno=" + turno + ", aula=" + aula
				+ ", materia=" + materia + ", cantEstudiantes=" + cantEstudiantes + ", observaciones=" + observaciones
				+ "]";
	}

}
