package com.br.banco.usuario.services;

import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.domain.Transacao;
import com.br.banco.usuario.dtos.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ContaService {
    Conta save(ContaDto contaDto);
    Page<Conta> findAll(Pageable pageable);
    Conta findById(String id);
    Conta findByConta(Integer agencia, Integer conta, Integer digito);
    Conta findByIdCliente(String id);
    SolicitacaoDto solicitarSaque(SaqueDto saqueDto);
    SaqueDto validarSaque(String id);
    DepositoDto deposito(DepositoDto depositoDto);
    TransferenciaDto transferenciaTed(TransferenciaDto transferenciaDto);
    TransferenciaDto transferenciaDoc(TransferenciaDto transferenciaDto);
    TransferenciaDto transferenciaPix(TransferenciaDto transferenciaDto);
    Page<Transacao> extrato(Integer agencia, Integer conta, Integer digito, Pageable pageable);
}
