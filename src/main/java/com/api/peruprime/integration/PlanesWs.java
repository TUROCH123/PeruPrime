package com.api.peruprime.integration;

import org.springframework.http.ResponseEntity;
import com.api.peruprime.controlador.dto.Respuesta;
import com.api.peruprime.exception.WSException;

public interface PlanesWs {
	public ResponseEntity<Respuesta> mayorIndicePlanes(Respuesta respuesta) throws WSException;
}
