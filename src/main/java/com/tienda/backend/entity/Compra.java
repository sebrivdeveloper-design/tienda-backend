package com.tienda.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    private Double totalPagado;

    private Double costoUnitario;

    private Double precioSugerido;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
}