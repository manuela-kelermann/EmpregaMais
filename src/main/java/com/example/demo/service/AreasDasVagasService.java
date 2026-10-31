package com.example.demo.service;

import com.example.demo.dtos.AreasDasVagasDTO;
import com.example.demo.model.AreasDasVagas;
import com.example.demo.repository.AreasDasVagasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreasDasVagasService {

    private final AreasDasVagasRepository repository;

    public AreasDasVagasService(AreasDasVagasRepository repository) {
        this.repository = repository;
    }

    private AreasDasVagasDTO paraDTO(AreasDasVagas area) {
        return new AreasDasVagasDTO(area.getNomeArea());
    }

    private AreasDasVagas buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));
    }

    public List<AreasDasVagasDTO> listar() {
        return repository.findAll().stream()
                .map(this::paraDTO)
                .toList();
    }

    public AreasDasVagasDTO buscarPorId(Long id) {
        return paraDTO(buscarEntidade(id));
    }

    // Digita nome de forma parcial
    public List<AreasDasVagasDTO> pesquisarPorNome(String nome) {
        return repository.findByNomeAreaContainingIgnoreCase(nome).stream()
                .map(this::paraDTO)
                .toList();
    }

    public AreasDasVagasDTO criar(AreasDasVagasDTO dto) {
        AreasDasVagas area = new AreasDasVagas();
        area.setNomeArea(dto.nomeArea());
        return paraDTO(repository.save(area));
    }

    public AreasDasVagasDTO atualizar(Long id, AreasDasVagasDTO dto) {
        AreasDasVagas area = buscarEntidade(id);
        area.setNomeArea(dto.nomeArea());
        return paraDTO(repository.save(area));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}