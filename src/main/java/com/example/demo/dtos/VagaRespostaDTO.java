package com.example.demo.dtos;

import com.example.demo.model.ModalidadeEnum;
import jakarta.persistence.Column;

public record VagaRespostaDTO(
        Long id,
        String titulo,
        String localidade,
        ModalidadeEnum modalidadeEnum,
        Double pisoSalario,
        Double tetoSalario,
        String descricao,
        String escolaridade,
        String responsabilidadeAtribuicoes,
        String requisitosTecnicos,
        String beneficios,
        String candidatarSe,
        Long areaDaVaga
) { }
