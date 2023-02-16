package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.domain.Cliente;
import com.br.banco.usuario.dtos.ContaDto;
import com.br.banco.usuario.repositories.ContaRepository;
import com.br.banco.usuario.repositories.ClienteRepository;
import com.br.banco.usuario.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Conta save(ContaDto contaDto) {
        Conta conta = new Conta();
        conta.setTipoConta(contaDto.getTipoConta());
        conta.setAgencia(contaDto.getAgencia());
        conta.setConta(Integer.parseInt(randomDigitsAsString(7)));
        conta.setDigito(Integer.parseInt(randomDigitsAsString(1)));
        Optional<Cliente> cliente = clienteRepository.findById(contaDto.getId_cliente());
        System.out.println(cliente.get());
        conta.setCliente(cliente.get());
        System.out.println(conta);
        return contaRepository.save(conta);
    }

    @Override
    public Page<Conta> findAll(Pageable pageable) {
        return contaRepository.findAll(pageable);
    }

    @Override
    public Optional<Conta> findById(String id) {
        return contaRepository.findById(id);
    }

    private static String randomDigitsAsString(int length) {
        int leftLimit = 48; // digito '0' na tabela ASCII
        int rightLimit = 57; // digito '9' na tabela ASCII
        int targetStringLength = length; // quantidade de digitos
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }
}
