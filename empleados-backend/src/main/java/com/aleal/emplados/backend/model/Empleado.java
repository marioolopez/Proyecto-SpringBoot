package com.aleal.emplados.backend.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "empleado")
public class Empleado {
 
	@Id
	private String id;
	private String nombre;
	private String apellido;
	private int edad;
	private String direccion;
	private String telefono;
	
}
