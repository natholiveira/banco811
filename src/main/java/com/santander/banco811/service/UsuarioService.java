package com.santander.banco811.service;

import com.santander.banco811.dto.UsuarioRequest;
import com.santander.banco811.dto.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponse> getAll();
    UsuarioResponse create(UsuarioRequest usuarioRequest);
}
