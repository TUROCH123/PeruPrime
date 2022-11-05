package com.api.peruprime.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.peruprime.modelo.TipoPlanes;

public interface TipoPlanesRepositorio extends JpaRepository<TipoPlanes, Integer> {

}