package com.br.banco.usuario.repositories;

import com.br.banco.usuario.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, String> {
}
