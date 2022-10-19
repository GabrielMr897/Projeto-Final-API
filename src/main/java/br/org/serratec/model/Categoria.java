package br.org.serratec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Long idCategoria;

	
	private String nomeCategoria;
	

	private String descricaoCategoria;


    public Long getIdCategoria() {
        return idCategoria;
    }


    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }


    public String getNomeCategoria() {
        return nomeCategoria;
    }


    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }


    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }


    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }


	
		
	
}
