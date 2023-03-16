package com.br.banco.usuario.controllers;

import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.domain.enums.TipoTransacao;
import com.br.banco.usuario.dtos.*;
import com.br.banco.usuario.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/conta")
public class ContaController {

    @Autowired
    ContaService contaService;
    @Autowired
    private KafkaTemplate<String, SolicitacaoDto> kafkaTemplate;

    @PostMapping(value = "/add")
    public ResponseEntity<Conta> save(@RequestBody @Valid ContaDto contaDto) {
        Conta conta1 = contaService.save(contaDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(conta1.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page> findAll(Pageable pageable) {
        Page<Conta> content = contaService.findAll(pageable);
        return ResponseEntity.ok().body(content);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Conta> findById(@PathVariable(value = "id") String id) {
        Conta conta = contaService.findById(id);
        return ResponseEntity.ok().body(conta);
    }

    @GetMapping(value = "/buscar-conta")
    public ResponseEntity<Conta> findByContaByAgencia(@RequestParam(name = "agencia") Integer agencia,
                                                      @RequestParam(name = "conta") Integer conta,
                                                      @RequestParam(name = "digito") Integer digito) {
        Conta conta1 = contaService.findByConta(agencia, conta, digito);
        return ResponseEntity.ok().body(conta1);
    }

    @GetMapping(value = "/cliente/{id}")
    public ResponseEntity<Conta> findByIdCliente(@PathVariable(value = "id") String id) {
        Conta conta = contaService.findByIdCliente(id);
        return ResponseEntity.ok().body(conta);
    }

    @PostMapping(value = "/solicitar-saque")
    public ResponseEntity<SolicitacaoRespostaDto> sacar(@RequestBody SaqueDto saqueDto) {
        SolicitacaoDto solicitacaoDto = contaService.solicitarSaque(saqueDto);
        SolicitacaoRespostaDto solicitacaoRespostaDto = new SolicitacaoRespostaDto(solicitacaoDto.getIdSolicitacao(), solicitacaoDto.getDateTime());
        kafkaTemplate.send("Solicitacao-2", solicitacaoDto);
        return ResponseEntity.ok().body(solicitacaoRespostaDto);
    }

    @PostMapping(value = "/validar-saque")
    public ResponseEntity<SaqueDto> validarSaque(@RequestParam(name = "id-solicitacao") String id) {
        SaqueDto saqueDto = contaService.validarSaque(id);
        return ResponseEntity.ok().body(saqueDto);
    }

    @PostMapping(value = "/depositar")
    public ResponseEntity<DepositoDto> depositar(@RequestBody DepositoDto depositoDto) {
        DepositoDto deposito = contaService.deposito(depositoDto);
        return ResponseEntity.ok().body(deposito);
    }

    @PostMapping(value = "/ted")
    public ResponseEntity<TransferenciaDto> ted(@RequestBody TransferenciaDto transferenciaDto) {
        TransferenciaDto transferencia = contaService.transferenciaTed(transferenciaDto);
        return ResponseEntity.ok().body(transferencia);
    }

    @PostMapping(value = "/doc")
    public ResponseEntity<TransferenciaDto> doc(@RequestBody TransferenciaDto transferenciaDto) {
        TransferenciaDto transferencia = contaService.transferenciaDoc(transferenciaDto);
        return ResponseEntity.ok().body(transferencia);
    }

    @PostMapping(value = "/pix")
    public ResponseEntity<TransferenciaDto> pix(@RequestBody TransferenciaDto transferenciaDto) {
        TransferenciaDto transferencia = contaService.transferenciaPix(transferenciaDto);
        return ResponseEntity.ok().body(transferencia);
    }

    @GetMapping(value = "/extrato")
    public ResponseEntity<Page<DefaultExtratoDto>> extrato(@RequestParam(name = "agencia") Integer agencia,
                                                           @RequestParam(name = "conta") Integer conta,
                                                           @RequestParam(name = "digito") Integer digito,
                                                           @RequestParam(name = "filter",required = false ) TipoTransacao tipoTransacao,
                                                           Pageable pageable) {
        if (tipoTransacao == null) {
            Page<DefaultExtratoDto> extrato = contaService.extratoPorIdConta(agencia, conta, digito,pageable);
            return ResponseEntity.ok().body(extrato);
        }

        Page<DefaultExtratoDto> extrato = contaService.extratoPorIdContaTipoTransacao(agencia, conta, digito,tipoTransacao,pageable);
        return ResponseEntity.ok().body(extrato);
    }
}
