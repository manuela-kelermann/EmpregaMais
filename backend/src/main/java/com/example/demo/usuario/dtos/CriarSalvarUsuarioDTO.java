package com.example.demo.usuario.dtos;

public record CriarSalvarUsuarioDTO(
        String nome,
        String email,
        String senha,
        Long idPerfil

) {
}
