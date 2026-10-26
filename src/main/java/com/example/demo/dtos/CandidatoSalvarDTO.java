package com.example.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CandidatoSalvarDTO(
        @NotBlank
        String nome,

        String foto,

        @NotNull
        LocalDate nascimento,

        @NotBlank
        String escolaridade,

        @NotNull
        Long idUsuario,

        Long areaDaVaga
) {
}
