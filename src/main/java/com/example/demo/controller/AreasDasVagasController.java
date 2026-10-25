package com.example.demo.controller;

import com.example.demo.dtos.AreasDasVagasDTO;
import com.example.demo.model.AreasDasVagas;
import com.example.demo.service.AreasDasVagasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreasDasVagasController {
    private final AreasDasVagasService service;

    public AreasDasVagasController(AreasDasVagasService service) {
        this.service = service;
    }

    @GetMapping
    public List<AreasDasVagas> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public AreasDasVagas buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/nome/{nome}")
    public AreasDasVagas buscarPorNome(@PathVariable String nome) {
        return service.buscarPorNome(nome);
    }

    @GetMapping("/pesquisa")
    public List<AreasDasVagas> pesquisar(@RequestParam String nome) {
        return service.pesquisarPorNome(nome);
    }

    @PostMapping
    public ResponseEntity<AreasDasVagas> criar(@RequestBody AreasDasVagasDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public AreasDasVagas atualizar(@PathVariable Long id, @RequestBody AreasDasVagasDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
