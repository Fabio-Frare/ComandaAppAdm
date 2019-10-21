package br.udesc.comandaappadm.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.udesc.comandaappadm.R;
import br.udesc.comandaappadm.model.ConfigProduto;

public class CadastroConfiguracaoProduto extends AppCompatActivity {

    private EditText editNomeConfiguracao;
    private Button btnNovo, btnAtualizar, btnApagar;
    private ListView listViewConfiguracao;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<ConfigProduto> listConfiguracao;
    private ArrayAdapter<ConfigProduto> arrayAdapterConfiguracao;
    private ConfigProduto configuracaoSelecionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao_produto);

        initComponentes();
        initFirebase();
        initListners();
        eventDataBase();
    }

    private void initComponentes() {
        editNomeConfiguracao = findViewById(R.id.editNomeConfiguracao);
        listViewConfiguracao = findViewById(R.id.listViewConfiguracao);
        btnNovo = findViewById(R.id.btnNovoConfig);
        btnAtualizar = findViewById(R.id.btnAtualizarConfig);
        btnApagar = findViewById(R.id.btnApagarConfig);
        listConfiguracao = new ArrayList<>();
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(CadastroConfiguracaoProduto.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);     // além do Firebase, tbm atualiza no banco interno do meu aplicativo.
        databaseReference = firebaseDatabase.getReference();
    }

    private void initListners() {
        listViewConfiguracao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                configuracaoSelecionada = (ConfigProduto) adapterView.getItemAtPosition(position);
                editNomeConfiguracao.setText(configuracaoSelecionada.getNomeConfigProduto());
            }
        });

        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfigProduto conf = new ConfigProduto();
                conf.setIdConfigProduto(UUID.randomUUID().toString());
                conf.setNomeConfigProduto(editNomeConfiguracao.getText().toString().trim());
                databaseReference.child("Configuracao").child(conf.getIdConfigProduto()).setValue(conf);
                limparCampos();
            }
        });
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfigProduto conf = new ConfigProduto();
                conf.setIdConfigProduto(configuracaoSelecionada.getIdConfigProduto());
                conf.setNomeConfigProduto(editNomeConfiguracao.getText().toString().trim());
                databaseReference.child("Configuracao").child(conf.getIdConfigProduto()).setValue(conf);
                limparCampos();
            }
        });
        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfigProduto conf = new ConfigProduto();
                conf.setIdConfigProduto(configuracaoSelecionada.getIdConfigProduto());
                databaseReference.child("Configuracao").child(conf.getIdConfigProduto()).removeValue();
                limparCampos();
            }
        });

    }

    private void eventDataBase() {
        databaseReference.child("Configuracao").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listConfiguracao.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    ConfigProduto conf = objSnapshot.getValue(ConfigProduto.class);
                    listConfiguracao.add(conf);
                }
                arrayAdapterConfiguracao = new ArrayAdapter<>(CadastroConfiguracaoProduto.this, android.R.layout.simple_list_item_1, listConfiguracao);
                listViewConfiguracao.setAdapter(arrayAdapterConfiguracao);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void limparCampos() {
        editNomeConfiguracao.setText("");
    }

    private void mensagem(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
