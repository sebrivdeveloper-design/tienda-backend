package com.tienda.backend.service;

import com.tienda.backend.entity.Producto;
import com.tienda.backend.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    /**
     * Porcentaje de ganancia que se asigna automáticamente cuando el
     * cliente no envía el campo, o lo envía en null. Vive en el backend
     * a propósito, para que ningún cliente (frontend, Postman, etc.)
     * pueda alterarlo enviando un valor distinto por accidente u omisión.
     */
    private static final double PORCENTAJE_GANANCIA_POR_DEFECTO = 25.0;

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto guardarProducto(Producto producto) {

        if (producto.getPorcentajeGanancia() == null) {
            producto.setPorcentajeGanancia(PORCENTAJE_GANANCIA_POR_DEFECTO);
        }

        return productoRepository.save(producto);
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {

        Producto producto = obtenerProductoPorId(id);

        producto.setNombre(productoActualizado.getNombre());

        producto.setPorcentajeGanancia(
                productoActualizado.getPorcentajeGanancia() != null
                        ? productoActualizado.getPorcentajeGanancia()
                        : PORCENTAJE_GANANCIA_POR_DEFECTO
        );

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}