package com.tienda.backend.repository;

import com.tienda.backend.entity.CostoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface CostoProductoRepository extends JpaRepository<CostoProducto, Long> {
    List<CostoProducto> findByFecha(LocalDate fecha);
}