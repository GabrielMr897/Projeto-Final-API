package br.org.serratec.dto;

import br.org.serratec.model.Cliente;

public class ClientePedidoDTO {

    private Long id;

    private String nomeUsuario;

    private String nomeCompleto;

    public ClientePedidoDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nomeUsuario = cliente.getNomeUsuario();
        this.nomeCompleto = cliente.getNomeCompleto();
    }

    public ClientePedidoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}
