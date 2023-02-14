package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.domain.Usuario;
import com.br.banco.usuario.infra.repositories.data.ContaRepository;
import com.br.banco.usuario.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Override
    public Conta criarConta(Usuario usuario) {
        Conta conta = new Conta();
        conta.setAgencia(4245);
        conta.setConta(Integer.parseInt(randomDigitsAsString(7)));
        conta.setSaldo(0.0);
        conta.setDigito(Integer.parseInt(randomDigitsAsString(1)));
        conta.setCliente(usuario);
        return conta;
    }

    @Override
    public Conta save(Conta conta) {
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
