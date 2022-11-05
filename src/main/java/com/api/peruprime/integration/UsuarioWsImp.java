package com.api.peruprime.integration;

import java.util.HashMap;
import java.util.Map;
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
import com.api.peruprime.controlador.dto.UsuarioResponse;
import com.api.peruprime.exception.WSException;
import com.api.peruprime.modelo.Usuario;
import com.api.peruprime.util.Constantes;

@Component
public class UsuarioWsImp implements UsuarioWs {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioWsImp.class);
	@Autowired
	RestTemplate restTemplateUsuario;

	@Override
	public Usuario obtenerUsuarioPorId(Long id) throws WSException {
		Usuario response = new Usuario();
		String url = "http://localhost:8095/api/v1/login/obtenerUsuarioPorID/" + id;
		String nombreComponente = "login";
		String nombreMetodo = "obtenerUsuarioPorID";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		try {
			response = restTemplateUsuario.getForObject(url, Usuario.class);
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
	public ResponseEntity<?> actualizarUsuarioPorID(Long id, Usuario usuario) throws WSException {
		ResponseEntity<Usuario> response = null;
		String url = "http://localhost:8095/api/v1/login/actualizarUsuarioPorID?id=" + id;
//		String url = "http://localhost:8095/api/v1/login/actualizarUsuarioPorID/"+id;
		String nombreComponente = "login";
		String nombreMetodo = "actualizarUsuarioPorID";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		logger.info("message {}{}", "[id]", id);
		try {
			HttpEntity<Usuario> req = new HttpEntity<>(usuario);
//			Map<String, String> uriVariables = new HashMap<>();
//			uriVariables.put("id", String.valueOf(id));
			response = restTemplateUsuario.exchange(url, HttpMethod.PUT, req, Usuario.class);

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
	public ResponseEntity<UsuarioResponse> validarDatos(String email, String pass) throws WSException {
		ResponseEntity<UsuarioResponse> response = null;
//		String url = "http://localhost:8080/api/v1/login/validarDatos/?email=" + email + "&pass=" + pass + "";
		String url = "http://localhost:8095/api/v1/login/validarDatos/";
		String nombreComponente = "login";
		String nombreMetodo = "validarDatos";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		logger.info("message {}{}", "[email]", email);
		try {
//			response = restTemplateUsuario.getForObject(url, Usuario.class);
			HttpEntity<?> req = null;
			Map<String, String> uriVariables = new HashMap<>();
			uriVariables.put("email", email);
			uriVariables.put("pass", pass);
			response = restTemplateUsuario.exchange(url, HttpMethod.GET, req, UsuarioResponse.class, uriVariables);
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
	public ResponseEntity<UsuarioResponse> guardarUsuario(Usuario usuario) throws WSException {
		ResponseEntity<UsuarioResponse> response = null;
		String url = "http://localhost:8095/api/v1/login/guardarUsuario";
		String nombreComponente = "login";
		String nombreMetodo = "guardarUsuario";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		try {
			HttpEntity<Usuario> req = new HttpEntity<>(usuario);
			response = restTemplateUsuario.exchange(url, HttpMethod.POST, req, UsuarioResponse.class);

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

	public ResponseEntity<Respuesta> mayorIndiceUsuario(Respuesta respuesta) throws WSException {
		ResponseEntity<Respuesta> response = null;
		String url = "http://localhost:8095/api/v1/login/obtenerMayorIndice";
		String nombreComponente = "login";
		String nombreMetodo = "obtenerMayorIndice";
		String msj = "[" + nombreComponente + "][" + nombreMetodo + "]";
		logger.info("message {}{}", "[INICIO]", msj);
		logger.info("message {}{}", "[url]", url);
		try {
			HttpEntity<Respuesta> req = new HttpEntity<>(respuesta);
			response = restTemplateUsuario.exchange(url, HttpMethod.POST, req, Respuesta.class);
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