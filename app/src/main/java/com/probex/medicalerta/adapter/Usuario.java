package com.probex.medicalerta.adapter;

public class Usuario {


    private int id;
    private String nome;
    private int idade;
    private String foto;

    public Usuario(int id, String nome, int idade, String foto) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.foto = foto;
    }

    public Usuario(String nome, int idade, String foto) {
        this.nome = nome;
        this.idade = idade;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
