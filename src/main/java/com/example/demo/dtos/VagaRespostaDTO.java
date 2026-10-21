package com.example.demo.dtos;

import com.example.demo.model.ModalidadeEnum;
import com.example.demo.model.StatusEnum;

public record VagaRespostaDTO(
        Long id,
        String titulo,
        String localidade,
        ModalidadeEnum modalidadeEnum,
        StatusEnum status,
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
