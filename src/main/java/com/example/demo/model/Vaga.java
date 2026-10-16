package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "vagas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "localidade")
    private String localidade;

    @Column(name = "modalidade")
    @Enumerated(EnumType.STRING)
    private ModalidadeEnum modalidadeEnum;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "escolaridade")
    private String escolaridade;

    @Column(name = "responsabilidade_atribuicao")
    private String responsabilidadeAtribuicoes;

    @Column(name = "requisitos_tecnicos")
    private String requisitosTecnicos;

    @Column(name = "beneficios")
    private String beneficios;

    @Column(name = "candidatar_se")
    private String candidatarSe;

    @Column(name = "id_areas_das_vagas")
    private Long areaDaVaga;


}
