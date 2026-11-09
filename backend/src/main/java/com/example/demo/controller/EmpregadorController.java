package com.example.demo.controller;

import com.example.demo.dtos.EmpregadorRespostaDTO;
import com.example.demo.dtos.EmpregadorSalvarDTO;
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
@RequestMapping("/empregador")
@RequiredArgsConstructor
public class EmpregadorController {

    private final EmpregadorService empregadorService;

    @PreAuthorize(Permissoes.EMPREGADOR_EDITAR)
    @PostMapping
    public ResponseEntity<EmpregadorRespostaDTO> cadastrar( @RequestBody @Valid EmpregadorSalvarDTO dto, @AuthenticationPrincipal Usuario usuario) {

        EmpregadorRespostaDTO salvo = empregadorService.cadastrar(dto, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PreAuthorize(Permissoes.EMPREGADOR_EDITAR)
    @PutMapping("/{id}")
    public ResponseEntity<EmpregadorRespostaDTO> atualizar(@PathVariable("id") Long id, @RequestBody @Valid EmpregadorSalvarDTO dto) {

        EmpregadorRespostaDTO atualizado = empregadorService.atualizar(id,dto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    @PreAuthorize(Permissoes.EMPREGADOR_VISUALIZAR)
    @GetMapping("/{id}")
    public ResponseEntity<EmpregadorRespostaDTO> buscarPorId(@PathVariable("id") Long id){
        EmpregadorRespostaDTO empregador = empregadorService.buscarPorId(id);
        return  ResponseEntity.status(HttpStatus.OK).body(empregador);
    }

    @PreAuthorize(Permissoes.EMPREGADOR_VISUALIZAR_TODOS)
    @GetMapping
    public ResponseEntity<List<EmpregadorRespostaDTO>> listarTodos(){
    List<EmpregadorRespostaDTO> empregadores = empregadorService.listarTodos();
    return ResponseEntity.status(HttpStatus.OK).body(empregadores);
    }






}
