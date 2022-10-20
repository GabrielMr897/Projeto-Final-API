package br.org.serratec.dto;

import java.time.LocalDate;

import br.org.serratec.model.Categoria;
import br.org.serratec.model.Produto;

public class ProdutoInserirDTO {
    private Long idProduto;
    private String nome;
    private String descricao;
    private Integer quantidadeEstoque;
    private LocalDate dataCadastro;
    private Double valorUnitario;
    private Categoria categoria;
    
    
    
    public ProdutoInserirDTO() {
    }
    
    public ProdutoInserirDTO(Produto produto) {
        this.idProduto = produto.getIdProduto();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.dataCadastro = produto.getDataCadastro();
        this.valorUnitario = produto.getValorUnitario();
        this.categoria = produto.getCategoria();
    }
   
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public Double getValorUnitario() {
        return valorUnitario;
    }
    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }
}
