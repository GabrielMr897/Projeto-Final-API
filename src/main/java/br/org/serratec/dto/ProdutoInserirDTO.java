package br.org.serratec.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.org.serratec.model.Categoria;
import br.org.serratec.model.Produto;

public class ProdutoInserirDTO {
    private Long idProduto;

    @NotBlank(message = " Por favor, preencha o nome do produto.")
    private String nome;

    @NotNull(message = " Por favor, preencha o nome do produto.")
    @Size(max = 100, message = "Tamanho máximo para descrição de 100 caracteres.")
    private String descricao;

    @NotBlank(message = " Por favor, preencha a quantidade de produtos em estoque.")
    private Integer quantidadeEstoque;

    @NotBlank(message = " Por favor, preencha a data de cadastro do produto.")
    private LocalDate dataCadastro;

    @NotBlank(message = " Por favor, preencha o valor unitário do produto.")
    private Double valorUnitario;

    @NotBlank(message = " Por favor, preencha o nome da categoria do produto.")
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
