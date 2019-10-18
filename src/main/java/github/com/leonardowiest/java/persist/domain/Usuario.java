package github.com.leonardowiest.java.persist.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "tx_nome")
    private String nome;

    @Column(name = "tx_login")
    private String login;

}
