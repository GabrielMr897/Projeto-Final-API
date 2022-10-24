package br.org.serratec.dto;

import br.org.serratec.model.ItemPedido;

public class ItemPedidoDTO {

    private Long idItemPedido;

    private Integer quantidade;

    private Integer precoVenda;

    private ProdutoItemPedidoDTO produto;

    private PedidoItemPedidoDTO pedido;

    public ItemPedidoDTO(ItemPedido itemPedido) {
        this.idItemPedido = itemPedido.getIdItemPedido();
        this.quantidade = itemPedido.getQuantidade();
        this.produto = new ProdutoItemPedidoDTO(itemPedido.getProduto());
        this.pedido = new PedidoItemPedidoDTO(itemPedido.getPedido());
        this.precoVenda = itemPedido.getPrecoVenda();
    }

    public ItemPedidoDTO() {
    }

    public Long getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(Long idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public ProdutoItemPedidoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoItemPedidoDTO produto) {
        this.produto = produto;
    }

    public Integer getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Integer precoVenda) {
        this.precoVenda = precoVenda;
    }

    public PedidoItemPedidoDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoItemPedidoDTO pedido) {
        this.pedido = pedido;
    }
}
