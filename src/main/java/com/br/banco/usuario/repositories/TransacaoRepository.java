package com.br.banco.usuario.repositories;

import com.br.banco.usuario.domain.Transacao;
import com.br.banco.usuario.domain.enums.TipoTransacao;
import com.br.banco.usuario.dtos.DefaultExtratoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.OrderBy;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, String> {

    Page<Transacao> findByIdContaOrderByDataHoraTransacaoDesc(String id,Pageable pageable);

    Page<Transacao> findByIdContaAndTipoTransacaoOrderByDataHoraTransacaoDesc(String id,TipoTransacao tipoTransacao,Pageable pageable);

}
