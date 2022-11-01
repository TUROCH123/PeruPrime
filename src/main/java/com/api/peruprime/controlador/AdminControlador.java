package com.api.peruprime.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.api.peruprime.modelo.Genero;
import com.api.peruprime.modelo.Pelicula;
import com.api.peruprime.repositorio.GeneroRepositorio;
import com.api.peruprime.repositorio.PeliculaRepositorio;
import com.api.peruprime.servicio.AlmacenServicioImpl;
import com.api.peruprime.util.Constantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
	private static final Logger logger = LoggerFactory.getLogger(AdminControlador.class);
	@Autowired
	private PeliculaRepositorio peliculaRepositorio;

	@Autowired
	private GeneroRepositorio generoRepositorio;

	@Autowired
	private AlmacenServicioImpl servicio;

	Integer ids;
	
	@GetMapping("")
	public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "titulo", size = 5) Pageable pageable) {
		Page<Pelicula> peliculas = peliculaRepositorio.findAll(pageable);
		return new ModelAndView("admin/index").addObject("peliculas", peliculas);
	}

	@GetMapping("/peliculas/nuevo")
	public ModelAndView mostrarFormularioDeNuevaPelicula() {
		List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
		logger.info(Constantes.MENSAJE2,"[mostrarFormularioDeNuevaPelicula][generos] ", generos.size());
		return new ModelAndView("admin/nueva-pelicula")
				.addObject("pelicula", new Pelicula())
				.addObject("generos",generos);
	}
	
	@PostMapping("/peliculas/nuevo")
	public ModelAndView registrarPelicula(@Validated Pelicula pelicula,BindingResult bindingResult) {
		
		logger.info(Constantes.MENSAJE2,"[registrarPelicula][pelicula][getRutaPortada] ", pelicula.getRutaPortada());
		logger.info(Constantes.MENSAJE2,"[registrarPelicula][pelicula][getTitulo] ", pelicula.getTitulo());
		logger.info(Constantes.MENSAJE2,"[registrarPelicula][pelicula][getFechaEstreno] ", pelicula.getFechaEstreno());
		logger.info(Constantes.MENSAJE2,"[registrarPelicula][pelicula][Portada] ", pelicula.getPortada());
		logger.info(Constantes.MENSAJE2,"[registrarPelicula][pelicula][TrailerId] ", pelicula.getYoutubeTrailerId());
		if(bindingResult.hasErrors() || pelicula.getPortada().isEmpty()) {
			if(pelicula.getPortada().isEmpty()) {
				bindingResult.rejectValue("portada","MultipartNotEmpty");
			}
			
			List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
			return new ModelAndView("/admin/nueva-pelicula")
					.addObject("pelicula",pelicula)
					.addObject("generos",generos);
		}
		
		String rutaPortada = servicio.almacenarArchivo(pelicula.getPortada());
		pelicula.setRutaPortada(rutaPortada);
		
		peliculaRepositorio.save(pelicula);
		return new ModelAndView("redirect:/admin");
	}
	
	@GetMapping("/peliculas/{id}/editar")
	public ModelAndView mostrarFormilarioDeEditarPelicula(@PathVariable Integer id) {
		logger.info(Constantes.MENSAJE2,"[mostrarFormilarioDeEditarPelicula][editar][id] ", id);
		Pelicula pelicula = peliculaRepositorio.getOne(id);
		List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
		logger.info(Constantes.MENSAJE2,"[mostrarFormilarioDeEditarPelicula][genero] ", generos.size());
		ids = pelicula.getId();
		logger.info(Constantes.MENSAJE2,"[mostrarFormilarioDeEditarPelicula][pelicula][id] ", ids);
		return new ModelAndView("admin/editar-pelicula")
				.addObject("pelicula",pelicula).addObject("id",pelicula.getId())
				.addObject("generos",generos);
	}
	
	@PostMapping("/peliculas/{id}/editar")
	public ModelAndView actualizarPelicula(@PathVariable String id,@Validated Pelicula pelicula,BindingResult bindingResult) {
		logger.info(Constantes.MENSAJE2,"[actualizarPelicula][editar][id] ", id);
		logger.info(Constantes.MENSAJE2,"[actualizarPelicula][pelicula][getRutaPortada] ", pelicula.getRutaPortada());
		logger.info(Constantes.MENSAJE2,"[actualizarPelicula][pelicula][getTitulo] ", pelicula.getTitulo());
		logger.info(Constantes.MENSAJE2,"[actualizarPelicula][pelicula][getFechaEstreno] ", pelicula.getFechaEstreno());
		logger.info(Constantes.MENSAJE2,"[actualizarPelicula][pelicula][Portada] ", pelicula.getPortada());
		logger.info(Constantes.MENSAJE2,"[actualizarPelicula][pelicula][TrailerId] ", pelicula.getYoutubeTrailerId());
//		if(bindingResult.hasErrors()) {
//			logger.info(Constantes.MENSAJE2,"[actualizarPelicula][bindingResult.hasErrors()] ", bindingResult.hasErrors());
//			List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
//			return new ModelAndView("admin/editar-pelicula")
//					.addObject("pelicula",pelicula)
//					.addObject("generos",generos);
//		}
		logger.info(Constantes.MENSAJE2,"[actualizarPelicula][editar][ids] ", ids);
		Pelicula peliculaDB = peliculaRepositorio.getOne(ids);
		peliculaDB.setTitulo(pelicula.getTitulo());
		peliculaDB.setSinopsis(pelicula.getSinopsis());
		peliculaDB.setFechaEstreno(pelicula.getFechaEstreno());
		peliculaDB.setYoutubeTrailerId(pelicula.getYoutubeTrailerId());
		peliculaDB.setGeneros(pelicula.getGeneros());
		peliculaDB.setProveedor(pelicula.getProveedor());
		if(!pelicula.getPortada().isEmpty()) {
			servicio.eliminarArchivo(peliculaDB.getRutaPortada());
			String rutaPortada = servicio.almacenarArchivo(pelicula.getPortada());
			peliculaDB.setRutaPortada(rutaPortada);
		}
		
		peliculaRepositorio.save(peliculaDB);
		return new ModelAndView("redirect:/admin");
	}
	
	@PostMapping("/peliculas/{id}/eliminar")
	public String eliminarPelicula(@PathVariable Integer id) {
		logger.info(Constantes.MENSAJE2,"[eliminarPelicula][id] ", id);
		Pelicula pelicula = peliculaRepositorio.getOne(id);
		peliculaRepositorio.delete(pelicula);
		servicio.eliminarArchivo(pelicula.getRutaPortada());
		
		return "redirect:/admin";
	}
}