package com.santander.banco811.service.impl;

import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoConta;
import com.santander.banco811.projection.ContaView;
import com.santander.banco811.repository.ContaRepository;
import com.santander.banco811.repository.UsuarioRepository;
import com.santander.banco811.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<ContaView> getAllViewByTipoConta(TipoConta tipoConta) {
        return contaRepository.findAllByTipoConta(tipoConta);
    }

    @Override
    public Conta create(ContaRequest contaRequest, String username) {
        var usuario = usuarioRepository.findByLogin(username);

        Conta conta = new Conta();
        conta.setUsuario(usuario.get());
        conta.setTipoConta(contaRequest.getTipoConta());

        return contaRepository.save(conta);
    }
}
