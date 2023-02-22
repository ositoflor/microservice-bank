package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Cliente;
import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.domain.enums.TipoConta;
import com.br.banco.usuario.dtos.ContaDto;
import com.br.banco.usuario.exceptionHandler.ContaExceptions.ContaNotFound;
import com.br.banco.usuario.exceptionHandler.DocumentoInvalidException;
import com.br.banco.usuario.repositories.ContaRepository;
import com.br.banco.usuario.services.ClienteService;
import com.br.banco.usuario.services.ContaService;
import com.br.banco.usuario.services.utils.GeradorConta;
import com.br.banco.usuario.services.utils.GeradorDigitos;
import com.br.banco.usuario.services.utils.ValidadorDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ClienteService clienteService;

    @Override
    @Transactional
    public Conta save(ContaDto contaDto) {
        Cliente cliente = clienteService.findById(contaDto.getIdCliente());
        TipoConta tipoConta = ValidadorDocumento.validarTipoConta(cliente);
        Conta conta = GeradorConta.gerarConta(contaDto, tipoConta);
        conta.setDocumentoCliente(cliente.getDocumento());
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

    @Override
    public Conta findByIdCliente(String id) {
        return contaRepository.findByIdCliente(id).orElseThrow(()-> new ContaNotFound("Conta não encontrada.") );
    }

}
