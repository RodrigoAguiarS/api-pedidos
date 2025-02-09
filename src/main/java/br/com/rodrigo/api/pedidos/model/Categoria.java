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
public class Categoria extends EntidadeBase {

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;
}
