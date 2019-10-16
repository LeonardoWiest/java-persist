package github.com.leonardowiest.java.persist.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "tx_nome")
    private String nome;

}
