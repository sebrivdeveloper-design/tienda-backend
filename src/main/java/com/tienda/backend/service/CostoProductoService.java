package com.tienda.backend.service;

import com.tienda.backend.dto.CostoProductoRequestDTO;
import com.tienda.backend.entity.CostoProducto;
import com.tienda.backend.entity.Producto;
import com.tienda.backend.repository.CostoProductoRepository;
import com.tienda.backend.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CostoProductoService {

    private final CostoProductoRepository costoProductoRepository;
    private final ProductoRepository productoRepository;

    public CostoProductoService(
            CostoProductoRepository costoProductoRepository,
            ProductoRepository productoRepository
    ) {
        this.costoProductoRepository = costoProductoRepository;
        this.productoRepository = productoRepository;
    }

    public CostoProducto registrarCostoProducto(CostoProductoRequestDTO dto) {

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        double costoUnitario = Math.round(dto.getTotalPagado() / dto.getCantidad() * 100.0) / 100.0;

        double precioSugerido = Math.round(
                costoUnitario +
                (costoUnitario * producto.getPorcentajeGanancia()) / 100);

        CostoProducto costoProducto = CostoProducto.builder()
                .producto(producto)
                .cantidad(dto.getCantidad())
                .totalPagado(dto.getTotalPagado())
                .costoUnitario(costoUnitario)
                .precioSugerido(precioSugerido)
                .fecha(LocalDate.now())
                .build();

        return costoProductoRepository.save(costoProducto);
    }

    public List<CostoProducto> listarCostosProductos() {
        return costoProductoRepository.findAll();
    }
}