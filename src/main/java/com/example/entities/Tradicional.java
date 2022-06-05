package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tradicionales")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name="id_aula")

public class Tradicional extends Aula{
	
@Column(name="cant_bancos",nullable = false)	
private int cantBancos;

@Column(name="pizzaron")
private String pizzaron;

@Column(name="proyector")
private boolean tieneProyector;


public Tradicional(int cantBancos, String pizzaron, boolean tieneProyector) {
	super();
	this.cantBancos = cantBancos;
	this.pizzaron = pizzaron;
	this.tieneProyector = tieneProyector;
}


}
