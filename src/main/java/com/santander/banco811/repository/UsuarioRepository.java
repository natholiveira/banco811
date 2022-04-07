package com.santander.banco811.repository;

import com.santander.banco811.dto.UsuarioResponse;
import com.santander.banco811.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>,
        JpaSpecificationExecutor<Usuario>, QuerydslPredicateExecutor<Usuario> {

    Page<Usuario> findByNome(String nome, Pageable pageable);

    @Query("select new com.santander.banco811.dto.UsuarioResponse(u.id, u.cpf, u.nome, u.dataCriacao, u.dataAtualizacao) from Usuario u " +
            "where u.cpf = :cpf")
    List<UsuarioResponse> findByCpf(@Param("cpf") String cpf, Pageable pageable);

    Optional<Usuario> findByLogin(String login);
}
