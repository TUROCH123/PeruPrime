package com.api.peruprime.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.api.peruprime.controlador.dto.UsuarioRegistroDTO;
import com.api.peruprime.modelo.Usuario;


public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();
	
}
