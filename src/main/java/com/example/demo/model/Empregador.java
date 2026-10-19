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
@Table(name = "empregadores")
public class Empregador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_empresa")
    private String nome;

    @Column(name = "foto")
    private String foto;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "contato")
    private String contato;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", nullable = false)
    private Usuario usuario;

}
