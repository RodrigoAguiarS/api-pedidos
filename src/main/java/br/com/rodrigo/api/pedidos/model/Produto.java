package br.com.rodrigo.api.pedidos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Produto extends EntidadeBase {

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(nullable = false, unique = true)
    private String codigoBarras;

    @Column(name = "imagem_url")
    private String imagemUrl;

    @Column(name = "quantidade")
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}