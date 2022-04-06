package com.santander.banco811.service;

import com.santander.banco811.dto.UsuarioRequest;
import com.santander.banco811.dto.UsuarioResponse;
import com.santander.banco811.model.Usuario;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsuarioService {
    Page<Usuario> getAll(String nome, int page, int size);
    List<Usuario> search(String search);
    List<UsuarioResponse> getAllByCpf(String cpf, int page, int size);
    UsuarioResponse create(UsuarioRequest usuarioRequest);
    Usuario getById(Integer id);
    Usuario update(UsuarioRequest usuarioRequest, Integer id);
    void delete(Integer id);
}
