package com.aleal.emplados.backend.service;

import java.util.List;

import com.aleal.emplados.backend.model.Empleado;

public interface IEmpleadoService {

	List<Empleado> buscarEmpleados(); //busca todos los empleados y los devuelve en una lista
	Empleado buscarPorId(String id); //busca un solo empleado por ID
	Empleado crearEmpleado(Empleado empleado); //crear un empleado pasandole el objeto
	void eliminarPorId(String id); //elimina un empleado por ID
}
