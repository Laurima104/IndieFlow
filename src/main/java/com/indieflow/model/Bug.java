package com.indieflow.model;

import com.indieflow.enums.Status;

public class Bug {
    private int id;
    private String titulo;
    private String descricao;
    private String gravidade;
    private Status status;
    private Projeto projeto;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public String getGravidade(){
        return gravidade;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Projeto getProjeto() {
        return projeto;
    }
}
