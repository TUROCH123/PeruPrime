package com.api.peruprime.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.remoting.jaxws.JaxWsSoapFaultException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.api.peruprime.controlador.dto.Respuesta;
import com.api.peruprime.exception.WSException;
import com.api.peruprime.modelo.Pago;
import com.api.peruprime.util.Constantes;

@Component
public class PagoWsImp implements PagoWs {
	private static final Logger logger = LoggerFactory.getLogger(PagoWsImp.class);
	@Autowired
	RestTemplate restTemplatePago;

	public ResponseEntity<?> realizarPago(Pago pago) throws WSException {
		ResponseEntity<Pago> response = null;
		String url = "http://localhost:8095/api/v1/pago/realizarPago";
		String nombreComponente = "pago";
		String nombreMetodo = "realizarPago";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		try {
			HttpEntity<Pago> req = new HttpEntity<>(pago);
			response = restTemplatePago.exchange(url, HttpMethod.POST, req, Pago.class);

		} catch (JaxWsSoapFaultException e) {
			throw new WSException(Constantes.CODIGO_IDT3,
					String.format(Constantes.MENSAJE_IDT3, nombreComponente, nombreMetodo), e);
		} catch (Exception e) {
			Constantes.capturarErrorWs(e, nombreComponente, nombreMetodo);
		} finally {
			logger.info("message {}{}", "[FIN]", msj);
		}
		return response;
	}

	public ResponseEntity<Respuesta> mayorIndicePago(Respuesta respuesta) throws WSException {
		ResponseEntity<Respuesta> response = null;
		String url = "http://localhost:8095/api/v1/pago/obtenerMayorIndice";
		String nombreComponente = "pago";
		String nombreMetodo = "obtenerMayorIndice";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		try {
			HttpEntity<Respuesta> req = new HttpEntity<>(respuesta);
			response = restTemplatePago.exchange(url, HttpMethod.POST, req, Respuesta.class);

		} catch (JaxWsSoapFaultException e) {
			throw new WSException(Constantes.CODIGO_IDT3,
					String.format(Constantes.MENSAJE_IDT3, nombreComponente, nombreMetodo), e);
		} catch (Exception e) {
			Constantes.capturarErrorWs(e, nombreComponente, nombreMetodo);
		} finally {
			logger.info("message {}{}", "[FIN]", msj);
		}
		return response;
	}

}