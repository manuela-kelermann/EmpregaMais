package com.example.demo.service;

import com.example.demo.dtos.CepRespostaDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class CepService {

    private final RestClient restClient;

    public CepService(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://brasilapi.com.br")
                .build();
    }

    public CepRespostaDTO buscarLocalizacao(String cep) {

        if (cep == null || !cep.matches("\\d{8}")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O CEP deve conter exatamente 8 dígitos."
            );
        }

        try {
            return restClient.get()
                    .uri("/cep/v1/{cep}", cep)
                    .retrieve()
                    .body(CepRespostaDTO.class);

        } catch (RestClientException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Não foi possível consultar o CEP na BrasilAPI.",
                    exception
            );
        }
    }
}