package com.probex.medicalerta.adapter;

public class Medicamento {

    int id_med;
    int indicacao;                  //codigo para imagem
    String substancia;              //DIPIRONA MONOIDRATADA;BUTILBROMETO DE ESCOPOLAMINA
    String produto;                 //BUSCOPAN COMPOSTO *
    String concentracao;            //10 MG + 250 MG *
    String forma_farmaceutica;      //COM   *
    String quantidade;              //20    *
    String subclasse;               //ANALGÉSICO E ANTITÉRMICO


    public Medicamento() {
    }

    //update
    public Medicamento(int id_med, int indicacao, String substancia, String produto, String concentracao, String forma_farmaceutica, String quantidade, String subclasse) {
        this.id_med = id_med;
        this.indicacao = indicacao;
        this.substancia = substancia;
        this.produto = produto;
        this.concentracao = concentracao;
        this.forma_farmaceutica = forma_farmaceutica;
        this.quantidade = quantidade;
        this.subclasse = subclasse;
    }

    //Insert
    public Medicamento(int indicacao, String substancia, String produto, String concentracao, String forma_farmaceutica, String quantidade, String subclasse) {
        this.indicacao = indicacao;
        this.substancia = substancia;
        this.produto = produto;
        this.concentracao = concentracao;
        this.forma_farmaceutica = forma_farmaceutica;
        this.quantidade = quantidade;
        this.subclasse = subclasse;
    }


    public int getId_med() {
        return id_med;
    }

    public void setId_med(int id_med) {
        this.id_med = id_med;
    }

    public int getIndicacao() {
        return indicacao;
    }

    public void setIndicacao(int indicacao) {
        this.indicacao = indicacao;
    }

    public String getSubstancia() {
        return substancia;
    }

    public void setSubstancia(String substancia) {
        this.substancia = substancia;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getConcentracao() {
        return concentracao;
    }

    public void setConcentracao(String concentracao) {
        this.concentracao = concentracao;
    }

    public String getForma_farmaceutica() {
        return forma_farmaceutica;
    }

    public void setForma_farmaceutica(String forma_farmaceutica) {
        this.forma_farmaceutica = forma_farmaceutica;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getSubclasse() {
        return subclasse;
    }

    public void setSubclasse(String subclasse) {
        this.subclasse = subclasse;
    }

}
