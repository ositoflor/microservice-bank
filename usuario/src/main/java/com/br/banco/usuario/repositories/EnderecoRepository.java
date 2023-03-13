package com.br.banco.usuario.repositories;

import com.br.banco.usuario.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {
}
