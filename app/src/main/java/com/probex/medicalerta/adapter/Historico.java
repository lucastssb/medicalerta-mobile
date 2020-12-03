package com.probex.medicalerta.adapter;

public class Historico {

    private int id_historico;
    private int id_medicamento;
    private int indicacao;                  //codigo para imagem
    private String substancia;              //DIPIRONA MONOIDRATADA;BUTILBROMETO DE ESCOPOLAMINA
    private String produto;                 //BUSCOPAN COMPOSTO *
    private String concentracao;            //10 MG + 250 MG *
    private String forma_farmaceutica;      //COM   *
    private String quantidade;              //20    *
    private String subclasse;               //ANALGÉSICO E ANTITÉRMICO
    private long data_inicial;
    private long data_final;
    private int intervalo;

    public Historico() {
    }

    public Historico(int id_medicamento, int indicacao, String substancia, String produto, String concentracao, String forma_farmaceutica, String quantidade, String subclasse, long data_inicial, long data_final, int intervalo) {
        this.id_medicamento = id_medicamento;
        this.indicacao = indicacao;
        this.substancia = substancia;
        this.produto = produto;
        this.concentracao = concentracao;
        this.forma_farmaceutica = forma_farmaceutica;
        this.quantidade = quantidade;
        this.subclasse = subclasse;
        this.data_inicial = data_inicial;
        this.data_final = data_final;
        this.intervalo = intervalo;
    }

    public Historico(int id_historico, int id_medicamento, int indicacao, String substancia, String produto, String concentracao, String forma_farmaceutica, String quantidade, String subclasse, long data_inicial, long data_final, int intervalo) {
        this.id_historico = id_historico;
        this.id_medicamento = id_medicamento;
        this.indicacao = indicacao;
        this.substancia = substancia;
        this.produto = produto;
        this.concentracao = concentracao;
        this.forma_farmaceutica = forma_farmaceutica;
        this.quantidade = quantidade;
        this.subclasse = subclasse;
        this.data_inicial = data_inicial;
        this.data_final = data_final;
        this.intervalo = intervalo;
    }

    public int getId_historico() {
        return id_historico;
    }

    public void setId_historico(int id_historico) {
        this.id_historico = id_historico;
    }

    public int getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(int id_medicamento) {
        this.id_medicamento = id_medicamento;
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

    public long getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(long data_inicial) {
        this.data_inicial = data_inicial;
    }

    public long getData_final() {
        return data_final;
    }

    public void setData_final(long data_final) {
        this.data_final = data_final;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }
}
