package com.tienda.backend.controller;

import com.tienda.backend.dto.SaldoInicialRequestDTO;
import com.tienda.backend.dto.SaldoInicialResponseDTO;
import com.tienda.backend.service.SaldoInicialService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saldo-inicial")
public class SaldoInicialController {

    private final SaldoInicialService saldoInicialService;

    public SaldoInicialController(SaldoInicialService saldoInicialService) {
        this.saldoInicialService = saldoInicialService;
    }

    @GetMapping
    public SaldoInicialResponseDTO obtener() {
        return saldoInicialService.obtener();
    }

    @PostMapping
    public SaldoInicialResponseDTO establecer(@RequestBody SaldoInicialRequestDTO dto) {
        return saldoInicialService.establecer(dto);
    }
}