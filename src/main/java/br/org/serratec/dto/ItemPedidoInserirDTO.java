package br.org.serratec.dto;

import br.org.serratec.model.ItemPedido;
import br.org.serratec.model.Pedido;
import br.org.serratec.model.Produto;

public class ItemPedidoInserirDTO {

    private Long idItemPedido;

    private Produto produto;

    private Pedido pedido;

    private Integer quantidade;

    private Integer precoVenda;

    public ItemPedidoInserirDTO(ItemPedido itemPedido) {
        this.idItemPedido = itemPedido.getIdItemPedido();
        this.produto = itemPedido.getProduto();
        this.pedido = itemPedido.getPedido();
        this.quantidade = itemPedido.getQuantidade();
        this.precoVenda = itemPedido.getPrecoVenda();
    }

    public ItemPedidoInserirDTO() {
    }

    public Long getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(Long idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
