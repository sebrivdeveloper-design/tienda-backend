package com.tienda.backend.service;

import com.tienda.backend.entity.GastoGeneral;
import com.tienda.backend.repository.GastoGeneralRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GastoGeneralService {

    private final GastoGeneralRepository gastoRepository;

    public GastoGeneralService(GastoGeneralRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }

    public GastoGeneral registrarGasto(GastoGeneral gasto) {

        gasto.setFecha(LocalDate.now());

        return gastoRepository.save(gasto);
    }

    public List<GastoGeneral> listarGastos() {
        return gastoRepository.findAll();
    }
}