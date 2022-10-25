package br.org.serratec.dto;

import br.org.serratec.model.Produto;

public class ProdutoItemPedidoInserirDTO {

    private Long idProduto;

    public ProdutoItemPedidoInserirDTO(Produto produto) {
        this.idProduto = produto.getIdProduto();
    }

    public ProdutoItemPedidoInserirDTO() {
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }
}
