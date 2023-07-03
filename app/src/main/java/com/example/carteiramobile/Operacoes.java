package com.example.carteiramobile;

public class Operacoes {
    private int id;
    private String ativo;
    private int quantidade;
    private int valor;
    private String data;

    // Construtores


    public Operacoes(int id, String ativo, int quantidade, int valor, String data) {
        this.id = id;
        this.ativo = ativo;
        this.quantidade = quantidade;
        this.valor = valor;
        this.data = data;
    }

    public Operacoes() {

    }

    // toString

    @Override
    public String toString() {
        return id +
                ", " + ativo +
                ", qtd= " + quantidade +
                ", valor= " + valor +
                ", data= " + data;
    }


    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
