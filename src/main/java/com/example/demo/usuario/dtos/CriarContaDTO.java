package com.example.demo.usuario.dtos;

public record CriarContaDTO(
        String nome,
        String email,
        String senha,
        Long idPerfil

) {
}
