package com.example.trabalhopaulinho.model;

import java.util.ArrayList;

public class Item {
    private Double valor;
    private String descricao;
    private Integer codProduto;
    private Double quantidade;
    private ArrayList<Item> cdgPedido;
    private ArrayList <Cliente>listaClientes;
    private ArrayList<Item>listaItem;

    public Item() {
    }


    public Item(Double valor, String descricao, Integer codProduto, Double quantidade, ArrayList<Item> cdgPedido, ArrayList<Cliente> listaClientes, ArrayList<Item> listaItem) {
        this.valor = valor;
        this.descricao = descricao;
        this.codProduto = codProduto;
        this.quantidade = quantidade;
        this.cdgPedido = cdgPedido;
        this.listaClientes = listaClientes;
        this.listaItem = listaItem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Integer codProduto) {
        this.codProduto = codProduto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public ArrayList<Item> getCdgPedido() {
        return cdgPedido;
    }

    public void setCdgPedido(ArrayList<Item> cdgPedido) {
        this.cdgPedido = cdgPedido;
    }
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Item> getListaItem() {
        return listaItem;
    }

    public void setListaItem(ArrayList<Item> listaItem) {
        this.listaItem = listaItem;
    }

    public void setListaClientes(String toString) {
    }

    public void setListaItem(String toString) {
    }

    public void setCdgPedido(String toString) {
    }
}
