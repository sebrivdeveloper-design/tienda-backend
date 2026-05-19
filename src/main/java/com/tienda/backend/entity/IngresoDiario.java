package com.tienda.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "ingresos_diarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngresoDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalIngresos;

    private LocalDate fecha;
}