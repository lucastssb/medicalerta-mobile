package com.probex.medicalerta.adapter;

public class Usuario {


    private int id;
    private String nome;
    private long dataNascimento;
    private String foto;

    public Usuario(int id, String nome, long dataNascimento, String foto) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.foto = foto;
    }

    public Usuario(String nome, long dataNascimento, String foto) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.foto = foto;
    }

    public Usuario(){

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

    public long getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(long dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
