package com.br.banco.usuario.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_ENDERECO")
@Table(name = "TB_ENDERECO")
public class Endereco {
    @Id
    @GenericGenerator(name="UUIDGenerator", strategy ="uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private String id;
    @Column(name = "rua")
    private String rua;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "estado")
    private String estado;
}
