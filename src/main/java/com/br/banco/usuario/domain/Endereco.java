package com.br.banco.usuario.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_ENDERECO")
@Table(name = "TB_ENDERECO")
public class Endereco {
    @Id
    @GenericGenerator(name="UUIDGenerator", strategy ="uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private String id;
    @Column
    private String rua;
    @Column
    private Integer numero;
    @Column
    private String complemento;
    @Column
    private String cep;
    @Column
    private String bairro;
    @Column
    private String cidade;
    @Column
    private String estado;
    @Column
    private String idCliente;
}
