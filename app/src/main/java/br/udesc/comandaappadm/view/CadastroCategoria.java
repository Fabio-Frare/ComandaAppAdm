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
import br.udesc.comandaappadm.model.Categoria;

public class CadastroCategoria extends AppCompatActivity {

    private EditText editNomeCategoria;
    private Button btnNovo, btnAtualizar, btnApagar;
    private ListView listViewCategoria;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<Categoria> listCategoria;
    private ArrayAdapter<Categoria> arrayAdapterCategoria;
    private Categoria categoriaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categoria);

        initComponentes();
        initFirebase();
        initListners();
        eventDataBase();
    }

    private void initComponentes() {
        editNomeCategoria = findViewById(R.id.editNomeCategoria);
        listViewCategoria = findViewById(R.id.listViewCategoria);
        btnNovo = findViewById(R.id.btnNovoConfig);
        btnAtualizar = findViewById(R.id.btnAtualizarConfig);
        btnApagar = findViewById(R.id.btnApagarConfig);
        listCategoria = new ArrayList<>();
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(CadastroCategoria.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);     // além do Firebase, tbm atualiza no banco interno do meu aplicativo.
        databaseReference = firebaseDatabase.getReference();
    }

    private void initListners() {
        listViewCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                categoriaSelecionada = (Categoria) adapterView.getItemAtPosition(position);
                editNomeCategoria.setText(categoriaSelecionada.getNomeCategoria());
            }
        });

        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(UUID.randomUUID().toString());
//                validaDuplicados(cat.getNome());
                cat.setNomeCategoria(editNomeCategoria.getText().toString());
                databaseReference.child("Categoria").child(cat.getIdCategoria()).setValue(cat);
                limparCampos();
            }
        });
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(categoriaSelecionada.getIdCategoria());
                cat.setNomeCategoria(editNomeCategoria.getText().toString().trim());
                databaseReference.child("Categoria").child(cat.getIdCategoria()).setValue(cat);
                limparCampos();
            }
        });
        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(categoriaSelecionada.getIdCategoria());
                databaseReference.child("Categoria").child(cat.getIdCategoria()).removeValue();
                limparCampos();
            }
        });
    }

    private void eventDataBase() {
        databaseReference.child("Categoria").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCategoria.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Categoria cat = objSnapshot.getValue(Categoria.class);
                    listCategoria.add(cat);
                }
                arrayAdapterCategoria = new ArrayAdapter<>(CadastroCategoria.this, android.R.layout.simple_list_item_1, listCategoria);
                listViewCategoria.setAdapter(arrayAdapterCategoria);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private boolean validaDuplicados(String nome) {
//        boolean duplicado = false;
//
//        return duplicado;
//    }

    private void mensagem(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void limparCampos() {
        editNomeCategoria.setText("");
    }

}
