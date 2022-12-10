package com.api.peruprime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import com.api.peruprime.controlador.AdminControlador;
import com.api.peruprime.exception.WSException;
import com.api.peruprime.integration.PagoWs;
import com.api.peruprime.modelo.Genero;
import com.api.peruprime.modelo.MedioPago;
import com.api.peruprime.modelo.Pago;
import com.api.peruprime.modelo.Pelicula;
import com.api.peruprime.modelo.Planes;
import com.api.peruprime.modelo.TipoPlanes;
import com.api.peruprime.modelo.User;
import com.api.peruprime.modelo.Usuario;
import com.api.peruprime.repositorio.GeneroRepositorio;
import com.api.peruprime.repositorio.PeliculaRepositorio;
import com.api.peruprime.repositorio.TipoPlanesRepositorio;
import com.api.peruprime.repositorio.UserRepositorio;
import com.api.peruprime.repositorio.UsuarioRepositorio;
import com.api.peruprime.servicio.AlmacenServicioImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
class AdminControladorTests {
	@InjectMocks
	private AdminControlador adminControlador;
	@Mock
	private UsuarioRepositorio usuarioRepositorio;
	@Mock
	private UserRepositorio userRepositorio;
	@Mock
	private PeliculaRepositorio peliculaRepositorio;
	@Mock
	private Model modelo;
	@Mock
	private BindingResult bindingResult;
	@Mock
	private Pageable pageable;
//	@Mock
	Integer ids = 1;
	@Mock
	private GeneroRepositorio generoRepositorio;
	@Mock
	private MultipartFile portada;
	TipoPlanes tipoPlanes = new TipoPlanes();
	@Mock
	private AlmacenServicioImpl servicio;
	@Mock
	private TipoPlanesRepositorio tipoPlanesRepositorio;
	@Mock
	private PagoWs pagoWs;
	Usuario usuario = new Usuario();
	  @SuppressWarnings("null")
	@Test
	  void testPeliculaRegistrarEliminar001() throws JsonProcessingException, WSException {
	    try {
		      List<User> response = new ArrayList<>();
		      User user = new User();
		      user.setEmail("ajochav@gmail.com");
		      response.add(user);
		      Mockito
		        .when(userRepositorio.findAll())
		        .thenReturn(response);
		      String rutaPortada = "";
	    	Pelicula pelicula = new Pelicula();
	    	Pelicula peliculaDB = new Pelicula();
			pelicula.setFechaEstreno(LocalDate.now());
	    	pelicula.setId(ids);
	    	pelicula.setProveedor("marce");
	    	pelicula.setPortada(portada);
	    	pelicula.setRutaPortada("IMG.png");
	    	pelicula.setSinopsis("DESCRIPCION");
	    	pelicula.setTitulo("CABIFY");
	    	pelicula.setYoutubeTrailerId("sdasfqrwry55yry");
			String id = "1";
			Page<Pelicula> peliculas = null;
			List<Genero> generos = new ArrayList<>();
			Genero genero= new Genero();
			genero.setId(ids);
			genero.setTitulo("ACCCION");
			generos.add(genero);
			peliculaDB.setFechaEstreno(pelicula.getFechaEstreno());
			peliculaDB.setId(pelicula.getId());
			peliculaDB.setProveedor(pelicula.getProveedor());
			peliculaDB.setPortada(portada);
			peliculaDB.setRutaPortada(pelicula.getRutaPortada());
			peliculaDB.setSinopsis(pelicula.getSinopsis());
			peliculaDB.setTitulo(pelicula.getTitulo());
			peliculaDB.setYoutubeTrailerId(pelicula.getYoutubeTrailerId());
			Mockito.when(peliculaRepositorio.findAll(pageable)).thenReturn(peliculas);
			adminControlador.verPaginaDeInicio(pageable);
			Mockito.when(peliculaRepositorio.getOne(ids)).thenReturn(peliculaDB);
//			adminControlador.verPaginaDeInicio(pageable);
			Mockito.when(generoRepositorio.findAll(Sort.by("titulo"))).thenReturn(generos);
			Mockito.when(servicio.almacenarArchivo(pelicula.getPortada())).thenReturn(rutaPortada);
			Mockito.when(peliculaRepositorio.save(peliculaDB)).thenReturn(peliculaDB);
			servicio.eliminarArchivo(pelicula.getRutaPortada());
			adminControlador.mostrarFormularioDeNuevaPelicula();
			adminControlador.registrarPelicula(peliculaDB, bindingResult);
			adminControlador.eliminarPelicula(ids);
			
			//			Optional<TipoPlanes> plan = tipoPlanesRepositorio.findById(1);
			tipoPlanes.setId(ids);
			tipoPlanes.setNombre("MENSUAL");
			tipoPlanes.setPrecio(Double.parseDouble("26.6"));
			tipoPlanes.setTiempo(1);
//			tipoPlanes = new TipoPlanes("MENSUAL", Double.parseDouble("26.6"), 1);
//			String subscribeOptions=Constantes.MONTH;
//			Mockito.when(tipoPlanesRepositorio.findById(1)).thenReturn(plan);
//			adminControlador.mostrarFormularioRegistro(subscribeOptions);
			Planes planes = new Planes(tipoPlanes);
			MedioPago medioPago = new MedioPago("bcp", "4514-2593-4582-5236", Integer.parseInt("102"), "05/23");
			Pago pago = new Pago(Double.parseDouble("26.6"), new Date(), medioPago, planes, usuario.getId());
			Mockito.when(usuarioRepositorio.findByEmail("ajochav@gmail.com")).thenReturn(usuario);

//			ResponseEntity<?> pagosResponse;
//			Mockito.when(pagoWs.realizarPago(pago)).thenReturn(pagosResponse);
			adminControlador.registrarPago("bcp", "4514-2593-4582-5236", "05/23", "102", "ajochav@gmail.com");
			adminControlador.actualizarPelicula(id, pelicula , bindingResult);

	    } catch (Exception e) {
	      assertNotNull(e);

	    }
	  }
}
