package com.br.banco.usuario.domain;

import com.br.banco.usuario.domain.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_CONTA")
@Table(name = "TB_CONTA")
public class Conta {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private String id;
    @Column
    private Integer agencia;
    @Column(name = "numero_conta")
    private Integer conta;
    @Column
    private Integer digito;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta")
    private TipoConta tipoConta;
    @Column
    private Double saldo = 0.0;
    @Column(name = "documento_cliente")
    private String documentoCliente;
    @Column(name = "id_cliente")
    private String idCliente;
    @Column(name = "quantidade_saque")
    private Integer quantidadeSaque;
}
