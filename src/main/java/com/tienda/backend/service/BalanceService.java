package com.tienda.backend.service;

import com.tienda.backend.dto.BalanceDTO;
import com.tienda.backend.entity.GastoGeneral;
import com.tienda.backend.entity.IngresoDiario;
import com.tienda.backend.repository.GastoGeneralRepository;
import com.tienda.backend.repository.IngresoDiarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Balance = Ingresos - Gastos.
 * Costo de Productos (antes "Compras") ya NO participa en este calculo:
 * la factura completa de la compra ya se registra como Gasto, y sumar
 * tambien el costo de productos producia doble contabilizacion.
 */
@Service
public class BalanceService {

    private final IngresoDiarioRepository ingresoRepository;
    private final GastoGeneralRepository gastoRepository;

    public BalanceService(
            IngresoDiarioRepository ingresoRepository,
            GastoGeneralRepository gastoRepository
    ) {
        this.ingresoRepository = ingresoRepository;
        this.gastoRepository = gastoRepository;
    }

    public BalanceDTO obtenerBalance(LocalDate fecha) {

        Double ingresos = ingresoRepository
                .findByFecha(fecha)
                .map(IngresoDiario::getTotalIngresos)
                .orElse(0.0);

        List<GastoGeneral> gastos =
                gastoRepository.findByFecha(fecha);

        Double totalGastos = gastos.stream()
                .mapToDouble(GastoGeneral::getValor)
                .sum();

        Double balance =
                ingresos - totalGastos;

        return BalanceDTO.builder()
                .ingresos(ingresos)
                .gastos(totalGastos)
                .balance(balance)
                .build();
    }
}