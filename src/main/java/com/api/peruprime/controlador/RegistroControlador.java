package com.api.peruprime.controlador;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.peruprime.modelo.User;
import com.api.peruprime.repositorio.UserRepositorio;
import com.api.peruprime.servicio.UsuarioServicio;
import com.api.peruprime.util.Constantes;

@Controller
public class RegistroControlador {
	private static final Logger logger = LoggerFactory.getLogger(RegistroControlador.class);
	@Autowired
	private UsuarioServicio servicio;
	@Autowired
	private UserRepositorio userRepositorio;
	
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
		logger.info(Constantes.MENSAJE2, "[inicioUserAdmin] ", username);
		if (username == null || username.isEmpty()) {
			logger.info(Constantes.MENSAJE2, "[inicioUserNotAdmin] ", "username es nulo");
			List<User> usere = userRepositorio.findAll();
			logger.info(Constantes.MENSAJE2, "[inicioUserNotAdmin] ", usere.get(0).getEmail());
			user = usere.get(0).getEmail() == null || usere.get(0).getEmail().equals("") ? "" : usere.get(0).getEmail();
			if (user.equals("admin@gmail.com")) {
				logger.info(Constantes.MENSAJE2, "[equals] ", username);
				modelo.addAttribute("usuarios", servicio.listarUsuarios());
				return "index";
			}
		}
		return "index";
	}
	
	@GetMapping("/userNotAdmin{username}")
	public String inicioUserNotAdmin(Model modelo,
			@RequestParam(value = "username", required = false) String username) {
		logger.info(Constantes.MENSAJE2, "[inicioUserNotAdmin] ", username);
		if (username == null || username.isEmpty()) {
			logger.info(Constantes.MENSAJE2, "[inicioUserNotAdmin] ", "username es nulo");
			user = username;
			}
		modelo.addAttribute("user", user);
		return "verificarPerfil";
	}

	@GetMapping("/tipoPlanes")
	public String tipoPlanes() {
		return "admin/tipoPlanes";
	}
	
	@GetMapping("/retroceder")
	public String retroceder() {
		return "/verificarPerfil";
	}
	
//	@GetMapping("/verificarRecibo/{userId}")
//	public String verificarRecibo(Model modelo,@RequestParam(value = "userId", required = false) String userId) {
//		logger.info(Constantes.MENSAJE2, "[verificarRecibo][userId] ", userId);
//		modelo.addAttribute("userId", userId);
//		return "admin/verificarRecibo";
//	}
}