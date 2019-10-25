package br.udesc.comandaappadm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.udesc.comandaappadm.R;
import br.udesc.comandaappadm.model.ConfigProduto;

public class ConfigValue extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Intent it;
    private TextView txtIdConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_value);

        initComponentes();
        initFirebase();
        getDataExtras();
//        initListners();
//        eventDataBase();


    }

    private void getDataExtras() {
        if (it.hasExtra("ConfigPoduto")) {
            String config = it.getExtras().getString("ConfigPoduto");
            txtIdConfig.setText(config);
        }
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(ConfigValue.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseDatabase.setPersistenceEnabled(true);     // além do Firebase, tbm atualiza no banco interno do meu aplicativo.
        databaseReference = firebaseDatabase.getReference();
    }

    private void initComponentes() {
        txtIdConfig = findViewById(R.id.txtIdConfig);
        it = getIntent();
    }

}
