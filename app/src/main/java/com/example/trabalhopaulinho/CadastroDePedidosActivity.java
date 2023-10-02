package com.example.trabalhopaulinho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import android.widget.AdapterView;
import com.example.trabalhopaulinho.model.Cliente;
import com.example.trabalhopaulinho.model.Item;

import java.util.ArrayList;

public class CadastroDePedidosActivity extends AppCompatActivity {
    private RadioGroup rgPag;
    private RadioButton rbVista;
    private RadioButton rbPrazo;
    private AutoCompleteTextView tvAddCliente;
    private AutoCompleteTextView tvAddItem;
    private TextView tvValorTotal;
    private TextView tvCodigoPedido;
    private EditText edQtd;
    private Button btFinalizar;
    private Spinner spParcelas;
    private TextView edValorUnit;
    private ImageButton btAddPedido;
    Item item = new Item();
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Item> listaItem;
    private int numeroPedido=1;


    public enum TipoPagamento {
        AVISTA,
        APRAZO,
        VAZIO
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro_de_pedidos);
        tvAddCliente = findViewById(R.id.tvAddCliente);
        btAddPedido = findViewById(R.id.btAddPedido);
        tvAddItem = findViewById(R.id.tvAddItem);
        tvValorTotal = findViewById(R.id.tvValorTotal);
        edQtd = findViewById(R.id.edQtd);
        edValorUnit = findViewById(R.id.edValorUnit);
        spParcelas = findViewById(R.id.spParcelas);
        rbPrazo = findViewById(R.id.rbPrazo);
        rbVista = findViewById(R.id.rbVista);
        rgPag = findViewById(R.id.rgPag);
        btFinalizar = findViewById(R.id.btFinalizar);
        tvCodigoPedido = findViewById(R.id.tvCodigoPedido);

        carregarClientes();
        carregarItens();
        calcularValorTotal();
        atualizarValorTotal();

        rgPag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                TipoPagamento tpPagamento = TipoPagamento.VAZIO;
                if (rbPrazo.isChecked()) {
                    tpPagamento = TipoPagamento.APRAZO;
                    spParcelas.setVisibility(View.VISIBLE);
                    ItemParcelas();
                    codigoPedido();
                } else {
                    tpPagamento = TipoPagamento.AVISTA;
                    spParcelas.setVisibility(View.INVISIBLE);
                    codigoPedido();
                }

            }

        });
        btAddPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarPedidos();
                limparCampos();

            }
        });
        btFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvAddItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemSelecionado = parent.getItemAtPosition(position).toString();

                for (Item item : listaItem) {
                    if (item.getCodProduto() == Integer.parseInt(itemSelecionado)) {
                        edValorUnit.setText(item.getValor().toString());
                        break;
                    }
                }
            }
        });
    }


    private void ItemParcelas(){
        String[] numeros = new String[12];
        for (int i = 1; i <= 12; i++) {
            numeros[i - 1] = String.valueOf(i + "x");
        }

        String[] parcelas = new String[numeros.length + 1];
        parcelas[0] = "Parcelas";
        System.arraycopy(numeros, 0, parcelas, 1, numeros.length);

        ArrayAdapter adapter = new ArrayAdapter(CadastroDePedidosActivity.this,
                android.R.layout.simple_dropdown_item_1line,parcelas);

        spParcelas.setAdapter(adapter);
    }
    private void carregarClientes() {
        listaClientes = Controller.getInstance().retornarCliente();
        String[] vetCliente = new String[listaClientes.size()];
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente clientes = listaClientes.get(i);
            vetCliente[i] = clientes.getNome();
            ArrayAdapter adapter = new ArrayAdapter(CadastroDePedidosActivity.this,
                    android.R.layout.simple_dropdown_item_1line, vetCliente);

            tvAddCliente.setAdapter(adapter);
        }
    }
        private void carregarItens() {
            listaItem = Controller.getInstance().retornarItens();
            String[] vetItens = new String[listaItem.size()];
            for (int i = 0; i < listaItem.size(); i++) {
                Item item = listaItem.get(i);
                vetItens[i] =Integer.toString(item.getCodProduto());
                ArrayAdapter adapter = new ArrayAdapter(CadastroDePedidosActivity.this,
                        android.R.layout.simple_dropdown_item_1line, vetItens);

                tvAddItem.setAdapter(adapter);
            }
    }
    private void salvarPedidos() {
        if(tvAddCliente.getText().toString().isEmpty()){
            tvAddCliente.setError("Informe o Cliente do pedido!");
            return;
        }
        if(tvAddItem.getText().toString().isEmpty()){
            tvAddItem.setError("Informe o item do pedido!");
            return;
        }

        Item pedido = new Item();
        pedido.setListaClientes(tvAddCliente.getText().toString());
        pedido.setListaItem(tvAddItem.getText().toString());
        pedido.setCdgPedido(tvCodigoPedido.getText().toString());


        Controller.getInstance().salvarPedido(pedido);

            Toast.makeText(CadastroDePedidosActivity.this,
                    "Pedido  realizado com Sucesso!!",
                    Toast.LENGTH_LONG).show();

    }
    private void limparCampos(){
        tvAddItem.setText(" ");
        edValorUnit.setText(" ");

    }
    private void codigoPedido() {
        tvCodigoPedido.setText(String.valueOf(numeroPedido));
        numeroPedido++;
    }
    private void calcularValorTotal() {
        atualizarValorTotal();
        if (edValorUnit != null && !edQtd.getText().toString().isEmpty()) {
            String valorUnitarioStr = edValorUnit.getText().toString();
            double valorUnitario = Double.parseDouble(valorUnitarioStr);
            String quantidadeStr = edQtd.getText().toString();
            double quantidade = Double.parseDouble(quantidadeStr);
            double valorTotal = valorUnitario * quantidade;

            if (rbPrazo.isChecked()) {

                valorTotal *= 1.05;
            } else {

                valorTotal *= 0.95;
            }

            tvValorTotal.setText(String.format("%.2f", valorTotal));

            edQtd.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    atualizarValorTotal();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

            private void atualizarValorTotal() {

                String quantidadeStr = edQtd.getText().toString();


                if (!quantidadeStr.isEmpty()) {

                    double quantidade = Double.parseDouble(quantidadeStr);


                    String valorUnitarioStr = edValorUnit.getText().toString();


                    if (!valorUnitarioStr.isEmpty()) {

                        double valorUnitario = Double.parseDouble(valorUnitarioStr);


                        double valorTotal = valorUnitario * quantidade;


                        tvValorTotal.setText(String.format("%.2f", valorTotal));
                    }
                }

            }
        }


