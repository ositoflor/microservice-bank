package com.br.banco.usuario.domain;

import com.br.banco.usuario.domain.enums.StatusSolicitacao;
import com.br.banco.usuario.domain.enums.TipoTransacao;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_SOLICITACAO")
@Table(name = "TB_SOLICITACAO")
@ToString
public class Solicitacao {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private String id;
    private String idConta;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;
    private Double valorSolitidado;
    private Double valorDebitado;
    private LocalDateTime localDateTime = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao statusSolicitacao = StatusSolicitacao.PD;
    private Boolean isVerificado = false;
}
