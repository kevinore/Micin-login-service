package com.login.service.models;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="plato")
public class Plato implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="codigo")
	private int codigo;
	@Column(name="nombre")
	private String nombre;
	@Column(name="costo")
	private int costo;
	@Column(name="cod_loca")
	private int cod_local;
	
	@ManyToOne
	@JoinColumn(name="cod_local", insertable = false, updatable = false)
	private Local local;
	
	public Plato() {
	}
	
	public Plato(String nombre, int costo, int cod_local) {
		super();
		this.nombre = nombre;
		this.costo = costo;
		this.cod_local = cod_local;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public int getCod_local() {
		return cod_local;
	}
	public void setCod_local(int cod_local) {
		this.cod_local = cod_local;
	}
	
}
