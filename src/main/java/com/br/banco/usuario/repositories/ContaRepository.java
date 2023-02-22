package com.br.banco.usuario.repositories;

import com.br.banco.usuario.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, String> {

    Optional<Conta> findByIdCliente(String id);
}
