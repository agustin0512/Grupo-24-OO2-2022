package com.example.entities;

import java.time.LocalDateTime;


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

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="notas_pedidos")
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)

public class NotaPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_nota_pedido",unique=true,nullable = false)
	private int idNotaPedido ;
	
	
	@Column(name="fecha_pedido")
	@UpdateTimestamp
	private LocalDateTime fecha;
	
	@Column(name="turno" ,length=1, nullable = false)
	private char  turno;
	
	@ManyToOne
	@JoinColumn(name = "id_aula")
	private Aula aula;
	
	@ManyToOne
	@JoinColumn(name = "id_materia")
	private Materia materia;
	
	@Column(name="observaciones" ,length=150)
	private String  observaciones;

	

	public NotaPedido(int idNotaPedido, LocalDateTime fecha, char turno, Aula aula, Materia materia,
			String observaciones) {
		super();
		this.idNotaPedido = idNotaPedido;
		this.fecha = fecha;
		this.turno = turno;
		this.aula = aula;
		this.materia = materia;
		this.observaciones = observaciones;
	}
	
	@Override
	public String toString() {
		return "NotaPedido [idNotaPedido=" + idNotaPedido + ", fecha=" + fecha + ", turno=" + turno + ", aula=" + aula
				+ ", materia=" + materia + ", observaciones=" + observaciones + "]";
	}
	
	
	
	
	
	

}
