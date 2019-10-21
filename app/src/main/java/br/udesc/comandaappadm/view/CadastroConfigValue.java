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
import br.udesc.comandaappadm.model.ConfigValues;

public class CadastroConfigValue extends AppCompatActivity {

    private EditText editNomeConfigValue, editAcrescimoPreco;
    private Button btnNovo, btnAtualizar, btnApagar;
    private ListView listViewConfigValue;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<ConfigValues> listConfigValue;
    private ArrayAdapter<ConfigValues> arrayAdapterConfigValue;
    private ConfigValues configValueSelecionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_value);

        initComponentes();
        initFirebase();
        initListners();
        eventDataBase();
    }

    private void initComponentes() {
        editNomeConfigValue = findViewById(R.id.editNomeConfigValue);
        editAcrescimoPreco = findViewById(R.id.editAcrescimoConfigValue);
        listViewConfigValue = findViewById(R.id.listViewConfigValue);
        btnNovo = findViewById(R.id.btnNovoConfigValue);
        btnAtualizar = findViewById(R.id.btnAtualizarConfigValue);
        btnApagar = findViewById(R.id.btnApagarConfigValue);
        listConfigValue = new ArrayList<>();
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(CadastroConfigValue.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);     // além do Firebase, tbm atualiza no banco interno do meu aplicativo.
        databaseReference = firebaseDatabase.getReference();
    }

    private void initListners() {
        listViewConfigValue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                configValueSelecionada = (ConfigValues) adapterView.getItemAtPosition(position);
                editNomeConfigValue.setText(configValueSelecionada.getNomeConfigValue());
                editAcrescimoPreco.setText(configValueSelecionada.getAcrescimoPreco());
            }
        });

        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfigValues value = new ConfigValues();
                value.setIdConfigValue(UUID.randomUUID().toString());
                value.setNomeConfigValue(editNomeConfigValue.getText().toString().trim());
                value.setAcrescimoPreco(editAcrescimoPreco.getText().toString().trim());
                databaseReference.child("ConfValue").child(value.getIdConfigValue()).setValue(value);
                limparCampos();
            }
        });
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfigValues value = new ConfigValues();
                value.setIdConfigValue(configValueSelecionada.getIdConfigValue());
                value.setNomeConfigValue(editNomeConfigValue.getText().toString().trim());
                value.setAcrescimoPreco(editAcrescimoPreco.getText().toString().trim());
                databaseReference.child("ConfValue").child(value.getIdConfigValue()).setValue(value);
                limparCampos();
            }
        });
        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfigValues value = new ConfigValues();
                value.setIdConfigValue(configValueSelecionada.getIdConfigValue());
                databaseReference.child("ConfValue").child(value.getIdConfigValue()).removeValue();
                limparCampos();
            }
        });
    }

    private void eventDataBase() {
        databaseReference.child("ConfValue").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listConfigValue.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    ConfigValues value = objSnapshot.getValue(ConfigValues.class);
                    listConfigValue.add(value);
                }
                arrayAdapterConfigValue = new ArrayAdapter<>(CadastroConfigValue.this, android.R.layout.simple_list_item_1, listConfigValue);
                listViewConfigValue.setAdapter(arrayAdapterConfigValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void limparCampos() {
        editNomeConfigValue.setText("");
        editAcrescimoPreco.setText("");
    }

}
