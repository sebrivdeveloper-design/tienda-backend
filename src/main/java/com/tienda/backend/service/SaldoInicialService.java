package com.tienda.backend.service;

import com.tienda.backend.dto.SaldoInicialRequestDTO;
import com.tienda.backend.dto.SaldoInicialResponseDTO;
import com.tienda.backend.entity.SaldoInicial;
import com.tienda.backend.repository.SaldoInicialRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class SaldoInicialService {

    private final SaldoInicialRepository saldoInicialRepository;
    private final Clock clock;

    public SaldoInicialService(SaldoInicialRepository saldoInicialRepository, Clock clock) {
        this.saldoInicialRepository = saldoInicialRepository;
        this.clock = clock;
    }

    public Optional<SaldoInicial> obtenerActivo() {
        return saldoInicialRepository.findFirstByOrderByIdDesc();
    }

    public SaldoInicialResponseDTO obtener() {

        return obtenerActivo()
                .map(s -> new SaldoInicialResponseDTO(s.getValor(), s.getFecha(), s.getFechaRegistro()))
                .orElse(new SaldoInicialResponseDTO(0.0, null, null));
    }

    /**
     * Crea el saldo inicial si no existe, o lo actualiza si ya se
     * habia definido antes (por ejemplo, para corregir un error de
     * digitacion). Siempre hay una unica fila activa.
     */
    public SaldoInicialResponseDTO establecer(SaldoInicialRequestDTO dto) {

        if (dto.getValor() == null) {
            throw new IllegalArgumentException("El valor del saldo inicial es obligatorio.");
        }

        LocalDate fecha = dto.getFecha() != null ? dto.getFecha() : LocalDate.now(clock);

        SaldoInicial saldoInicial = obtenerActivo().orElseGet(SaldoInicial::new);

        saldoInicial.setValor(dto.getValor());
        saldoInicial.setFecha(fecha);
        saldoInicial.setFechaRegistro(java.time.LocalDateTime.now(clock));

        SaldoInicial guardado = saldoInicialRepository.save(saldoInicial);

        return new SaldoInicialResponseDTO(guardado.getValor(), guardado.getFecha(), guardado.getFechaRegistro());
    }
}