package br.udesc.comandaappadm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnCategoria;
    private Button btnConfigProduto;
    private Button btnProduto;
    private Button btnListaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponentes();

    }

    private void initComponentes() {
        btnCategoria = findViewById(R.id.btnCategoria);
        btnConfigProduto = findViewById(R.id.btnConfigProduto);
        btnProduto = findViewById(R.id.btnProduto);
        btnListaProdutos = findViewById(R.id.btnLista);
    }
}
