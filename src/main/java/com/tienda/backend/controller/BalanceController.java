package com.tienda.backend.controller;

import com.tienda.backend.dto.BalanceDTO;
import com.tienda.backend.service.BalanceService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping
    public BalanceDTO obtenerBalance(
            @RequestParam String fecha
    ) {

        LocalDate fechaParseada =
                LocalDate.parse(fecha);

        return balanceService.obtenerBalance(fechaParseada);
    }
}