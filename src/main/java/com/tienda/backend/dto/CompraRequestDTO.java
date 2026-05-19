package com.tienda.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraRequestDTO {

    private Long productoId;

    private Integer cantidad;

    private Double totalPagado;
}