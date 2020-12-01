package com.probex.medicalerta.adapter;

public class Notificacao {
    private int id;
    private String nome;
    private String descricao;

    public Notificacao(int id, String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Notificacao(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Notificacao() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
