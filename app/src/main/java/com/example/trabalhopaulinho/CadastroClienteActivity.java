package com.example.trabalhopaulinho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalhopaulinho.model.Cliente;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText edNmCliente;
    private EditText edCpf;
    private Button btCadastrar;
    private TextView tvErroCampoNum;
    private TextView tvCampoVazio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        edNmCliente = findViewById(R.id.edNmCliente);
        edCpf = findViewById(R.id.edCpf);
        btCadastrar = findViewById(R.id.btCadastrar);
        tvErroCampoNum = findViewById(R.id.tvErroCampoNum);
        tvCampoVazio = findViewById(R.id.tvCampoVazio);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCliente();
            }
        });
    }

    private void salvarCliente() {
        if(edNmCliente.getText().toString().isEmpty()){
            edNmCliente.setError("Informe o Nome do cliente!");
            return;
        }
        if(edCpf.getText().toString().isEmpty()){
            edCpf.setError("Informe o CPF do cliente!");
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNome(edNmCliente.getText().toString());
        cliente.setCpf(edCpf.getText().toString());


        Controller.getInstance().salvarCliente(cliente);

        Toast.makeText(CadastroClienteActivity.this,
                "Cliente Cadastrado com Sucesso!!",
                Toast.LENGTH_LONG).show();

        this.finish();
    }


    }
