package com.br.banco.usuario.services;

import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.dtos.ContaDto;
import com.br.banco.usuario.dtos.DepositoDto;
import com.br.banco.usuario.dtos.SaqueDto;
import com.br.banco.usuario.dtos.TransferenciaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ContaService {
    Conta save(ContaDto contaDto);
    Page<Conta> findAll(Pageable pageable);
    Conta findById(String id);
    Conta findByConta(Integer agencia, Integer conta, Integer digito);
    Conta findByIdCliente(String id);
    SaqueDto saque(String id, Double valor);
    DepositoDto deposito(DepositoDto depositoDto);
    TransferenciaDto transferenciaTed(TransferenciaDto transferenciaDto);
    TransferenciaDto transferenciaDoc(TransferenciaDto transferenciaDto);
    TransferenciaDto transferenciaPix(TransferenciaDto transferenciaDto);
}
