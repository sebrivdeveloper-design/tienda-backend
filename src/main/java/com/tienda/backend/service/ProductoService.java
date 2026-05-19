package com.tienda.backend.service;

import com.tienda.backend.entity.Producto;
import com.tienda.backend.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto guardarProducto(Producto producto) {
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
        producto.setPorcentajeGanancia(productoActualizado.getPorcentajeGanancia());

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}