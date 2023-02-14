package com.br.banco.usuario.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_USUARIO")
@Table(name = "TB_USUARIO")
public class Usuario {
    @Id
    @GenericGenerator(name="UUIDGenerator", strategy ="uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private String id;
    @Column(name = "nome")
    private String nome;
    @JsonSerialize(using = DateSerializer.class)
    @Column(name = "data_nascimento")
    private Date nascimento;
    @Column(name = "cpf",length = 11,unique = true,nullable = false)
    private String cpf;
    @OneToOne(cascade=CascadeType.ALL)
    private Conta conta;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
}
