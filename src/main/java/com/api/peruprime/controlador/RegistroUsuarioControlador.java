package com.api.peruprime.controlador;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.api.peruprime.controlador.dto.UsuarioRegistroDTO;
import com.api.peruprime.servicio.UsuarioServicio;
import com.api.peruprime.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {
	private static final Logger logger = LoggerFactory.getLogger(RegistroUsuarioControlador.class);
	private UsuarioServicio usuarioServicio;

	public RegistroUsuarioControlador(UsuarioServicio usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}
	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO)
			throws JsonProcessingException {
		registroDTO.setSuscrito("FREE");
		registroDTO.setFechaInscripcion(new Date());
		registroDTO.setFechaVencimiento("");
		String usr = Constantes.printPrettyJSONString(registroDTO);
		logger.info(Constantes.MENSAJE2, "[registroDTO] ", usr);
		usuarioServicio.guardar(registroDTO);
		return "redirect:/registro?exito";
	}
}
