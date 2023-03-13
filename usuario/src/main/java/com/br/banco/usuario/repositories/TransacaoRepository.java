package com.br.banco.usuario.repositories;

import com.br.banco.usuario.domain.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, String> {

    Page<Transacao> findByIdConta(String id, Pageable pageable);
}
