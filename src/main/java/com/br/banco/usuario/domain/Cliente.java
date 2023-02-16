package com.br.banco.usuario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_USUARIO")
@Table(name = "TB_USUARIO")
public class Cliente implements Serializable {
    @Id
    @GenericGenerator(name="UUIDGenerator", strategy ="uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private String id;
    @Column
    private String nome;
    @JsonSerialize(using = DateSerializer.class)
    @Column(name = "data_nascimento")
    private Date nascimento;
    @Column(length = 11,unique = true,nullable = false)
    private String documento;
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "cliente")
    private List<Conta> conta;
}
