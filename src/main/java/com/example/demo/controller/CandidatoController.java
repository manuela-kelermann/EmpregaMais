package com.example.demo.controller;

import com.example.demo.dtos.CandidatoRespostaDTO;
import com.example.demo.dtos.CandidatoSalvarDTO;
import com.example.demo.dtos.EmpregadorRespostaDTO;
import com.example.demo.dtos.EmpregadorSalvarDTO;
import com.example.demo.repository.CandidatoRepository;
import com.example.demo.service.CandidatoService;
import com.example.demo.service.EmpregadorService;
import com.example.demo.usuario.Usuario;
import com.example.demo.usuario.permissoes.Permissoes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
@RequiredArgsConstructor
public class CandidatoController {

    private final CandidatoService candidatoService;

    @PreAuthorize(Permissoes.CANDIDATO_EDITAR)
    @PostMapping
    public ResponseEntity<CandidatoRespostaDTO> cadastrar(@RequestBody @Valid CandidatoSalvarDTO dto, @AuthenticationPrincipal Usuario usuario){
        CandidatoRespostaDTO salvo = candidatoService.cadastrar(dto,usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PreAuthorize(Permissoes.CANDIDATO_EDITAR)
    @PutMapping("/{id}")
    public ResponseEntity<CandidatoRespostaDTO> atualizar(@PathVariable("id") Long id, @RequestBody @Valid CandidatoSalvarDTO dto) {

        CandidatoRespostaDTO atualizado = candidatoService.atualizar(id,dto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    @PreAuthorize(Permissoes.CANDIDATO_VISUALIZAR)
    @GetMapping("/{id}")
    public ResponseEntity<CandidatoRespostaDTO> buscarPorId(@PathVariable("id") Long id){
        CandidatoRespostaDTO empregador = candidatoService.buscarPorId(id);
        return  ResponseEntity.status(HttpStatus.OK).body(empregador);
    }

    @PreAuthorize(Permissoes.CANDIDATO_VISUALIZAR_TODOS)
    @GetMapping
    public ResponseEntity<List<CandidatoRespostaDTO>> listarTodos(){
        List<CandidatoRespostaDTO> empregadores = candidatoService.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(empregadores);
    }


}
