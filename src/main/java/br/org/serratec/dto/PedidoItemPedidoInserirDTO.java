package br.org.serratec.dto;

import br.org.serratec.model.Pedido;

public class PedidoItemPedidoInserirDTO {

    private Long idPedido;

    public PedidoItemPedidoInserirDTO(Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
    }

    public PedidoItemPedidoInserirDTO() {
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long id) {
        this.idPedido = id;
    }
}
