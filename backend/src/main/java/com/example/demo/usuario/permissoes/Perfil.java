
package com.example.demo.usuario.permissoes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "perfis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfis")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome", nullable = false, unique = true)
    private PerfilUsuario nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "perfis_permissoes",
            joinColumns = @JoinColumn(name = "id_perfis"),
            inverseJoinColumns = @JoinColumn(name = "id_permissoes")
    )
    private Set<Permissao> permissoes = new HashSet<>();
}
