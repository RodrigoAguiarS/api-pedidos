package br.com.rodrigo.api.pedidos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Perfil extends EntidadeBase {

    public final static Long GERENCIA = 3L;

    public final static Long CLIENTE = 2L;

    public final static Long OPERADOR = 1L;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "ativo")
    private Boolean ativo;
}
