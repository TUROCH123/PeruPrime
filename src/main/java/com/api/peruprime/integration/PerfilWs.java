package com.api.peruprime.integration;

import org.springframework.http.ResponseEntity;
import com.api.peruprime.exception.WSException;
import com.api.peruprime.modelo.Perfiles;
import com.api.peruprime.modelo.Usuario;

public interface PerfilWs {
	public ResponseEntity<Perfiles> obtenerPerfilPorID(Integer id) throws WSException;
	public ResponseEntity<?> editarPerfilPorID(Long id, Perfiles perfil) throws WSException;
	public ResponseEntity<?> guardarPerfil(Perfiles perfil) throws WSException;
	public ResponseEntity<Usuario> obtenerPerfilPorEmail(String email) throws WSException;
}