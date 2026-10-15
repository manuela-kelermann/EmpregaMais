package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.sql.SQLTransactionRollbackException;

@Entity
@Table(name = "vagas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "localidade")
    private String localidade;

    @Column(name = "modalidade")
    @Enumerated(EnumType.STRING)
    private ModalidadeEnum modalidadeEnum;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "escolarida")
    private String escolaridade;

    @Column(name = "responsabilidade_atribuicao")
    private String responsabilidadeAtribuicoes;

    @Column(name = "responsabilidade_atribuicao")
    private String requisitosTecnicos;

    @Column(name = "beneficios")
    private String beneficios;

    @Column(name = "candidatar_se")
    private String candidatarSe;


}
