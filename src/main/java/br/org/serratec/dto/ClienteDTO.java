package br.org.serratec.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.org.serratec.model.Cliente;
import br.org.serratec.model.Endereco;

public class ClienteDTO {

    private Long id;
    @Email(message = " Digite um email válido")
    private String email;

    @NotBlank(message = " Você deve digitar um nome de usuário válido")
    private String nomeUsuario;

    @NotBlank(message = " Você deve digitar um nome")
    private String nomeCompleto;

    @NotBlank(message = " Você deve digitar uma senha")
    @Size(min = 8)
    private String senha;

    @CPF(message = " Você deve digitar um cpf válido")
    private String cpf;

    @NotBlank(message = " Você deve digitar um telefone válido")
    @Size(min = 12)
    private String telefone;

    @NotNull(message = " Você deve digitar uma data correta")
    @Past(message = " Digite uma data de nascimento válida.")
    private LocalDate dataNascimento;
    private Endereco endereco;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.email = cliente.getEmail();
        this.nomeUsuario = cliente.getNomeUsuario();
        this.nomeCompleto = cliente.getNomeCompleto();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.dataNascimento = cliente.getDataNascimento();
        this.endereco = cliente.getEndereco();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
