package com.tienda.backend.repository;

import com.tienda.backend.entity.IngresoDiario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface IngresoDiarioRepository
        extends JpaRepository<IngresoDiario, Long> {

    Optional<IngresoDiario> findByFecha(LocalDate fecha);

    List<IngresoDiario> findByFechaBetween(LocalDate desde, LocalDate hasta);
}