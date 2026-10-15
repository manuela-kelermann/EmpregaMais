package com.example.demo.dtos;

import com.example.demo.model.ModalidadeEnum;

public record VagaRespostaDTO(
        long id,
        String titulo,
        String localidade,
        ModalidadeEnum modalidadeEnum,
        String descricao,
        String escolaridade,
        String responsabilidadeAtribuicoes,
        String requisitosTecnicos,
        String beneficios,
        String candidatarSe
) { }
