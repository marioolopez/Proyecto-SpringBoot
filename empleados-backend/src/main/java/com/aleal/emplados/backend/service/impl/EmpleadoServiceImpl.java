package com.aleal.emplados.backend.service.impl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aleal.emplados.backend.dao.IEmpleadoDao;
import com.aleal.emplados.backend.model.Empleado;
import com.aleal.emplados.backend.service.IEmpleadoService;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{
	
	@Autowired
	private IEmpleadoDao empleadoDao;

	@Override
	public List<Empleado> buscarEmpleados() {
		return empleadoDao.findAll();
	}

	@Override
	public Empleado buscarPorId(String id) {
		Optional<Empleado> empl = empleadoDao.findById(id); //el optional dice "yo lo guardo... pero nose si lo que me dices está ahí"
		if(empl.isPresent()){//si empleado esta ahi
			return empl.get();//retorna el valor 
		}
		return null;//sino devuelve nulo
	}

	@Override
	public Empleado crearEmpleado(Empleado empleado) {
		return empleadoDao.save(empleado);
	}

	@Override
	public void eliminarPorId(String id) {
		empleadoDao.deleteById(id);
	}
}