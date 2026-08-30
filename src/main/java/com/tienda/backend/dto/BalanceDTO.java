package com.tienda.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BalanceDTO {

    
    private Double ingresos;

    private Double gastos;

    
    private Double variacionDia;

    private Double saldo;
}