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
import com.api.peruprime.controlador.dto.UsuarioResponse;
import com.api.peruprime.exception.WSException;
import com.api.peruprime.modelo.Perfiles;
import com.api.peruprime.modelo.Usuario;
import com.api.peruprime.util.Constantes;

@Component
public class PerfilWsImp implements PerfilWs {
	private static final Logger logger = LoggerFactory.getLogger(PerfilWsImp.class);
	@Autowired
	RestTemplate restTemplatePerfiles;

	@Override
	public ResponseEntity<Perfiles> obtenerPerfilPorID(Integer id) throws WSException {
		ResponseEntity<Perfiles> response = null;
		String url = "http://localhost:8095/api/v1/perfil/obtenerPerfilPorID/?id=" + id;
		String nombreComponente = "perfil";
		String nombreMetodo = "obtenerPerfilPorID";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		try {
			HttpEntity<?> req = null;
//			response = restTemplatePerfiles.getForObject(url, Perfiles.class);
			response = restTemplatePerfiles.exchange(url, HttpMethod.GET, req, Perfiles.class);
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

	@Override
	public ResponseEntity<?> editarPerfilPorID(Long id, Perfiles perfil) throws WSException {
		ResponseEntity<Perfiles> response = null;
		String url = "http://localhost:8095/api/v1/perfil/editarPerfilPorID?id=" + id;

		String nombreComponente = "perfil";
		String nombreMetodo = "editarPerfilPorID";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		logger.info("message {}{}", "[id]", id);
		try {
			HttpEntity<Perfiles> req = new HttpEntity<>(perfil);
			response = restTemplatePerfiles.exchange(url, HttpMethod.PUT, req, Perfiles.class);

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

	@Override
	public ResponseEntity<?> guardarPerfil(Perfiles perfil) throws WSException {
		ResponseEntity<?> response = null;
		String url = "http://localhost:8095/api/v1/perfil/guardarPerfil";
		String nombreComponente = "perfil";
		String nombreMetodo = "guardarPerfil";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		try {
			HttpEntity<Perfiles> req = new HttpEntity<>(perfil);
			response = restTemplatePerfiles.exchange(url, HttpMethod.POST, req, Perfiles.class);

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

//	public ResponseEntity<Respuesta> mayorIndiceUsuario(Respuesta respuesta) throws WSException {
//		ResponseEntity<Respuesta> response = null;
//		String url = "http://localhost:8095/api/v1/perfil/obtenerMayorIndice";
//		String nombreComponente = "login";
//		String nombreMetodo = "obtenerMayorIndice";
//		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
//		logger.info("message {}{}", "[INICIO]", msj);
//		logger.info("message {}{}", "[url]", url);
//		try {
//			HttpEntity<Respuesta> req = new HttpEntity<>(respuesta);
//			response = restTemplatePerfiles.exchange(url, HttpMethod.POST, req, Respuesta.class);
//		} catch (JaxWsSoapFaultException e) {
//			throw new WSException(Constantes.CODIGO_IDT3,
//					String.format(Constantes.MENSAJE_IDT3, nombreComponente, nombreMetodo), e);
//		} catch (Exception e) {
//			Constantes.capturarErrorWs(e, nombreComponente, nombreMetodo);
//		} finally {
//			logger.info("message {}{}", "[FIN]", msj);
//		}
//		return response;
//	}
	
	@Override
	public ResponseEntity<Usuario> obtenerPerfilPorEmail(String email) throws WSException {
		ResponseEntity<Usuario> response = null;
//		String url = "http://localhost:8095/api/v1/perfil/obtenerPerfilPorEmail/" + email;
		String url = "http://localhost:8095/api/v1/perfil/obtenerPerfilPorEmail/?email="+email;
		String nombreComponente = "perfil";
		String nombreMetodo = "obtenerPerfilPorEmail";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		logger.info("message {}{}", "[email]", email);
		try {
			HttpEntity<?> req = null;
//			Map<String, String> uriVariables = new HashMap<>();
//			uriVariables.put("email", email);
//			uriVariables.put("pass", pass);
			response = restTemplatePerfiles.exchange(url, HttpMethod.GET, req, Usuario.class);
//			response = restTemplatePerfiles.getForObject(url, Usuario.class);
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