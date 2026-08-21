package com.tienda.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

/**
 * Fuente unica de "que hora/fecha es ahora" para toda la aplicacion.
 *
 * Antes, cada Service llamaba a LocalDate.now() / LocalDateTime.now()
 * directamente. Esos metodos usan la zona horaria POR DEFECTO DE LA JVM,
 * que en Render (contenedor Docker sin TZ configurado) es UTC -- no la
 * de Colombia. Como Bogota es UTC-5, entre las 7:00 p. m. y las 11:59 p. m.
 * hora Colombia, en UTC ya es el dia siguiente, y por eso los registros
 * se guardaban con un dia de mas.
 *
 * La solucion NO depende de configurar el sistema operativo/contenedor
 * (fragil: hay que recordarlo en cada entorno -- local, Docker, Render).
 * En su lugar, se expone un Clock explicito fijado a America/Bogota, que
 * los Services inyectan y usan en vez de los metodos ".now()" estaticos.
 * Es la practica recomendada por java.time para este tipo de casos:
 * codigo explicito, portable entre entornos, y facil de testear
 * (en un test se puede inyectar un Clock fijo con una fecha conocida).
 */
@Configuration
public class RelojConfig {

    public static final ZoneId ZONA_COLOMBIA = ZoneId.of("America/Bogota");

    @Bean
    public Clock clock() {
        return Clock.system(ZONA_COLOMBIA);
    }
}