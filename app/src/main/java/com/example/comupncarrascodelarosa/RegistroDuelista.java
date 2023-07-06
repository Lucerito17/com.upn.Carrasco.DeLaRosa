package com.example.comupncarrascodelarosa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.comupncarrascodelarosa.BD.AppDatabase;
import com.example.comupncarrascodelarosa.Clases.Duelistas;
import com.example.comupncarrascodelarosa.Repositories.DuelistaRepository;
import com.example.comupncarrascodelarosa.Utilities.RetrofitU;
import com.google.gson.Gson;

import retrofit2.Retrofit;

public class RegistroDuelista extends AppCompatActivity {
    Retrofit dRetrofit;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_duelista);

        EditText etNombreDuelista = findViewById(R.id.etNombreCartas);
        Button btnRegistrado = findViewById(R.id.btnRegistradoCartas);


        dRetrofit = RetrofitU.build();
        btnRegistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase database = AppDatabase.getInstance(context);
                DuelistaRepository duelistaRepository = database.duelistaRepository();

                int lastId = duelistaRepository.getLastId();

                Duelistas duelistas = new Duelistas();
                duelistas.setId(lastId + 1);
                duelistas.setNombre(etNombreDuelista.getText().toString());

                duelistaRepository.create(duelistas);
                Log.i("MAIN_APP: DB", new Gson().toJson(duelistas));
                Intent intent = new Intent(RegistroDuelista.this, ListaDuelistaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}