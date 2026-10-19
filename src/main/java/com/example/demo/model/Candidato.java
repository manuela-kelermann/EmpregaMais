package com.example.demo.model;

import com.example.demo.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidatos")
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "foto")
    private String foto;

    @Column(name = "nascimento")
    private LocalDate nascimento;

    @Column(name = "escolaridade")
    private String escolaridade;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", nullable = false)
    private Usuario usuario;

    @Column(name = "id_areas_das_vagas")
    private Long areaDaVaga;

}
