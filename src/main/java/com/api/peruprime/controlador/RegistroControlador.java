package com.api.peruprime.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.api.peruprime.servicio.UsuarioServicio;

@Controller
public class RegistroControlador {

	@Autowired
	private UsuarioServicio servicio;

	@GetMapping("/home")
	public String home() {
		return "index";
	}
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) {
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		return "index";
	}
	
}