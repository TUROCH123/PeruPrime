package com.api.peruprime.integration;

import org.springframework.http.ResponseEntity;

import com.api.peruprime.controlador.dto.Respuesta;
import com.api.peruprime.controlador.dto.UsuarioResponse;
import com.api.peruprime.exception.WSException;
import com.api.peruprime.modelo.Usuario;

public interface UsuarioWs {
	public Usuario obtenerUsuarioPorId(Long id) throws WSException;

	public ResponseEntity<?> actualizarUsuarioPorID(Long id, Usuario usuario) throws WSException;

	public ResponseEntity<UsuarioResponse> validarDatos(String email, String pass) throws WSException;

	public ResponseEntity<UsuarioResponse> guardarUsuario(Usuario usuario) throws WSException;

	public ResponseEntity<Respuesta> mayorIndiceUsuario(Respuesta respuesta) throws WSException;
}