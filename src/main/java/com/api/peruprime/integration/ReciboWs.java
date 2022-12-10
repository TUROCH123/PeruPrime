package com.api.peruprime.integration;

import org.springframework.http.ResponseEntity;
//import com.api.peruprime.controlador.dto.Respuesta;
import com.api.peruprime.exception.WSException;
//import com.api.peruprime.modelo.Pago;
import com.api.peruprime.modelo.Recibo;
import com.api.peruprime.modelo.Usuario;

public interface ReciboWs {

	public ResponseEntity<?> realizarRecibo(Recibo recibo) throws WSException;
//	public Recibo obtenerUsuarioPorId(String id) throws WSException;
//	public ResponseEntity<Respuesta> mayorIndicePago(Respuesta respuesta) throws WSException;
	public Recibo obtenerReciboPorId(String id) throws WSException;
}