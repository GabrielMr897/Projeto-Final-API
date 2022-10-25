package br.org.serratec.dto;

import br.org.serratec.model.Cliente;

public class ClientePedidoInserirDTO {
    
    private Long id;

    public ClientePedidoInserirDTO(Cliente cliente) {
        this.id = cliente.getId();
    }

    public ClientePedidoInserirDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    
}
