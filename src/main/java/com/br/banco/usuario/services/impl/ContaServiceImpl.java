package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.dtos.ContaDto;
import com.br.banco.usuario.exceptionHandler.ContaExceptions.ContaNotFound;
import com.br.banco.usuario.repositories.ContaRepository;
import com.br.banco.usuario.services.ContaService;
import com.br.banco.usuario.services.utils.GeradorDigitos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Override
    @Transactional
    public Conta save(ContaDto contaDto) {
        Conta conta = new Conta();
        conta.setAgencia(contaDto.getAgencia());
        conta.setConta(Integer.parseInt(GeradorDigitos.gerarDigito(5)));
        conta.setDigito(Integer.parseInt(GeradorDigitos.gerarDigito(1)));
        conta.setDocumentoCliente(contaDto.getDocumentoCliente());
        conta.setIdCliente(contaDto.getIdCliente());
        System.out.println(conta);
        return contaRepository.save(conta);
    }

    @Override
    public Page<Conta> findAll(Pageable pageable) {
        return contaRepository.findAll(pageable);
    }

    @Override
    public Conta findById(String id) {
        return contaRepository.findById(id).orElseThrow(() -> new ContaNotFound("Conta não encontrada."));
    }

}
