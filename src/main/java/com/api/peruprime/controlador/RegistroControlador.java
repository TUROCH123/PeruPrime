package com.api.peruprime.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.peruprime.servicio.UsuarioServicio;
import com.api.peruprime.util.Constantes;

@Controller
public class RegistroControlador {
	private static final Logger logger = LoggerFactory.getLogger(RegistroControlador.class);
	@Autowired
	private UsuarioServicio servicio;
	String user = Constantes.TEXTO_VACIO;

	@GetMapping("/home")
	public String home() {
		return "index";
	}

	@GetMapping("/login")
	public String iniciarSesion() {
		logger.info(Constantes.MENSAJE2, "[iniciarSesion] ", "login");
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) {

		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		return "index";
	}

	@GetMapping("/userAdmin{username}")
	public String inicioUserAdmin(Model modelo, @RequestParam(value = "username", required = false) String username) {
		logger.info(Constantes.MENSAJE2, "[inicioUserNotAdmin] ", username);
		if (username == null || username.isEmpty()) {
			logger.info(Constantes.MENSAJE2, "[inicioUserNotAdmin] ", "username es nulo");

		} else {
			if (!username.equals("admin@gmail.com")) {
				logger.info(Constantes.MENSAJE2, "[equals] ", username);
				return "index2";
			}
		}
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		return "index";
	}
	
	@GetMapping("/userNotAdmin{username}")
	public String inicioUserNotAdmin(Model modelo,
			@RequestParam(value = "username", required = false) String username) {
		logger.info(Constantes.MENSAJE2, "[inicioUserNotAdmin] ", username);
		if (username == null || username.isEmpty()) {
			logger.info(Constantes.MENSAJE2, "[inicioUserNotAdmin] ", "username es nulo");
//			modelo.addAttribute("usuarios", servicio.listarUsuarios());
		} else {
			if (!username.equals("admin@gmail.com")) {
				logger.info(Constantes.MENSAJE2, "[equals] ", username);
				return "index2";
			}
		}
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		return "index2";
	}

	@GetMapping("/tipoPlanes")
	public String tipoPlanes() {
		return "admin/tipoPlanes";
	}
}