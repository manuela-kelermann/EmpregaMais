package com.example.demo.service;

import com.example.demo.controller.TratadorGlobal;
import com.example.demo.dtos.VagaRespostaDTO;
import com.example.demo.dtos.VagaSalvarDTO;
import com.example.demo.model.Vaga;
import com.example.demo.repository.VagaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository vagaRepository;

    @Transactional
    public VagaRespostaDTO criar(VagaSalvarDTO dto){
        Vaga vaga = new Vaga();
        Vaga vagaSalva = vagaRepository.save(dtoToEntity(vaga, dto));
        return entityToRespostaDTO(vagaSalva);

    }

    @Transactional
    public List<VagaRespostaDTO> listarTodos(){
    return vagaRepository.findAllByOrderByIdDesc().stream()
            .map(this::entityToRespostaDTO).collect(Collectors.toList());
    }

    @Transactional
    public VagaRespostaDTO atualizar(Long id, VagaSalvarDTO dto){

        Vaga vaga = vagaRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException( "Vaga com id " + id + " não encontrada!"));

            dtoToEntity(vaga, dto);

            Vaga vagaAtualizada = vagaRepository.save(vaga);

        return entityToRespostaDTO(vagaAtualizada);
    }


    @Transactional
    public VagaRespostaDTO buscarPorId(Long id){
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Vaga com id " + id + " não encontrada!"
                        )
                );
        return entityToRespostaDTO(vaga);

    }


    @Transactional
    public void deletar(Long id){
        if(!vagaRepository.existsById(id)){

            throw new EntityNotFoundException("Vaga com id " + id + " não encontrado!");
        }
        vagaRepository.deleteById(id);
    }


    private Vaga dtoToEntity(Vaga vaga, VagaSalvarDTO dto){

                vaga.setTitulo(dto.titulo());
                vaga.setLocalidade(dto.localidade());
                vaga.setModalidadeEnum(dto.modalidadeEnum());
                vaga.setDescricao(dto.descricao());
                vaga.setEscolaridade(dto.escolaridade());
                vaga.setResponsabilidadeAtribuicoes(dto.responsabilidadeAtribuicoes());
                vaga.setRequisitosTecnicos(dto.requisitosTecnicos());
                vaga.setBeneficios(dto.beneficios());
                vaga.setCandidatarSe(dto.candidatarSe());
                vaga.setAreaDaVaga(dto.areaDaVaga());

        return vaga;

    }

    private VagaRespostaDTO entityToRespostaDTO(Vaga vaga){

        return new VagaRespostaDTO(

                vaga.getId(),
                vaga.getTitulo(),
                vaga.getLocalidade(),
                vaga.getModalidadeEnum(),
                vaga.getDescricao(),
                vaga.getEscolaridade(),
                vaga.getResponsabilidadeAtribuicoes(),
                vaga.getRequisitosTecnicos(),
                vaga.getBeneficios(),
                vaga.getCandidatarSe(),
                vaga.getAreaDaVaga()

        );
    }



}
