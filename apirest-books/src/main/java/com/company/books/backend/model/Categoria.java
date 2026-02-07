package com.company.books.backend.model; //representan tablas en la bbdd, SON MODELOS
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity //le dice a Hibernate que esta clase es una tabla
@Table(name="categorias") //crear el nombre de la tabla en la bbdd
public class Categoria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Categoria() {
		super();
	}
	
	public Categoria(Long id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	
	@Id //se asocia como clave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) //que cree este atributo como un campo autoincremental
	private Long id;
	private String nombre;
	private String descripcion;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
