package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Cliente;
import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.domain.Solicitacao;
import com.br.banco.usuario.domain.Transacao;
import com.br.banco.usuario.domain.enums.StatusSolicitacao;
import com.br.banco.usuario.domain.enums.TipoConta;
import com.br.banco.usuario.domain.enums.TipoTransacao;
import com.br.banco.usuario.dtos.*;
import com.br.banco.usuario.exceptionHandler.BusinessException;
import com.br.banco.usuario.repositories.ContaRepository;
import com.br.banco.usuario.services.ClienteService;
import com.br.banco.usuario.services.ContaService;
import com.br.banco.usuario.services.SolicitacaoService;
import com.br.banco.usuario.services.TransacaoService;
import com.br.banco.usuario.services.utils.GeradorConta;
import com.br.banco.usuario.services.utils.GerarTransacao;
import com.br.banco.usuario.services.utils.ValidadorDocumento;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ClienteService clienteService;
    @Autowired
    TransacaoService transacaoService;
    @Autowired
    SolicitacaoService solicitacaoService;

    @Autowired
    ModelMapper mapper;

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
        return contaRepository.findById(id).orElseThrow(() -> new BusinessException("Conta não encontrada."));
    }

    @Override
    public Conta findByConta(Integer agencia, Integer conta, Integer digito) {
        var response = contaRepository.findByContaBydigitoByAgencia(agencia,conta,digito);
        return response.orElseThrow(() -> new BusinessException("Conta não encontrada."));
    }

    @Override
    public Conta findByIdCliente(String id) {
        return contaRepository.findByIdCliente(id).orElseThrow(() -> new BusinessException("Conta não encontrada."));
    }

    @Override
    public SolicitacaoDto solicitarSaque(SaqueDto saqueDto) {
        Conta conta = findByConta(saqueDto.getAgencia(), saqueDto.getConta(),saqueDto.getDigito());
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setIdConta(conta.getId());
        solicitacao.setValorSolitidado(saqueDto.getValorSaque());
        solicitacao.setTipoTransacao(TipoTransacao.SA);

        var res = solicitacaoService.save(solicitacao);

        SolicitacaoDto solicitacaoDto = new SolicitacaoDto(conta, saqueDto.getValorSaque(), res.getId(), res.getLocalDateTime());

        return solicitacaoDto;
    }

    @Override
    public SaqueDto validarSaque(String id) {
        Solicitacao solicitacao = solicitacaoService.findById(id);
        Conta conta = findById(solicitacao.getIdConta());

        if (solicitacao.getIsVerificado()){
            throw new BusinessException("Solicitação já concluida.");
        }

        if (solicitacao.getStatusSolicitacao() != StatusSolicitacao.AT){
            throw new BusinessException("Não foi possivel realizar operação.");
        }

        if (conta.getQuantidadeSaque() > 0) {
            conta.setQuantidadeSaque(conta.getQuantidadeSaque() - 1);
        }

        conta.setSaldo(conta.getSaldo() - solicitacao.getValorDebitado());
        contaRepository.save(conta);

        Transacao transacao = GerarTransacao.gerar(conta.getId(),solicitacao.getTipoTransacao(), solicitacao.getValorDebitado());
        transacao.setClienteOrigemTransacao(conta.getIdCliente());
        transacaoService.save(transacao);

        SaqueDto saqueDto= new SaqueDto();
        saqueDto.setAgencia(conta.getAgencia());
        saqueDto.setConta(conta.getConta());
        saqueDto.setDigito(conta.getDigito());
        saqueDto.setValorSaque(solicitacao.getValorSolitidado());

        solicitacao.setIsVerificado(true);
        solicitacaoService.save(solicitacao);

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
            throw new BusinessException("Saldo nsuficiente");
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
            throw new BusinessException("Saldo nsuficiente");
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
            throw new BusinessException("Saldo nsuficiente");
        }
        contaRepository.updateSaldoConta(contaOrigem.getId(), contaOrigem.getSaldo() - transferenciaDto.getValor());
        contaRepository.updateSaldoConta(contaDestino.getId(), contaDestino.getSaldo() + transferenciaDto.getValor());
        Transacao transacao = GerarTransacao.gerar(contaOrigem.getId(), TipoTransacao.PIX, transferenciaDto.getValor());
        transacao.setClienteOrigemTransacao(contaOrigem.getId());
        transacao.setClienteDestinoTransacao(contaDestino.getId());
        transacaoService.save(transacao);
        return transferenciaDto;
    }

    @Override
    public Page<DefaultExtratoDto> extratoPorIdConta(Integer agencia, Integer conta, Integer digito,Pageable pageable) {
        Conta conta1 = findByConta(agencia, conta, digito);
            return new PageImpl<DefaultExtratoDto>(transacaoService.findByIdConta(conta1.getId(),pageable).stream()
                    .map(transacao -> mapper.map(transacao, DefaultExtratoDto.class)).collect(Collectors.toList()));
    }

    @Override
    public Page<DefaultExtratoDto> extratoPorIdContaTipoTransacao(Integer agencia, Integer conta, Integer digito, TipoTransacao tipoTransacao, Pageable pageable) {
        Conta conta1 = findByConta(agencia, conta, digito);
        return new PageImpl<DefaultExtratoDto>(transacaoService.findByIdContaByTipoTransacao(conta1.getId(),tipoTransacao,pageable).stream()
                .map(transacao -> mapper.map(transacao, DefaultExtratoDto.class)).collect(Collectors.toList()));
    }


}
