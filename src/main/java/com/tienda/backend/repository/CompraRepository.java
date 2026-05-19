package com.tienda.backend.repository;

import com.tienda.backend.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByFecha(LocalDate fecha);
}