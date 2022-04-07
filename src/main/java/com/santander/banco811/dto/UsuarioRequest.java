package com.santander.banco811.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioRequest {
    private String cpf;
    private String nome;
    private String senha;
    private String login;
}
