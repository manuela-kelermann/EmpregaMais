package com.example.demo.controller;

import com.example.demo.dtos.CepRespostaDTO;
import com.example.demo.service.CepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cep/v1")
@RequiredArgsConstructor
public class CepController {

    private final CepService cepService;

    @GetMapping("/{cep}")
    public ResponseEntity<CepRespostaDTO> buscarLocalizacao(@PathVariable("cep") String cep) {

        CepRespostaDTO endereco = cepService.buscarLocalizacao(cep);

        return ResponseEntity.ok(endereco);
    }
}