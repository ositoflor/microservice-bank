package com.br.banco.usuario.controllers;

import com.br.banco.usuario.domain.Cliente;
import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.dtos.ContaDto;
import com.br.banco.usuario.dtos.DepositoDto;
import com.br.banco.usuario.dtos.SaqueDto;
import com.br.banco.usuario.dtos.TransferenciaDto;
import com.br.banco.usuario.services.ContaService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ContaController {

    @Autowired
    ContaService contaService;
    private final KafkaTemplate<String,String > templete;

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
    public ResponseEntity<Conta> findByContaByAgencia(@RequestParam(name = "agencia")Integer agencia,
                                             @RequestParam(name = "conta")Integer conta,
                                             @RequestParam(name = "digito")Integer digito){
        Conta conta1 = contaService.findByConta(agencia,conta,digito);
        return ResponseEntity.ok().body(conta1);
    }

    @GetMapping(value = "/cliente/{id}")
    public ResponseEntity<Conta> findByIdCliente(@PathVariable(value = "id") String id) {
        Conta conta = contaService.findByIdCliente(id);
        return ResponseEntity.ok().body(conta);
    }

    @PutMapping(value = "/sacar/{valor}/{id}")
    public ResponseEntity<SaqueDto> sacar(@PathVariable(value = "valor")Double valor, @PathVariable(value = "id")String id) {
        SaqueDto saqueDto = contaService.saque(id,valor);
        templete.send("Transacao",id);
        return ResponseEntity.ok().body(saqueDto);
    }

    @PutMapping(value = "/depositar")
    public ResponseEntity<DepositoDto> depositar(@RequestBody DepositoDto depositoDto){
        DepositoDto deposito = contaService.deposito(depositoDto);
        return ResponseEntity.ok().body(deposito);
    }

    @PutMapping(value = "/ted")
    public ResponseEntity<TransferenciaDto> ted(@RequestBody TransferenciaDto transferenciaDto) {
        TransferenciaDto transferencia = contaService.transferenciaTed(transferenciaDto);
        return ResponseEntity.ok().body(transferencia);
    }
    @PutMapping(value = "/doc")
    public ResponseEntity<TransferenciaDto> doc(@RequestBody TransferenciaDto transferenciaDto) {
        TransferenciaDto transferencia = contaService.transferenciaDoc(transferenciaDto);
        return ResponseEntity.ok().body(transferencia);
    }
    @PutMapping(value = "/pix")
    public ResponseEntity<TransferenciaDto> pix(@RequestBody TransferenciaDto transferenciaDto) {
        TransferenciaDto transferencia = contaService.transferenciaPix(transferenciaDto);
        return ResponseEntity.ok().body(transferencia);
    }
}
