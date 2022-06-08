package com.example.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="user")
@Getter @Setter @NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username", unique=true,  length=45)
	private String username;
	
	@Column(name="password", length=60)
	private String password;
	
	@Column(name="mail", length=60)
	private String mail;
	
	@Column(name="nombre",  length=60)
	private String nombre;
	
	@Column(name="apellido", length=60)
	private String apellido;
	
	@Column(name="tipodoc",  length=4)
	private String tipodoc;
	
	@Column(name="dni",unique=true)
	private long dni;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="createdat")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updatedat")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	
	@ManyToOne
	@JoinColumn(name="id_rol")
	private UserRole rol;
	
	
	
	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	
	public User(String username, String password, boolean enabled, UserRole rol) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", mail=" + mail + ", nombre="
				+ nombre + ", apellido=" + apellido + ", tipodoc=" + tipodoc + ", dni=" + dni + ", userRoles="
				+ rol + "]";
	}

	
}