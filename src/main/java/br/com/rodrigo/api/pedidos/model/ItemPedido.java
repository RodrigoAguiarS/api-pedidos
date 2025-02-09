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
public class ItemPedido extends EntidadeBase {

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "preco")
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    public void calcularValorTotal() {
        BigDecimal precoUnitario = this.produto.getPreco();
        BigDecimal quantidade = BigDecimal.valueOf(this.getQuantidade());
        BigDecimal valorTotalItem = precoUnitario.multiply(quantidade);
        this.setValorTotal(valorTotalItem);
    }
}
