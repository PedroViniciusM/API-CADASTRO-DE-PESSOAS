package com.api.cadastropessoas.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_CADASTRO_PESSOAS")
public class CadastroPessoasModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //ID da pessoa gerado automaticamente
    private UUID id;

    @Column(nullable = false, length = 45) //vai ter valor, valor falso (existem pessoas com o mesmo nome), tamanho de no máximo 25 caracteres
    private String Nome; //Nome da Pessoa

    @Column(nullable = false,  length = 10)
    private String dataNascimento; //Data de nascimento

    @Column(nullable = false, length = 100)
    private String endereco;

    @Column(nullable = false,  length = 100)
    private String logradouro;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(nullable = false,  length = 5)
    private String numero;

    @Column(nullable = false,  length = 40)
    private String cidade;

    @Column(nullable = false)
    private LocalDateTime registrationDate; //Data do registro


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
