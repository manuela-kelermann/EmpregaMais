package com.example.demo.dtos;

import com.example.demo.model.ModalidadeEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record VagaSalvarDTO(

        @NotBlank(message = "Titulo da vaga não pode ser vazio!")
        long id,

        @NotBlank(message = "Localidade da vaga não pode ser vazia!")
        String localidade,

        @NotBlank(message = "Modalidade da vaga não pode ser vazia!")
        ModalidadeEnum modalidadeEnum,

        @NotBlank(message = "Descrição da vaga não pode ser vazia!")
        String descricao,

        @NotBlank(message = "Escolaridade da vaga não pode ser vazia!")
        String escolaridade,

        @NotBlank(message = "As responsabilidades e atribuições da vaga não podem ser vazias!")
        String responsabilidadeAtribuicoes,

        @NotBlank(message = "Requisitos técnicos da vaga não podem ser vazios!")
        String requisitosTecnicos,

        @NotBlank(message = "Beneficios da vaga são obrigatórios!")
        String beneficios,

        @NotBlank(message = "O link de redirecionamento da vaga é obrigatório!")
        String candidatarSe

) {
}
