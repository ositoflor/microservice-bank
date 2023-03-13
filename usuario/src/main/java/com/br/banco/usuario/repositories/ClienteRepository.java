package com.br.banco.usuario.repositories;

import com.br.banco.usuario.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
