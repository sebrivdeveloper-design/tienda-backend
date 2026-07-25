package com.tienda.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Registra el costo de adquisicion de productos (antes "Compra").
 * Se mantiene @Table(name = "compras") para seguir usando exactamente
 * la misma tabla ya existente en Neon -- no se crea tabla nueva ni se
 * migran registros.
 */
@Entity
@Table(name = "compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostoProducto {

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