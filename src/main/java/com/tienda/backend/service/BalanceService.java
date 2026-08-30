package com.tienda.backend.service;

import com.tienda.backend.dto.BalanceDTO;
import com.tienda.backend.entity.GastoGeneral;
import com.tienda.backend.entity.IngresoDiario;
import com.tienda.backend.entity.SaldoInicial;
import com.tienda.backend.repository.GastoGeneralRepository;
import com.tienda.backend.repository.IngresoDiarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**es un saldo acumulado : saldo = saldoInicial + todos los ingresos - todos los
 * gastos, registrados entre la fecha del saldo inicial y la fecha
 * consultada (ambas incluidas). 
 */
@Service
public class BalanceService {

    private final IngresoDiarioRepository ingresoRepository;
    private final GastoGeneralRepository gastoRepository;
    private final SaldoInicialService saldoInicialService;

    public BalanceService(
            IngresoDiarioRepository ingresoRepository,
            GastoGeneralRepository gastoRepository,
            SaldoInicialService saldoInicialService
    ) {
        this.ingresoRepository = ingresoRepository;
        this.gastoRepository = gastoRepository;
        this.saldoInicialService = saldoInicialService;
    }

    public BalanceDTO obtenerBalance(LocalDate fecha) {

        // --- Movimiento DEL DIA consultado (para saber si ese dia especifico subio o bajo) ---
        Double ingresosDia = ingresoRepository
                .findByFecha(fecha)
                .map(IngresoDiario::getTotalIngresos)
                .orElse(0.0);

        List<GastoGeneral> gastosDelDia = gastoRepository.findByFecha(fecha);

        Double gastosDia = gastosDelDia.stream()
                .mapToDouble(GastoGeneral::getValor)
                .sum();

        Double variacionDia = ingresosDia - gastosDia;

        // --- Saldo acumulado hasta la fecha consultada ---
        SaldoInicial saldoInicial = saldoInicialService.obtenerActivo().orElse(null);

        Double valorInicial = saldoInicial != null ? saldoInicial.getValor() : 0.0;
        LocalDate fechaBase = saldoInicial != null ? saldoInicial.getFecha() : LocalDate.MIN;

        Double saldo = valorInicial;

        // Si la fecha consultada es anterior al saldo inicial, no tiene
        // sentido acumular nada todavia (el saldo inicial aun no aplicaba).
        if (!fecha.isBefore(fechaBase)) {

            Double totalIngresosAcumulados = ingresoRepository
                    .findByFechaBetween(fechaBase, fecha)
                    .stream()
                    .mapToDouble(IngresoDiario::getTotalIngresos)
                    .sum();

            Double totalGastosAcumulados = gastoRepository
                    .findByFechaBetween(fechaBase, fecha)
                    .stream()
                    .mapToDouble(GastoGeneral::getValor)
                    .sum();

            saldo = valorInicial + totalIngresosAcumulados - totalGastosAcumulados;
        }

        return BalanceDTO.builder()
                .ingresos(ingresosDia)
                .gastos(gastosDia)
                .variacionDia(variacionDia)
                .saldo(saldo)
                .build();
    }
}