package com.tienda.backend.controller;

import com.tienda.backend.dto.CompraRequestDTO;
import com.tienda.backend.entity.Compra;
import com.tienda.backend.service.CompraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public Compra registrarCompra(@RequestBody CompraRequestDTO dto) {
        return compraService.registrarCompra(dto);
    }

    @GetMapping
    public List<Compra> listarCompras() {
        return compraService.listarCompras();
    }
}