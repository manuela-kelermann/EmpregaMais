package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;

public record CepRespostaDTO(

        String cep,

       @JsonAlias("state")
       String estado,

       @JsonAlias("city")
        String cidade,

       @JsonAlias("neighborhood")
        String bairro,

       @JsonAlias("street")
        String rua

) {
}