package com.example.demo.controller;

import com.example.demo.dtos.VagaRespostaDTO;
import com.example.demo.dtos.VagaSalvarDTO;
import com.example.demo.model.Permissoes;
import com.example.demo.service.VagaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
@RequiredArgsConstructor
public class VagaController {

    private final VagaService vagaService;



    @PostMapping
    public ResponseEntity<VagaRespostaDTO> criar(@RequestBody @Valid  VagaSalvarDTO dto){
    VagaRespostaDTO salvo = vagaService.criar(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }




    @PreAuthorize(Permissoes.VAGAS_VISUALIZAR)
    @GetMapping
    public ResponseEntity<List<VagaRespostaDTO>> listarTodos(){
        List<VagaRespostaDTO> vagas = vagaService.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(vagas);

    }

}
