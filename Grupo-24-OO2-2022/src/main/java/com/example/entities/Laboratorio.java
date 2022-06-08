package com.example.entities;

 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="laboratorios")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name="id_aula")

public class Laboratorio extends Aula{

	 
	//@Column(name="id_laboratorio",nullable = false)
//	private int idLaboratorio;
	
	@Column(name="cant_pc",nullable = false)
	private int cantPc;
	
	@Column(name="cant_sillas",nullable = false)
	private int cantSillas;

	
	@Override
	public String toString() {
		return "Laboratorio [cantPc=" + cantPc + ", cantSillas=" + cantSillas + "]";
	}


	public Laboratorio(int id, int numero, Edificio edificio, int cantPc, int cantSillas) {
		super(id, numero, edificio);
		this.cantPc = cantPc;
		this.cantSillas = cantSillas;
	}
	
	
	
}
