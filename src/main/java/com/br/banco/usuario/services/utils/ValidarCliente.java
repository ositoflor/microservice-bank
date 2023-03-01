package com.br.banco.usuario.services.utils;

import com.br.banco.usuario.domain.Cliente;
import com.br.banco.usuario.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarCliente {
    @Autowired
    ClienteService clienteService;

    public Cliente validarCliente(String id) {
        Cliente cliente = clienteService.findById(id);
        System.out.println(cliente);
        return cliente;
    }
}
