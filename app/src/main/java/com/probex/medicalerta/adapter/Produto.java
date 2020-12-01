package com.probex.medicalerta.adapter;

public class Produto {

    private String id;
    private String intervalo;
    private String valorIntervalo;
    private String qtdDias;
    private String valorQtdDias;
    private String dataInicial;
    private String valorDataInicial;
    private String dataFinal;
    private String valorDataFinal;

    public Produto(String id, String intervalo, String valorIntervalo, String qtdDias, String valorQtdDias, String dataInicial, String valorDataInicial, String dataFinal, String valorDataFinal) {
        this.id = id;
        this.intervalo = intervalo;
        this.valorIntervalo = valorIntervalo;
        this.qtdDias = qtdDias;
        this.valorQtdDias = valorQtdDias;
        this.dataInicial = dataInicial;
        this.valorDataInicial = valorDataInicial;
        this.dataFinal = dataFinal;
        this.valorDataFinal = valorDataFinal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(String intervalo) {
        this.intervalo = intervalo;
    }

    public String getValorIntervalo() {
        return valorIntervalo;
    }

    public void setValorIntervalo(String valorIntervalo) {
        this.valorIntervalo = valorIntervalo;
    }

    public String getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(String qtdDias) {
        this.qtdDias = qtdDias;
    }

    public String getValorQtdDias() {
        return valorQtdDias;
    }

    public void setValorQtdDias(String valorQtdDias) {
        this.valorQtdDias = valorQtdDias;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getValorDataInicial() {
        return valorDataInicial;
    }

    public void setValorDataInicial(String valorDataInicial) {
        this.valorDataInicial = valorDataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getValorDataFinal() {
        return valorDataFinal;
    }

    public void setValorDataFinal(String valorDataFinal) {
        this.valorDataFinal = valorDataFinal;
    }
}
