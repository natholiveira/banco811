package com.santander.banco811.repository;

import com.santander.banco811.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

@DataJpaTest
@Profile("test")
public class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void validar_findAll_vazio_se_repository_em_branco() {
        var usuarios = usuarioRepository.findAll();

        Assertions.assertEquals(Arrays.asList(), usuarios);
    }

    @Test
    public void trazer_usuarios_cadastrados_no_find_all() {
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setSenha("12345677");
        usuario.setCpf("12312312312");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Anderson");
        usuario2.setSenha("12345677");
        usuario2.setCpf("12312312313");

        entityManager.persist(usuario);
        entityManager.persist(usuario2);

        var usuarios = usuarioRepository.findAll();

        Assertions.assertEquals(Arrays.asList(usuario, usuario2), usuarios);
    }

    @Test
    public void trazer_usuarios_pelo_nome_com_sucesso() {
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setSenha("12345677");
        usuario.setCpf("12312312312");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Anderson");
        usuario2.setSenha("12345677");
        usuario2.setCpf("12312312313");

        usuario = entityManager.persist(usuario);
        entityManager.persist(usuario2);

        PageRequest pageRequest = PageRequest.of(0,3);

        var usuarios = usuarioRepository.findByNome("Maria", pageRequest);

        Assertions.assertEquals(1, usuarios.getTotalElements());
        Assertions.assertEquals(usuario, usuarios.stream().findFirst().get());
    }

    @Test
    public void salvar_um_novo_usuario_com_sucesso() {
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678911");
        usuario.setNome("Teste");
        usuario.setSenha("12345678");

        usuario = usuarioRepository.save(usuario);

        Assertions.assertNotNull(usuario.getId());
        Assertions.assertNotNull(usuario.getDataAtualizacao());
        Assertions.assertNotNull(usuario.getDataCriacao());
    }

    @Test
    public void trazer_resultados_no_findAll_quando_houver_usuarios_na_base() {
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678912");
        usuario.setNome("Teste");
        usuario.setSenha("12345678");

        Usuario usuario2 = new Usuario();
        usuario2.setCpf("12345678911");
        usuario2.setNome("Maria");
        usuario2.setSenha("12345678");

        entityManager.persist(usuario);
        entityManager.persist(usuario2);

        var usuarios = usuarioRepository.findAll();

        Assertions.assertEquals(Arrays.asList(usuario, usuario2), usuarios);
    }

    @Test
    public void trazer_usuarios_pelo_nome() {
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678911");
        usuario.setNome("Teste");
        usuario.setSenha("12345678");

        Usuario usuario2 = new Usuario();
        usuario2.setCpf("12345678912");
        usuario2.setNome("Maria");
        usuario2.setSenha("12345678");

        entityManager.persist(usuario);
        entityManager.persist(usuario2);

        PageRequest pageRequest = PageRequest.of(
                0,
                10,
                Sort.Direction.DESC,
                "nome");

        var usuarios = usuarioRepository.findByNome("Maria", pageRequest);

        Assertions.assertEquals(1, usuarios.getTotalElements());
        Assertions.assertEquals(usuario2, usuarios.stream().findFirst().get());
    }

    @Test
    public void encontrar_usuario_pelo_id() {
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678911");
        usuario.setNome("Teste");
        usuario.setSenha("12345678");

       usuario = entityManager.persist(usuario);


        var usuarioEncontrado = usuarioRepository.findById(usuario.getId()).get();

        Assertions.assertEquals(usuarioEncontrado.getId(), usuario.getId());
        Assertions.assertEquals(usuarioEncontrado.getCpf(), usuario.getCpf());
    }

    @Test
    public void editar_usuario_pelo_id() {
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678911");
        usuario.setNome("Teste");
        usuario.setSenha("12345678");

        usuario = entityManager.persist(usuario);

        usuario.setNome("Maria");

        usuarioRepository.save(usuario);


        var usuarioEncontrado = usuarioRepository.findById(usuario.getId()).get();

        Assertions.assertEquals(usuarioEncontrado.getId(), usuario.getId());
        Assertions.assertEquals("Maria", usuario.getNome());
    }

}
