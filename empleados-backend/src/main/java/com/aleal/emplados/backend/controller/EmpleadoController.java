package com.aleal.emplados.backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.aleal.emplados.backend.model.Empleado;
import com.aleal.emplados.backend.service.IEmpleadoService;

//@RestController --> Para definir un controlador de una API REST (OSEA ESTO DEVUELVE DATOS.. NO VISTAS)
//@Controller --> Para definir un controlador usando Spring MVC  (Y ESTO PARA QUE TE DEVUELVA VISTAS: html, Thymeleaf, JSP...) 
@Controller 
@RequestMapping("/")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService service;
	
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("list", service.buscarEmpleados());
		return "index";
	}
	
	
	@PostMapping("/guardar")
	public String save(Empleado empleado, Model model){
		service.crearEmpleado(empleado); //creamos empleado
		return "redirect:/"; //redireccionamos de nuevo a la pestaña 
	}
	
	
	@GetMapping("/guardar/{id}")
	public String showSave(@PathVariable("id") String id, Model model){
		if(id != null && !"0".equals(id)) {
			model.addAttribute("empleado", service.buscarPorId(id));
		} else {
			model.addAttribute("empleado", new Empleado());
		}
		return "guardar";
	}
	

	@GetMapping("/eliminar/{id}")
	public String eliminarPorId(@PathVariable("id") String id, Model model) {
		service.eliminarPorId(id);
		return "redirect:/";
	}	
	
}