package com.example.trabalhopaulinho;

import com.example.trabalhopaulinho.model.Cliente;
import com.example.trabalhopaulinho.model.Item;

import java.util.ArrayList;

public class Controller {
    private static Controller instancia;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Item> listaItens;
    private ArrayList<Item> listaPedidos;

    public static Controller getInstance(){
        if(instancia == null) {
            return instancia = new Controller();
        }else {
            return instancia;
        }
    }
    private Controller(){
        listaClientes = new ArrayList<>();
        listaItens = new ArrayList<>();
        listaPedidos = new ArrayList<>();
        instancia = this;
    }
    public void salvarCliente(Cliente cliente){

        listaClientes.add(cliente);
    }
    public ArrayList<Cliente> retornarCliente(){
        return listaClientes;
    }

    public void salvarItem(Item item){

        listaItens.add(item);
    }
    public ArrayList<Item> retornarItens(){
        return listaItens;
    }

    public void salvarPedido(Item pedido){

        listaPedidos.add(pedido);
    }
    public ArrayList<Item> retornarPedidos(){
        return listaPedidos;
    }
}
