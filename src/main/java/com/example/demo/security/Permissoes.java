package com.example.demo.security;

public final class Permissoes {

    private Permissoes(){}

    public static final String VAGAS_EDITAR = "hasAuthority('VAGAS_EDITAR')";
    public static final String VAGAS_VISUALIZAR = "hasAuthority('VAGAS_VISUALIZAR')";


}
