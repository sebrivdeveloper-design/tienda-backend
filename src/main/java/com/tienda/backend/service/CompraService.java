package com.tienda.backend.service;

import com.tienda.backend.dto.CompraRequestDTO;
import com.tienda.backend.entity.Compra;
import com.tienda.backend.entity.Producto;
import com.tienda.backend.repository.CompraRepository;
import com.tienda.backend.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;

    public CompraService(
            CompraRepository compraRepository,
            ProductoRepository productoRepository
    ) {
        this.compraRepository = compraRepository;
        this.productoRepository = productoRepository;
    }

    public Compra registrarCompra(CompraRequestDTO dto) {

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        double costoUnitario = Math.round(dto.getTotalPagado() / dto.getCantidad() * 100.0) / 100.0;

        double precioSugerido = Math.round(
                costoUnitario +
                (costoUnitario * producto.getPorcentajeGanancia()) / 100);

        Compra compra = Compra.builder()
                .producto(producto)
                .cantidad(dto.getCantidad())
                .totalPagado(dto.getTotalPagado())
                .costoUnitario(costoUnitario)
                .precioSugerido(precioSugerido)
                .fecha(LocalDate.now())
                .build();

        return compraRepository.save(compra);
    }

    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }
}