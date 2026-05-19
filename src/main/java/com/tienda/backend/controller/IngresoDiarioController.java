package com.tienda.backend.controller;

import com.tienda.backend.entity.IngresoDiario;
import com.tienda.backend.service.IngresoDiarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingresos")
public class IngresoDiarioController {

    private final IngresoDiarioService ingresoService;

    public IngresoDiarioController(IngresoDiarioService ingresoService) {
        this.ingresoService = ingresoService;
    }

    @PostMapping
    public IngresoDiario registrarIngreso(
            @RequestParam Double totalIngresos
    ) {
        return ingresoService.registrarIngreso(totalIngresos);
    }

    @GetMapping
    public List<IngresoDiario> listarIngresos() {
        return ingresoService.listarIngresos();
    }
}