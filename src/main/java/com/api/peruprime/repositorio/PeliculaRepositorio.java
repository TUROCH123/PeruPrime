package com.api.peruprime.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.peruprime.modelo.Pelicula;

public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer>{

}
