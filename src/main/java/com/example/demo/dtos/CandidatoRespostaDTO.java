package com.example.demo.dtos;

import java.time.LocalDate;

public record CandidatoRespostaDTO (
        Long id,
        String nome,
        String foto,
        LocalDate nascimento,
        String escolaridade,
        Long idUsuario,
        Long areaDaVaga
){
}
