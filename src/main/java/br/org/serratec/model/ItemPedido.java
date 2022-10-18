package br.org.serratec.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
    
    @EmbeddedId
    @Column(name = "id_item_pedido")
    private ItemPedidoPK id = new ItemPedidoPK();

    private Integer quantidade;

    @Column(name = "preco_venda")
    private Integer precoVenda;

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Integer precoVenda) {
        this.precoVenda = precoVenda;
    }
    
}
