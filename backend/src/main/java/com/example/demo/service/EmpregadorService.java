package com.example.demo.service;

import com.example.demo.dtos.CepRespostaDTO;
import com.example.demo.dtos.EmpregadorRespostaDTO;
import com.example.demo.dtos.EmpregadorSalvarDTO;
import com.example.demo.dtos.VagaRespostaDTO;
import com.example.demo.model.Empregador;
import com.example.demo.model.Vaga;
import com.example.demo.repository.EmpregadorRepository;
import com.example.demo.usuario.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpregadorService {

    private final CepService cepService;
    private final EmpregadorRepository empregadorRepository;

    @Transactional
    public EmpregadorRespostaDTO cadastrar(EmpregadorSalvarDTO dto, Usuario usuario) {

        Empregador empregador = dtoToEntity(dto, new Empregador());
        empregador.setUsuario(usuario);

        return entityToDto(empregadorRepository.save(empregador));
    }

    @Transactional
    public EmpregadorRespostaDTO atualizar(Long id, EmpregadorSalvarDTO dto) {

        Empregador empregador = empregadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empregador não encontrado."));

        dtoToEntity(dto, empregador);

        return entityToDto(empregadorRepository.save(empregador));
    }

    @Transactional
    public List<EmpregadorRespostaDTO> listarTodos(){
        return empregadorRepository.findAllByOrderByIdDesc()
                .stream()
                .map(this::entityToDto).collect(Collectors.toList());
    }


    @Transactional
    public EmpregadorRespostaDTO buscarPorId(Long id){
        Empregador empregador = empregadorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Empregador com id " + id + " não encontrada!"
                        )
                );
        return entityToDto(empregador);

    }

    private Empregador dtoToEntity(EmpregadorSalvarDTO dto, Empregador empregador) {

        String cepLimpo = dto.cep().replaceAll("\\D", "");

        if (!cepLimpo.equals(empregador.getCep())) {
            CepRespostaDTO endereco = cepService.buscarLocalizacao(cepLimpo);

            empregador.setCep(cepLimpo);
            empregador.setRua(endereco.rua());
            empregador.setBairro(endereco.bairro());
            empregador.setCidade(endereco.cidade());
            empregador.setEstado(endereco.estado());
        }

        empregador.setNome(dto.nome());
        empregador.setFoto(dto.foto());
        empregador.setDescricao(dto.descricao());
        empregador.setContato(dto.contato());
        empregador.setNumero(dto.numero());
        empregador.setComplemento(dto.complemento());

        return empregador;
    }

    private EmpregadorRespostaDTO entityToDto(Empregador empregador) {
        return new EmpregadorRespostaDTO(
                empregador.getId(),
                empregador.getNome(),
                empregador.getFoto(),
                empregador.getDescricao(),
                empregador.getCep(),
                empregador.getRua(),
                empregador.getNumero(),
                empregador.getComplemento(),
                empregador.getBairro(),
                empregador.getCidade(),
                empregador.getEstado(),
                empregador.getContato()
        );
    }
}

