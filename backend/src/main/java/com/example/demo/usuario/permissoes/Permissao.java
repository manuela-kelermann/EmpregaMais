package com.example.demo.usuario.permissoes;

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
@Table(name = "permissoes")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permissoes")
    private Long id;

    @Column(name = "modulo", length = 50)
    String modulo;

    @Column(name = "descricao")
    String descricao;
}
