package com.login.service.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="registro_venta")
public class Registro_venta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="codigo")
	private int codigo;
	@Column(name="precio_total")
	private int precio_total;
	@Column(name="numero_mesa")
	private int numero_mesa;
	@Column(name="fecha")
	private Date fecha;
	@Column(name="cod_local")
	private int cod_local;
	
	@ManyToOne
	@JoinColumn(name = "cod_local", insertable = false, updatable = false)
	private Local local;
	
	public Registro_venta() {

	}
	
	public Registro_venta(int precio_total, int numero_mesa, Date fecha, int cod_local) {
		super();
		this.precio_total = precio_total;
		this.numero_mesa = numero_mesa;
		this.fecha = fecha;
		this.cod_local = cod_local;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getPrecio_total() {
		return precio_total;
	}
	public void setPrecio_total(int precio_total) {
		this.precio_total = precio_total;
	}
	public int getNumero_mesa() {
		return numero_mesa;
	}
	public void setNumero_mesa(int numero_mesa) {
		this.numero_mesa = numero_mesa;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCod_local() {
		return cod_local;
	}
	public void setCod_local(int cod_local) {
		this.cod_local = cod_local;
	}
}
