package com.tienda.backend.service;

import com.tienda.backend.dto.LoginRequestDTO;
import com.tienda.backend.dto.LoginResponseDTO;
import com.tienda.backend.entity.Usuario;
import com.tienda.backend.security.JwtService;
import com.tienda.backend.security.UsuarioPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {

        if (dto.getUsername() == null || dto.getUsername().isBlank()
                || dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("El usuario y la contraseña son obligatorios.");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        UsuarioPrincipal principal = (UsuarioPrincipal) authentication.getPrincipal();
        Usuario usuario = principal.getUsuario();

        String token = jwtService.generarToken(principal);

        return LoginResponseDTO.builder()
                .token(token)
                .tipo("Bearer")
                .expiraEn(jwtService.getExpiracionMs())
                .nombre(usuario.getNombre())
                .rol(usuario.getRol().name())
                .build();
    }
}