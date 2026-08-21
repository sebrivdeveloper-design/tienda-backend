package com.tienda.backend.service;

import java.time.Clock;
import com.tienda.backend.entity.IngresoDiario;
import com.tienda.backend.repository.IngresoDiarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IngresoDiarioService {

    private final IngresoDiarioRepository ingresoRepository;
    private final Clock clock;

    public IngresoDiarioService(IngresoDiarioRepository ingresoRepository, Clock clock) {
        this.ingresoRepository = ingresoRepository;
        this.clock = clock;
    }

    public IngresoDiario registrarIngreso(Double totalIngresos) {

        LocalDate hoy = LocalDate.now(clock);

        IngresoDiario ingreso = ingresoRepository
                .findByFecha(hoy)
                .orElse(
                        IngresoDiario.builder()
                                .fecha(hoy)
                                .totalIngresos(0.0)
                                .build()
                );

        ingreso.setTotalIngresos(
                ingreso.getTotalIngresos() + totalIngresos
        );

        return ingresoRepository.save(ingreso);
    }

    public List<IngresoDiario> listarIngresos() {
        return ingresoRepository.findAll();
    }
}