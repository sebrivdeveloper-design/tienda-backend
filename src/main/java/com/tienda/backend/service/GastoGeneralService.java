package com.tienda.backend.service;

import com.tienda.backend.entity.GastoGeneral;
import com.tienda.backend.repository.GastoGeneralRepository;
import org.springframework.stereotype.Service;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

@Service
public class GastoGeneralService {

    private final GastoGeneralRepository gastoRepository;
    private final Clock clock;

    public GastoGeneralService(GastoGeneralRepository gastoRepository, Clock clock) {
        this.gastoRepository = gastoRepository;
        this.clock = clock;
    }

    public GastoGeneral registrarGasto(GastoGeneral gasto) {

        gasto.setFecha(LocalDate.now(clock));

        return gastoRepository.save(gasto);
    }

    public List<GastoGeneral> listarGastos() {
        return gastoRepository.findAll();
    }
}