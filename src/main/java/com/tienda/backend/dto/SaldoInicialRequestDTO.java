package com.tienda.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SaldoInicialRequestDTO {

    private Double valor;

    // Opcional: si no la envian, se usa la fecha de hoy.
    private LocalDate fecha;
}