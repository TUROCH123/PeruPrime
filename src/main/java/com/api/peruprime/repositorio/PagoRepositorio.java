package com.api.peruprime.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.api.peruprime.modelo.Pago;

@Repository
public interface PagoRepositorio extends JpaRepository<Pago, Integer> {
	@Query(value = "select max(pagos_id) from pagos", nativeQuery = true)
	Integer verficarPagos();
}