package br.org.serratec.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Email(message = "digite um email valido")
    private String email;

    @NotBlank(message = "você deve digitar um nome de usuário valido")
    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @NotBlank(message = "você deve digitar um nome")
    @Column(name = "nome_completo")
    private String nomeCompleto;

    @NotBlank(message = "você deve digitar uma senha")
    @Size(min = 8)
    private String senha;

    @CPF(message = "você deve digitar um cpf valido")
    private String cpf;

    @NotBlank(message = "você deve digitar um telefone valido")
    @Size(min = 12)
    private String telefone;

    @NotNull(message = "você deve digitar uma data correta")
    @Past(message = " Digite uma data de nascimento válida.")
    @Column(name = "data_nasc")
    private LocalDate dataNascimento;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedido;

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

    public List<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }

}
