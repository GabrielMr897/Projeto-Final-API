package br.org.serratec.dto;

import java.time.LocalDate;

import br.org.serratec.enums.Status;
import br.org.serratec.model.Pedido;

public class PedidoInserirDTO {

    private LocalDate dataEntrega;

    private LocalDate dataEnvio;

    private Status status;

    private ClientePedidoInserirDTO cliente;

    public PedidoInserirDTO(Pedido pedido) {
        this.dataEntrega = pedido.getDataEntrega();
        this.dataEnvio = pedido.getDataEnvio();
        this.status = pedido.getStatus();
        this.cliente = new ClientePedidoInserirDTO(pedido.getCliente());
    }

    public PedidoInserirDTO() {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ClientePedidoInserirDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClientePedidoInserirDTO cliente) {
        this.cliente = cliente;
    }

}
