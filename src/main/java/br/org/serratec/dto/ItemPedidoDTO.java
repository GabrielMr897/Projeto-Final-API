package br.org.serratec.dto;

import br.org.serratec.model.Pedido;
import br.org.serratec.model.Produto;

public class ItemPedidoDTO {

    private Produto produto;
    private Pedido pedido;
    private Integer quantidade;
    private Integer precoVenda;
}
