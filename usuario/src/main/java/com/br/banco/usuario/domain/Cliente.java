package com.br.banco.usuario.domain;

import com.br.banco.usuario.domain.enums.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_CLIENTE")
@Table(name = "TB_CLIENTE")
public class Cliente {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private String id;
    @Column
    private String nome;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "data_nascimento")
    private LocalDateTime dataNascimento;
    @Column(length = 14, unique = true, nullable = false)
    private String documento;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento")
    private TipoDocumento tipoDocumento;
    @Column
    private BigDecimal renda;
    @Column
    private String telefone;

}
