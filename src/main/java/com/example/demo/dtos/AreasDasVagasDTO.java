package com.example.demo.dtos;

import jakarta.validation.constraints.NotBlank;

public record AreasDasVagasDTO(
        @NotBlank(message = "O nome da área é obrigatório") String nomeArea
) {}