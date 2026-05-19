package com.tienda.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "gastos_generales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GastoGeneral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private String descripcion;

    private Double valor;

    private LocalDate fecha;
}