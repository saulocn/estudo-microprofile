package com.saulocn.microprofile.dto;

public class MunicipioDTO {

    private Integer id;
    private String nome;
    private Integer populacao;

    public MunicipioDTO(Integer id, String nome, Integer populacao) {
        this.id = id;
        this.nome = nome;
        this.populacao = populacao;
    }

    public MunicipioDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }
}
