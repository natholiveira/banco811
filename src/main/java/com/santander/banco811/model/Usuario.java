package com.santander.banco811.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santander.banco811.dto.UsuarioRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "usuario")
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "login")
    private String login;

    @Column(name = "data_criacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @Column(name = "senha")
    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Conta> contas;

    public Usuario(UsuarioRequest usuarioRequest, String encryptedPassword) {
        this.cpf = usuarioRequest.getCpf();
        this.nome = usuarioRequest.getNome();
        this.senha = encryptedPassword;
    }
}
