package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "vagas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vagas")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "localidade")
    private String localidade;

    @Column(name = "modalidade")
    @Enumerated(EnumType.STRING)
    private ModalidadeEnum modalidadeEnum;

    @Column(name = "status_vaga")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatusEnum status = StatusEnum.ABERTA;

    @Column(name = "piso_salario")
    private Double pisoSalario;

    @Column(name = "teto_salario")
    private Double tetoSalario;

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
