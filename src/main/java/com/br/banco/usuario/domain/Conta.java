package com.br.banco.usuario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_CONTA")
@Table(name = "TB_CONTA")
public class Conta {

    @Id
    @GenericGenerator(name="UUIDGenerator", strategy ="uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private String id;
    @OneToOne
    @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
    @JsonIgnore
    private Usuario cliente;
    @Column(name = "agencia")
    private Integer agencia;
    @Column(name = "numero_conta")
    private Integer conta;
    @Column(name = "digito")
    private Integer digito;
    @Column(name = "tipo_conta")
    private TipoConta tipoConta;
    @Column(name = "saldo")
    private Double saldo;

}
