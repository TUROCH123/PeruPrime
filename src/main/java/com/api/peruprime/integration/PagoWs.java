package com.api.peruprime.integration;

import org.springframework.http.ResponseEntity;
import com.api.peruprime.controlador.dto.Respuesta;
import com.api.peruprime.exception.WSException;
import com.api.peruprime.modelo.Pago;

public interface PagoWs {

	public ResponseEntity<?> realizarPago(Pago pago) throws WSException;

	public ResponseEntity<Respuesta> mayorIndicePago(Respuesta respuesta) throws WSException;
}