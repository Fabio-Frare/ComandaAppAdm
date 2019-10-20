package br.udesc.comandaappadm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.udesc.comandaappadm.R;

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
        initListners();


    }

    private void initComponentes() {
        btnCategoria = findViewById(R.id.btnCategoria);
        btnConfigProduto = findViewById(R.id.btnConfigProduto);
        btnProduto = findViewById(R.id.btnProduto);
        btnListaProdutos = findViewById(R.id.btnLista);
    }

    private void initListners() {
        btnCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CadastroCategoria.class);
                startActivity(it);
            }
        });
    }


}
