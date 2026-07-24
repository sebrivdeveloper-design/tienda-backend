package com.tienda.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponseDTO {

    private String token;

    private String tipo;

    private long expiraEn;

    private String nombre;

    private String rol;
}