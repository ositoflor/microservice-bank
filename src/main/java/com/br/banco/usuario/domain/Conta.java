package com.br.banco.usuario.domain;

import com.br.banco.usuario.domain.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_CONTA")
@Table(name = "TB_CONTA")
public class Conta implements Serializable {

    @Id
    @GenericGenerator(name="UUIDGenerator", strategy ="uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private String id;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "documento")
    private Cliente cliente;
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

}
