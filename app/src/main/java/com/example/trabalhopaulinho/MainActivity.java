package com.example.trabalhopaulinho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btCadastroClientes;
    private Button btCadastroItens;
    private Button btLancamentoPedidos;
    private Button btConsultarPedidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCadastroClientes = findViewById(R.id.btCadastroClientes);
        btCadastroItens = findViewById(R.id.btCadastroItens);
        btLancamentoPedidos = findViewById(R.id.btLancamentoPedidos);
        btConsultarPedidos = findViewById(R.id.btConsultarPedidos);


        btCadastroClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastroClienteActivity.class);
            }
        });
        btCadastroItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastroItemActivity.class);
            }
        });
        btLancamentoPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastroDePedidosActivity.class);

            }
        });
            btConsultarPedidos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {abrirActivity(ConsultaPedidoActivity.class);
                }
            }

        );
    }

    private void abrirActivity(Class<?> activity){
        Intent intent =  new Intent(MainActivity.this,
                activity);
        startActivity(intent);
    }
}