package com.example.demo;

import com.example.demo.dtos.CandidatoRespostaDTO;
import com.example.demo.dtos.CandidatoSalvarDTO;
import com.example.demo.model.Candidato;
import com.example.demo.repository.CandidatoRepository;
import com.example.demo.service.CandidatoService;
import com.example.demo.usuario.Usuario;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidatoServiceTest {

    @Mock
    private CandidatoRepository candidatoRepository;

    @Mock
    private CandidatoSalvarDTO dto;

    @InjectMocks
    private CandidatoService candidatoService;

    private Candidato candidato;

    @BeforeEach
    void configurar() {
        Usuario usuario = new Usuario();
        usuario.setId(10L);

        candidato = new Candidato();
        candidato.setId(1L);
        candidato.setNome("João");
        candidato.setFoto("joao.png");
        candidato.setNascimento(LocalDate.of(2000, 5, 10));
        candidato.setEscolaridade("Ensino Médio");
        candidato.setUsuario(usuario);
        candidato.setAreasDeInteresse(new ArrayList<>());
    }

    @Test
    void deveBuscarCandidatoExistente() {
        when(candidatoRepository.findById(1L)).thenReturn(Optional.of(candidato));

        CandidatoRespostaDTO resposta = candidatoService.buscarPorId(1L);

        assertNotNull(resposta);
        assertEquals(1L, resposta.id());
        assertEquals("João", resposta.nome());
        assertEquals("Ensino Médio", resposta.escolaridade());

        verify(candidatoRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(candidatoRepository);
    }

    @Test
    void deveLancarExcecaoAoBuscarCandidatoInexistente() {
        when(candidatoRepository.findById(999L)).thenReturn(Optional.empty());

        EntityNotFoundException excecao = assertThrows(
                EntityNotFoundException.class,
                () -> candidatoService.buscarPorId(999L)
        );

        assertEquals("Candidato com id 999 não encontrado!", excecao.getMessage());
        verify(candidatoRepository).findById(999L);
        verify(candidatoRepository, never()).save(any(Candidato.class));
    }

    @Test
    void deveAtualizarCandidatoComSucesso() {
        configurarDtoValido();

        when(candidatoRepository.findById(1L)).thenReturn(Optional.of(candidato));
        when(candidatoRepository.save(any(Candidato.class)))
                .thenAnswer(invocacao -> invocacao.getArgument(0));

        CandidatoRespostaDTO resposta = candidatoService.atualizar(1L, dto);

        assertNotNull(resposta);
        assertEquals("João Silva", resposta.nome());
        assertEquals("Ensino Superior", resposta.escolaridade());
        assertEquals(LocalDate.of(2001, 8, 20), resposta.nascimento());

        verify(candidatoRepository).findById(1L);
        verify(candidatoRepository).save(candidato);
    }


    @Test
    void naoDeveAtualizarQuandoNomeForInvalido() {
        CandidatoSalvarDTO dtoInvalido = new CandidatoSalvarDTO(
                "   ",
                "joao.png",
                LocalDate.of(2001, 8, 20),
                "Ensino Superior",
                10L,
                new ArrayList<>()
        );

        Validator validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        var erros = validator.validate(dtoInvalido);

        assertFalse(erros.isEmpty());

        assertTrue(erros.stream()
                .anyMatch(erro ->
                        erro.getPropertyPath().toString().equals("nome")
                ));

        verifyNoInteractions(candidatoRepository);
    }
    private void configurarDtoValido() {
        when(dto.nome()).thenReturn("João Silva");
        when(dto.foto()).thenReturn("joao-silva.png");
        when(dto.nascimento()).thenReturn(LocalDate.of(2001, 8, 20));
        when(dto.escolaridade()).thenReturn("Ensino Superior");
    }
}
