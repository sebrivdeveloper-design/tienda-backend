package com.tienda.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BalanceDTO {

    private Double ingresos;

    private Double compras;

    private Double gastos;

    private Double balance;
}