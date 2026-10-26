package com.example.demo.dtos;

import java.time.LocalDate;
import java.util.List;

public record CandidatoRespostaDTO (
        Long id,
        String nome,
        String foto,
        LocalDate nascimento,
        String escolaridade,
        Long idUsuario,
        List<Long> areasDeInteresse
){
}
