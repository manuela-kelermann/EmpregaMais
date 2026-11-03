package com.example.demo.service;

import com.example.demo.dtos.CandidatoRespostaDTO;
import com.example.demo.dtos.CandidatoSalvarDTO;
import com.example.demo.model.AreasDasVagas;
import com.example.demo.model.Candidato;
import com.example.demo.repository.CandidatoRepository;
import com.example.demo.usuario.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;

    @Transactional
    public CandidatoRespostaDTO cadastrar(
            CandidatoSalvarDTO dto,
            Usuario usuario
    ) {
        Candidato candidato = dtoToEntity(dto, new Candidato());
        candidato.setUsuario(usuario);

        return entityToDto(candidatoRepository.save(candidato));
    }

    @Transactional
    public CandidatoRespostaDTO atualizar(
            Long id,
            CandidatoSalvarDTO dto
    ) {
        Candidato candidato = candidatoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Candidato não encontrado."
                        )
                );

        dtoToEntity(dto, candidato);

        return entityToDto(candidatoRepository.save(candidato));
    }

    @Transactional
    public List<CandidatoRespostaDTO> listarTodos() {
        return candidatoRepository.findAllByOrderByIdDesc()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CandidatoRespostaDTO buscarPorId(Long id) {
        Candidato candidato = candidatoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Candidato com id " + id + " não encontrado!"
                        )
                );

        return entityToDto(candidato);
    }

    private Candidato dtoToEntity( CandidatoSalvarDTO dto, Candidato candidato ) {
        candidato.setNome(dto.nome());
        candidato.setFoto(dto.foto());
        candidato.setNascimento(dto.nascimento());
        candidato.setEscolaridade(dto.escolaridade());

        return candidato;
    }

    private CandidatoRespostaDTO entityToDto(Candidato candidato) {
        return new CandidatoRespostaDTO(
                candidato.getId(),
                candidato.getNome(),
                candidato.getFoto(),
                candidato.getNascimento(),
                candidato.getEscolaridade(),
                candidato.getUsuario().getId(),
                candidato.getAreasDeInteresse()
                        .stream()
                        .map(AreasDasVagas::getId)
                        .toList()
        );
    }
}
