package com.tienda.backend.repository;

import com.tienda.backend.entity.GastoGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface GastoGeneralRepository
        extends JpaRepository<GastoGeneral, Long> {
                List<GastoGeneral> findByFecha(LocalDate fecha);

                List<GastoGeneral> findByFechaBetween(LocalDate desde, LocalDate hasta);
}