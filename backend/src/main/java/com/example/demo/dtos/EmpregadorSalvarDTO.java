package com.example.demo.dtos;

import jakarta.validation.constraints.NotBlank;

public record EmpregadorSalvarDTO(
        @NotBlank
        String nome,

        String foto,

        @NotBlank
        String descricao,

        @NotBlank
        String cep,

        @NotBlank
        String numero,

        String complemento,

        @NotBlank
        String contato
) {
}
