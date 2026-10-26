package com.example.demo.model;

import com.example.demo.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidatos")
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidatos")
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

    @ManyToMany
    @JoinTable(
            name = "candidatos_areas_vagas",
            joinColumns = @JoinColumn(name = "id_candidatos"),
            inverseJoinColumns = @JoinColumn(name = "id_areas_das_vagas")
    )
    private List<AreasDasVagas> areasDeInteresse = new ArrayList<>();

}
