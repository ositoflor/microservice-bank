package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Cliente;
import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.domain.Transacao;
import com.br.banco.usuario.domain.enums.TipoConta;
import com.br.banco.usuario.domain.enums.TipoTransacao;
import com.br.banco.usuario.dtos.ContaDto;
import com.br.banco.usuario.dtos.DepositoDto;
import com.br.banco.usuario.dtos.SaqueDto;
import com.br.banco.usuario.dtos.TransferenciaDto;
import com.br.banco.usuario.exceptionHandler.ContaExceptions.ContaNotFound;
import com.br.banco.usuario.exceptionHandler.ContaExceptions.SaldoNotValid;
import com.br.banco.usuario.repositories.ContaRepository;
import com.br.banco.usuario.services.ClienteService;
import com.br.banco.usuario.services.ContaService;
import com.br.banco.usuario.services.TransacaoService;
import com.br.banco.usuario.services.utils.GeradorConta;
import com.br.banco.usuario.services.utils.GerarTransacao;
import com.br.banco.usuario.services.utils.ValidadorDocumento;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    TransacaoService transacaoService;

    @Override
    @Transactional
    public Conta save(ContaDto contaDto) {
        Cliente cliente = clienteService.findById(contaDto.getIdCliente());
        TipoConta tipoConta = ValidadorDocumento.validarTipoConta(cliente);
        Conta conta = GeradorConta.gerarConta(contaDto, tipoConta);
        conta.setDocumentoCliente(cliente.getDocumento());
        conta.setQuantidadeSaque(tipoConta.getQuantidadeSaque());
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
    public Conta findByConta(Integer agencia, Integer conta, Integer digito) {
        var response = contaRepository.findByContaBydigitoByAgencia(agencia,conta,digito);
        System.out.println();
        System.out.println(response.toString());
        return response.orElseThrow(() -> new ContaNotFound("Conta não encontrada."));
    }

    @Override
    public Conta findByIdCliente(String id) {
        return contaRepository.findByIdCliente(id).orElseThrow(() -> new ContaNotFound("Conta não encontrada."));
    }

    @Override
    public SaqueDto saque(String id, Double valor) {
        Conta conta = findById(id);
        SaqueDto saqueDto = new SaqueDto();
        Transacao transacao = GerarTransacao.gerar(id,TipoTransacao.SA,valor);
        transacao.setClienteOrigemTransacao(conta.getIdCliente());
        if (conta.getSaldo() < valor){
            throw new SaldoNotValid();
        }
        if (conta.getQuantidadeSaque() == 0){
            var valorDebitado = valor + conta.getTipoConta().getValorSaque();
            if (conta.getSaldo() < valorDebitado){
                throw new SaldoNotValid();
            }
            conta.setSaldo(conta.getSaldo() - valorDebitado);
            contaRepository.save(conta);
            transacao.setValor(valorDebitado);
            transacaoService.save(transacao);
            return saqueDto;
        }
        conta.setSaldo(conta.getSaldo() - valor);
        contaRepository.save(conta);
        transacao.setValor(valor);
        transacaoService.save(transacao);
        return saqueDto;
    }

    @Override
    public DepositoDto deposito(DepositoDto depositoDto) {
        Conta conta = findByConta(depositoDto.getAgencia(),depositoDto.getConta(),depositoDto.getDigito());
        conta.setSaldo(conta.getSaldo() + depositoDto.getValorDeposito());
        Transacao transacao = GerarTransacao.gerar(conta.getId(), TipoTransacao.DE, depositoDto.getValorDeposito());
        transacao.setClienteOrigemTransacao(conta.getIdCliente());
        contaRepository.save(conta);
        transacaoService.save(transacao);
        return depositoDto;
    }

    @Override
    public TransferenciaDto transferenciaTed(TransferenciaDto transferenciaDto) {
        Conta contaOrigem = findById(transferenciaDto.getIdCLienteOrigem());
        Conta contaDestino = findByConta(transferenciaDto.getAgenciaDestino(), transferenciaDto.getContaDestino(), transferenciaDto.getDigitoDestino());
        if (contaOrigem.getSaldo() < transferenciaDto.getValor()) {
            throw new SaldoNotValid();
        }
        contaRepository.updateSaldoConta(contaOrigem.getId(), contaOrigem.getSaldo() - transferenciaDto.getValor());
        contaRepository.updateSaldoConta(contaDestino.getId(), contaDestino.getSaldo() + transferenciaDto.getValor());
        Transacao transacao = GerarTransacao.gerar(contaOrigem.getId(), TipoTransacao.TED, transferenciaDto.getValor());
        transacao.setClienteOrigemTransacao(contaOrigem.getId());
        transacao.setClienteDestinoTransacao(contaDestino.getId());
        transacaoService.save(transacao);
        return transferenciaDto;
    }

    @Override
    public TransferenciaDto transferenciaDoc(TransferenciaDto transferenciaDto) {
        Conta contaOrigem = findById(transferenciaDto.getIdCLienteOrigem());
        Conta contaDestino = findByConta(transferenciaDto.getAgenciaDestino(), transferenciaDto.getContaDestino(), transferenciaDto.getDigitoDestino());
        if (contaOrigem.getSaldo() < transferenciaDto.getValor()) {
            throw new SaldoNotValid();
        }
        contaRepository.updateSaldoConta(contaOrigem.getId(), contaOrigem.getSaldo() - transferenciaDto.getValor());
        contaRepository.updateSaldoConta(contaDestino.getId(), contaDestino.getSaldo() + transferenciaDto.getValor());
        Transacao transacao = GerarTransacao.gerar(contaOrigem.getId(), TipoTransacao.DOC, transferenciaDto.getValor());
        transacao.setClienteOrigemTransacao(contaOrigem.getId());
        transacao.setClienteDestinoTransacao(contaDestino.getId());
        transacaoService.save(transacao);
        return transferenciaDto;
    }

    @Override
    public TransferenciaDto transferenciaPix(TransferenciaDto transferenciaDto) {
        Conta contaOrigem = findById(transferenciaDto.getIdCLienteOrigem());
        Conta contaDestino = findByConta(transferenciaDto.getAgenciaDestino(), transferenciaDto.getContaDestino(), transferenciaDto.getDigitoDestino());
        if (contaOrigem.getSaldo() < transferenciaDto.getValor()) {
            throw new SaldoNotValid();
        }
        contaRepository.updateSaldoConta(contaOrigem.getId(), contaOrigem.getSaldo() - transferenciaDto.getValor());
        contaRepository.updateSaldoConta(contaDestino.getId(), contaDestino.getSaldo() + transferenciaDto.getValor());
        Transacao transacao = GerarTransacao.gerar(contaOrigem.getId(), TipoTransacao.PIX, transferenciaDto.getValor());
        transacao.setClienteOrigemTransacao(contaOrigem.getId());
        transacao.setClienteDestinoTransacao(contaDestino.getId());
        transacaoService.save(transacao);
        return transferenciaDto;
    }
}
