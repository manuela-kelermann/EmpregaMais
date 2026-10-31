package com.example.demo.controller;

import com.example.demo.dtos.AreasDasVagasDTO;
import com.example.demo.service.AreasDasVagasService;
import com.example.demo.usuario.permissoes.Permissoes;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreasDasVagasController {
    private final AreasDasVagasService service;

    public AreasDasVagasController(AreasDasVagasService service) {
        this.service = service;
    }

    @PreAuthorize(Permissoes.AREA_DA_VAGA_LISTAR)
    @GetMapping
    public ResponseEntity<List<AreasDasVagasDTO>> listar() {
        List<AreasDasVagasDTO> areas = service.listar();
        return ResponseEntity.status(HttpStatus.OK).body(areas);
    }

    @PreAuthorize(Permissoes.AREA_DA_VAGA_LISTAR)
    @GetMapping("/{id}")
    public ResponseEntity<AreasDasVagasDTO> buscarPorId(@PathVariable Long id) {
        AreasDasVagasDTO busca = service.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(busca);
    }

    @PreAuthorize(Permissoes.AREA_DA_VAGA_LISTAR)
    @GetMapping("/pesquisa")
    public ResponseEntity<List<AreasDasVagasDTO>> pesquisar(@RequestParam String nome) {
        List<AreasDasVagasDTO> pesquisa = service.pesquisarPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(pesquisa);
    }

    @PreAuthorize(Permissoes.AREA_DA_VAGA_SALVAR)
    @PostMapping
    public ResponseEntity<AreasDasVagasDTO> criar(@Valid @RequestBody AreasDasVagasDTO dto) {
        AreasDasVagasDTO cria = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cria);
    }

    @PreAuthorize(Permissoes.AREA_DA_VAGA_SALVAR)
    @PutMapping("/{id}")
    public ResponseEntity<AreasDasVagasDTO> atualizar(@PathVariable("id") Long id, @RequestBody AreasDasVagasDTO dto) {
        AreasDasVagasDTO atualiza = service.atualizar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(atualiza);
    }

    @PreAuthorize(Permissoes.AREA_DA_VAGA_EXCLUIR)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
