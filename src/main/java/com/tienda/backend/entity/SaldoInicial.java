package com.tienda.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "saldo_inicial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaldoInicial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    private LocalDate fecha;

    private LocalDateTime fechaRegistro;
}