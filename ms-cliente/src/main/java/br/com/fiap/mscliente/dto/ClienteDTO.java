package br.com.fiap.mscliente.dto;

import br.com.fiap.mscliente.model.Cliente;

import javax.validation.constraints.NotNull;

public class ClienteDTO {
    private Long id;

    @NotNull(message = "Campo Obrigatorio")
    private String name;

    @NotNull(message = "Campo Obrigatorio")
    private String email;

    @NotNull(message = "Campo Obrigatorio")
    private String endereco;

    @NotNull(message = "Campo Obrigatorio")
    private String telefone;

    @NotNull(message = "Campo Obrigatorio")
    private String senha;

    private ClienteDTO() {

    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.name = cliente.getName();
        this.email = cliente.getEmail();
        this.endereco = cliente.getEndereco();
        this.telefone = cliente.getTelefone();
        this.senha = cliente.getSenha();
    }

    public ClienteDTO(Long id, String name, String email, String endereco, String telefone, String senha) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
