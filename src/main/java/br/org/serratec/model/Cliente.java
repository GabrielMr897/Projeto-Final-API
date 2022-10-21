package br.org.serratec.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Email(message = " Digite um email válido")
    private String email;

    @NotBlank(message = " Você deve digitar um nome de usuário válido")
    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @NotBlank(message = " Você deve digitar um nome")
    @Column(name = "nome_completo")
    private String nomeCompleto;

    @NotBlank(message = " Você deve digitar uma senha")
    @Size(min = 8)
    private String senha;

    @CPF(message = " Você deve digitar um cpf válido")
    private String cpf;

    @NotBlank(message = " Você deve digitar um telefone válido")
    private String telefone;

    @NotNull(message = "você deve digitar uma data correta")
    @Past(message = " Digite uma data de nascimento válida.")
    @Column(name = "data_nasc")
    private LocalDate dataNascimento;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @Override
    public String toString() {
        return "Email: " + email + "\nNome de Usuario: " + nomeUsuario + "\nNome Completo: " + nomeCompleto + "\nCpf: "
                + cpf + "\nTelefone: " + telefone + "\nData de Nascimento: " + dataNascimento;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
