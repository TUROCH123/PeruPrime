package com.api.peruprime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import com.api.peruprime.controlador.RegistroControlador;
import com.api.peruprime.modelo.User;
import com.api.peruprime.repositorio.UserRepositorio;
import com.api.peruprime.servicio.UsuarioServicio;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
class RegistroControladorTests {
	@InjectMocks
	private RegistroControlador registroControlador;
	@Mock
	private UsuarioServicio servicio;
	@Mock
	private UserRepositorio userRepositorio;
	@Mock
	private Model modelo;
	  @SuppressWarnings("null")
	@Test
	  void testException001() throws JsonProcessingException {
	    try {

	      registroControlador.home();
	      registroControlador.iniciarSesion();
	      registroControlador.verPaginaDeInicio(modelo);
	      String username = "";
	      List<User> response = new ArrayList<>();
	      User user = new User();
	      user.setEmail("ajochav@gmail.com");
	      response.add(user);
	      Mockito
	        .when(userRepositorio.findAll())
	        .thenReturn(response);
	      registroControlador.inicioUserAdmin(modelo, username);
	      user.setEmail("admin@gmail.com");
	      registroControlador.inicioUserAdmin(modelo, username);
	      registroControlador.tipoPlanes();
	      username = "";
	      registroControlador.inicioUserNotAdmin(modelo, username);
	    } catch (Exception e) {
	      assertNotNull(e);
	    }
	  }
}
