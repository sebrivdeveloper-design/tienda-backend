package com.tienda.backend.controller;

import com.tienda.backend.entity.GastoGeneral;
import com.tienda.backend.service.GastoGeneralService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos")
public class GastoGeneralController {

    private final GastoGeneralService gastoService;

    public GastoGeneralController(GastoGeneralService gastoService) {
        this.gastoService = gastoService;
    }

    @PostMapping
    public GastoGeneral registrarGasto(
            @RequestBody GastoGeneral gasto
    ) {
        return gastoService.registrarGasto(gasto);
    }

    @GetMapping
    public List<GastoGeneral> listarGastos() {
        return gastoService.listarGastos();
    }
}