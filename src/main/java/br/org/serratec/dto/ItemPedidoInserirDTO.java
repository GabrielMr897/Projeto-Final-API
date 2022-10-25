package br.org.serratec.dto;

import br.org.serratec.model.ItemPedido;

public class ItemPedidoInserirDTO {

    private ProdutoItemPedidoInserirDTO produto;

    private PedidoItemPedidoInserirDTO pedido;

    private Integer quantidade;

    private Integer precoVenda;

    public ItemPedidoInserirDTO(ItemPedido itemPedido) {
        this.produto = new ProdutoItemPedidoInserirDTO(itemPedido.getProduto());
        this.pedido = new PedidoItemPedidoInserirDTO(itemPedido.getPedido());
        this.quantidade = itemPedido.getQuantidade();
        this.precoVenda = itemPedido.getPrecoVenda();
    }

    public ItemPedidoInserirDTO() {
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

    public PedidoItemPedidoInserirDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoItemPedidoInserirDTO pedido) {
        this.pedido = pedido;
    }

    public ProdutoItemPedidoInserirDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoItemPedidoInserirDTO produto) {
        this.produto = produto;
    }
}
