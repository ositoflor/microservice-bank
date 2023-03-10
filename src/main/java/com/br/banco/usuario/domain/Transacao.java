package com.br.banco.usuario.domain;

import com.br.banco.usuario.domain.enums.TipoTransacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_TRANSACOES")
@Table(name = "TB_TRANSACOES")
public class Transacao {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private String id;
    @Column
    private Double valor;
    @Column(name = "tipo_transacao")
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "data_hora_transacao")
    private LocalDateTime dataHoraTransacao;
    @Column(name = "id_cliente_destino")
    private String clienteDestinoTransacao;
    @Column(name = "id_cliente_origem")
    private String clienteOrigemTransacao;
    @Column(name = "id_conta")
    private String idConta;

}
