package com.br.banco.usuario.repositories;

import com.br.banco.usuario.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ContaRepository extends JpaRepository<Conta, String> {

    Optional<Conta> findByIdCliente(String id);
    @Query(value = "SELECT c FROM TB_CONTA c WHERE c.agencia=:agencia AND c.conta=:conta AND c.digito=:digito")
    Optional<Conta> findByContaBydigitoByAgencia(@Param("agencia") Integer agencia,
                                                 @Param("conta") Integer conta,
                                                 @Param("digito") Integer digito);
    @Modifying
    @Query(value = "UPDATE TB_CONTA c SET c.saldo= :valor WHERE c.id = :id ")
    void updateSaldoConta(@Param("id")String id, @Param("valor")Double valor);
}
