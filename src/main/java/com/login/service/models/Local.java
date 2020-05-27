package com.login.service.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="local")
public class Local implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="codigo")
	private int codigo;
	@Column(name="nombre")
	private String nombre;
	@Column(name="direccion")
	private String direccion;
	@Column(name="telefono")
	private int telefono;
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	@Column(name="activo")
	private Boolean activo;
	@Column(name="habilitado")
	private Boolean habilitado;
	@Column(name="nit")
	private String nit;
	
	@OneToMany(mappedBy = "local",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	private Set<Plato> productos;
	
	@OneToMany(mappedBy = "local",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	private Set<Registro_venta> registor_venta;
	
	public Local() {
		
	}
	
	public Local(String nit, String nombre, String email, String direccion, int telefono, String password,
			Boolean activo, Boolean habilitado) {
		super();
		this.nit = nit;
		this.nombre = nombre;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.password = password;
		this.activo = activo;
		this.habilitado = habilitado;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	} 
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Set<Plato> getProductos() {
		return productos;
	}

	public void setProductos(Set<Plato> productos) {
		this.productos = productos;
	}

	public Set<Registro_venta> getRegistor_venta() {
		return registor_venta;
	}

	public void setRegistor_venta(Set<Registro_venta> registor_venta) {
		this.registor_venta = registor_venta;
	}
	
}
