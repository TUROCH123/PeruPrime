package com.api.peruprime.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.api.peruprime.modelo.MedioPago;

public interface MedioPagoRepositorio extends JpaRepository<MedioPago, Integer> {
	@Query(value = "select max(medio_pagos_id) from medio_pagos", nativeQuery = true)
	Integer verficarMedioPago();
}