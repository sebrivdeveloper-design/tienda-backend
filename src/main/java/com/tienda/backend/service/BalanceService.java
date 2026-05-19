package com.tienda.backend.service;

import com.tienda.backend.dto.BalanceDTO;
import com.tienda.backend.entity.Compra;
import com.tienda.backend.entity.GastoGeneral;
import com.tienda.backend.entity.IngresoDiario;
import com.tienda.backend.repository.CompraRepository;
import com.tienda.backend.repository.GastoGeneralRepository;
import com.tienda.backend.repository.IngresoDiarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BalanceService {

    private final IngresoDiarioRepository ingresoRepository;
    private final CompraRepository compraRepository;
    private final GastoGeneralRepository gastoRepository;

    public BalanceService(
            IngresoDiarioRepository ingresoRepository,
            CompraRepository compraRepository,
            GastoGeneralRepository gastoRepository
    ) {
        this.ingresoRepository = ingresoRepository;
        this.compraRepository = compraRepository;
        this.gastoRepository = gastoRepository;
    }

    public BalanceDTO obtenerBalance(LocalDate fecha) {

        Double ingresos = ingresoRepository
                .findByFecha(fecha)
                .map(IngresoDiario::getTotalIngresos)
                .orElse(0.0);

        List<Compra> compras = compraRepository.findByFecha(fecha);

        Double totalCompras = compras.stream()
                .mapToDouble(Compra::getTotalPagado)
                .sum();

        List<GastoGeneral> gastos =
                gastoRepository.findByFecha(fecha);

        Double totalGastos = gastos.stream()
                .mapToDouble(GastoGeneral::getValor)
                .sum();

        Double balance =
                ingresos - totalCompras - totalGastos;

        return BalanceDTO.builder()
                .ingresos(ingresos)
                .compras(totalCompras)
                .gastos(totalGastos)
                .balance(balance)
                .build();
    }
}