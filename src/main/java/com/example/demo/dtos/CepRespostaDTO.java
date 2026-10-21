package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CepRespostaDTO(

        String cep,

        @JsonProperty("state")
        String estado,

        @JsonProperty("city")
        String cidade,

        @JsonProperty("neighborhood")
        String bairro,

        @JsonProperty("street")
        String rua

) {
}