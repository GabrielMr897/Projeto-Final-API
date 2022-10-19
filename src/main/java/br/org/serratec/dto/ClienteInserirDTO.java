package br.org.serratec.dto;

import br.org.serratec.model.Cliente;
import br.org.serratec.model.Endereco;

public class ClienteInserirDTO {
	private String nomeUsuario;
	private String email;
	private String senha;
	private String cpf;
	private Endereco endereco;
	
	public ClienteInserirDTO() {
		
	}
	
	public ClienteInserirDTO(Cliente cliente) {
		super();
		this.nomeUsuario = cliente.getNomeUsuario();
		this.email = cliente.getEmail();
		this.senha = cliente.getSenha();
		this.cpf = cliente.getCpf();
		this.endereco = cliente.getEndereco();
	}
	
	

	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
