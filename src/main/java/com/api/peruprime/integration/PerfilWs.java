package com.api.peruprime.integration;

import org.springframework.http.ResponseEntity;

import com.api.peruprime.controlador.dto.Respuesta;
//import com.api.peruprime.controlador.dto.UsuarioResponse;
import com.api.peruprime.exception.WSException;
import com.api.peruprime.modelo.Perfiles;
import com.api.peruprime.modelo.Usuario;

public interface PerfilWs {
	public ResponseEntity<Perfiles> obtenerPerfilPorID(Integer id) throws WSException;

	public ResponseEntity<?> editarPerfilPorID(Long id, Perfiles perfil) throws WSException;

//	public ResponseEntity<UsuarioResponse> validarDatos(String email, String pass) throws WSException;

	public ResponseEntity<?> guardarPerfil(Perfiles perfil) throws WSException;

//	public ResponseEntity<Respuesta> mayorIndiceUsuario(Respuesta respuesta) throws WSException;
	public ResponseEntity<Usuario> obtenerPerfilPorEmail(String email) throws WSException;
}