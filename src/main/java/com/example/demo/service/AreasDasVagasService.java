package com.example.demo.service;

import com.example.demo.dtos.AreasDasVagasDTO;
import com.example.demo.model.AreasDasVagas;
import com.example.demo.repository.AreasDasVagasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreasDasVagasService {

    private final AreasDasVagasRepository repository;

    public AreasDasVagasService(AreasDasVagasRepository repository){
        this.repository = repository;
    }

    public List<AreasDasVagas> listar() {
        return repository.findAll();
    }

    //Digita nome inteiro
    public AreasDasVagas buscarPorNome(String nome) {
        return repository.findByNomeAreaIgnoreCase(nome)
                .orElseThrow(() -> new RuntimeException("Área não encontrada: " + nome));
    }

    public AreasDasVagas buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área não encontrada"));
    }

    //Digita nome de forma parcial
    public List<AreasDasVagas> pesquisarPorNome(String nome) {
        return repository.findByNomeAreaContainingIgnoreCase(nome);
    }

    public AreasDasVagas criar(AreasDasVagasDTO dto) {
        AreasDasVagas area = new AreasDasVagas();
        area.setNomeArea(dto.nomeArea());
        return repository.save(area);
    }

    public AreasDasVagas atualizar(Long id, AreasDasVagasDTO dto) {
        AreasDasVagas area = buscarPorId(id);
        area.setNomeArea(dto.nomeArea());
        return repository.save(area);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
