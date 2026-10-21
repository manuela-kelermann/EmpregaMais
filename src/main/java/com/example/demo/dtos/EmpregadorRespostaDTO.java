package com.example.demo.dtos;

public record EmpregadorRespostaDTO(
     Long id,
     String nome,
     String foto,
     String descricao,
     String cep,
     String rua,
     String numero,
     String complemento,
     String bairro,
     String cidade,
     String estado,
     String contato
) {
}
