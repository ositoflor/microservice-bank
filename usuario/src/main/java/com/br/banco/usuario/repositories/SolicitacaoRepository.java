package com.br.banco.usuario.repositories;

import com.br.banco.usuario.domain.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, String> {
}
