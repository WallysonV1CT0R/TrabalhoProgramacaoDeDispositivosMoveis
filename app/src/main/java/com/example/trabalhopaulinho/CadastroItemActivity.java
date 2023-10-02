package com.example.trabalhopaulinho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhopaulinho.model.Item;

public class CadastroItemActivity extends AppCompatActivity {

    private EditText edValorProd;
    private EditText edCodigoProd;
    private EditText idDesc;
    private Button btCadastroItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_itens);

        edValorProd = findViewById(R.id.edValorProd);
        btCadastroItem = findViewById(R.id.btCadastroItem);
        edCodigoProd = findViewById(R.id.edCodigoProd);
        idDesc = findViewById(R.id.idDesc);


        btCadastroItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarItem();
            }
        });
    }
    private void salvarItem() {
        if(edValorProd.getText().toString().isEmpty()){
            edValorProd.setError("Informe o Valor do produto!");
            return;
        }
        if(edCodigoProd.getText().toString().isEmpty()){
            edCodigoProd.setError("Informe o Código do produto!");
            return;
        }
        if(idDesc.getText().toString().isEmpty()){
            edCodigoProd.setError("Informe a descrição do produto!");
            return;
        }

        Item item= new Item();
        item.setDescricao(idDesc.getText().toString());
        item.setValor(Double.parseDouble(edValorProd.getText().toString()));
        item.setCodProduto(Integer.parseInt(edCodigoProd.getText().toString()));


        Controller.getInstance().salvarItem(item);

        Toast.makeText(CadastroItemActivity.this,
                "Item Cadastrado com Sucesso!!",
                Toast.LENGTH_LONG).show();

        this.finish();
    }
}