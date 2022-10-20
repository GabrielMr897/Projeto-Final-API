package br.org.serratec.dto;

import br.org.serratec.model.Endereco;



public class EnderecoInserirDTO {

    private Integer numero;
    private String complemento;
    private String cep;

    public EnderecoInserirDTO() {
    }

    public EnderecoInserirDTO(Endereco endereco) {
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.cep = endereco.getCep();
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
