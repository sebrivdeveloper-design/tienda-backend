package com.tienda.backend.repository;

import com.tienda.backend.entity.SaldoInicial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaldoInicialRepository extends JpaRepository<SaldoInicial, Long> {

    // Se espera una unica fila activa; si en algun momento hubiera
    // mas de una (por ejemplo, por un ajuste manual en la base de
    // datos), se toma siempre la mas reciente.
    Optional<SaldoInicial> findFirstByOrderByIdDesc();
}