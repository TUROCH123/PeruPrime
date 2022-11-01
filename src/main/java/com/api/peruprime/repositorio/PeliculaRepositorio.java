package com.api.peruprime.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.api.peruprime.modelo.Pelicula;

public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer>{

	@Query(value = "SELECT * FROM pelicula p WHERE p.titulo LIKE %:palabraClave% OR p.proveedor LIKE %:palabraClave%", nativeQuery = true)
	public List<Pelicula> findAlls(String palabraClave);
}
