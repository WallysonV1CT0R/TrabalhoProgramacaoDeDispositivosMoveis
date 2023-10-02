package com.example.trabalhopaulinho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.trabalhopaulinho.model.Cliente;
import com.example.trabalhopaulinho.model.Item;

import java.util.ArrayList;

public class ConsultaPedidoActivity extends AppCompatActivity {
    private Button btPesquisar;
    private AutoCompleteTextView atvPedido;
    private TextView tvPedidoVazio;
    private ArrayList<Item> listaPedidos;
    Cliente c = new Cliente();
    Item i = new Item();
    CadastroDePedidosActivity p = new CadastroDePedidosActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_pedido);
        btPesquisar = findViewById(R.id.btPesquisar);
        atvPedido = findViewById(R.id.atvPedido);
        tvPedidoVazio = findViewById(R.id.tvPedidoVazio);

        listaPedidos = Controller.getInstance().retornarPedidos();

        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesquisarPedido();
            }
        });
    }


    private void pesquisarPedido() {
        String codigoPedido = atvPedido.getText().toString();

        if (codigoPedido.isEmpty()) {
            atvPedido.setError("Informe um código");
            return;
        }


    }
    private void pedidoNaoEncontrado() {
        tvPedidoVazio.setText("Pedido não encontrado.");
    }

    public String carregarPedidos() {
        CadastroDePedidosActivity.TipoPagamento tpPagamento = CadastroDePedidosActivity.TipoPagamento.VAZIO;
        if (tpPagamento == CadastroDePedidosActivity.TipoPagamento.VAZIO) {
            return("Nenhum pedido realizado \uD83D\uDE25 ");
        } else if (tpPagamento == CadastroDePedidosActivity.TipoPagamento.APRAZO) {
            return ("Código pedido:" +
                    "Cliente " + "\n" +
                    "Itens:" + "\n" +
                    "Total: " + "\n" +
                    "Forma de pagamento: Á Prazo \n" +
                    "Total de parcelas:" + "\n" +
                    "Valor Total do Pedido:");
        } else {
            return ("Código pedido:" +
                    "Cliente " + "\n" +
                    "Itens:" + "\n" +
                    "Total: " + "\n" +
                    "Forma de pagamento: Á Vista \n" +
                    "Valor Total do Pedido:");
        }
    }
}




