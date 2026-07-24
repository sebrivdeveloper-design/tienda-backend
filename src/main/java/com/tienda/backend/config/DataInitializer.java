package com.tienda.backend.config;

import com.tienda.backend.entity.Rol;
import com.tienda.backend.entity.Usuario;
import com.tienda.backend.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private static final String USERNAME_ADMIN = "JaniaPaola";
    private static final String PASSWORD_ADMIN = "JaniaPaola1953";
    private static final String NOMBRE_ADMIN = "Administrador";

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (usuarioRepository.count() > 0) {
            return;
        }

        Usuario admin = Usuario.builder()
                .nombre(NOMBRE_ADMIN)
                .username(USERNAME_ADMIN)
                .password(passwordEncoder.encode(PASSWORD_ADMIN))
                .rol(Rol.ADMIN)
                .activo(true)
                .build();

        usuarioRepository.save(admin);

        log.info("Usuario administrador creado automáticamente (username: '{}').", USERNAME_ADMIN);
    }
}