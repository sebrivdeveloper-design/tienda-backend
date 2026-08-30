package com.tienda.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class SaldoInicialResponseDTO {

    private Double valor;

    private LocalDate fecha;

    private LocalDateTime fechaRegistro;
}