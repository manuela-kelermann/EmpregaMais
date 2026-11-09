package com.example.demo.model;

import com.example.demo.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empregadores")
public class Empregador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empregadores")
    private Long id;

    @Column(name = "nome_empresa")
    private String nome;

    @Column(name = "foto")
    private String foto;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "cep")
    private String cep;

    @Column(name = "rua")
    private String rua;

    @Column(name = "numero")
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "contato")
    private String contato;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", nullable = false)
    private Usuario usuario;

}
