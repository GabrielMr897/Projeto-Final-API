package br.org.serratec.dto;

import java.time.LocalDate;

import br.org.serratec.enums.Status;
import br.org.serratec.model.Cliente;
import br.org.serratec.model.Pedido;

public class PedidoInserirDTO {

    private LocalDate dataPedido;

    private LocalDate dataEntrega;

    private LocalDate dataEnvio;

    private Status status;

    private Cliente cliente;

    public PedidoInserirDTO(Pedido pedido) {
        this.dataPedido = pedido.getDataPedido();
        this.dataEntrega = pedido.getDataEntrega();
        this.dataEnvio = pedido.getDataEnvio();
        this.status = pedido.getStatus();
        this.cliente = pedido.getCliente();
    }

    public PedidoInserirDTO() {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
