package br.org.serratec.dto;

import java.time.LocalDate;

import br.org.serratec.model.Pedido;

public class PedidoItemPedidoDTO {

    private Long idPedido;

    private LocalDate dataPedido;

    private LocalDate dataEntrega;

    private LocalDate dataEnvio;

    public PedidoItemPedidoDTO(Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
        this.dataPedido = pedido.getDataPedido();
        this.dataEntrega = pedido.getDataEntrega();
        this.dataEnvio = pedido.getDataEnvio();
    }

    public PedidoItemPedidoDTO() {
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
