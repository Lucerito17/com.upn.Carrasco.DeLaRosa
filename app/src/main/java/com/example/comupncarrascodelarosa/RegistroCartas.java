package com.example.comupncarrascodelarosa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.comupncarrascodelarosa.BD.AppDatabase;
import com.example.comupncarrascodelarosa.Clases.Cartas;
import com.example.comupncarrascodelarosa.Repositories.CartaRepository;
import com.example.comupncarrascodelarosa.Utilities.RetrofitU;

import retrofit2.Retrofit;

public class RegistroCartas extends AppCompatActivity {
    Retrofit retrofitP;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cartas);
        EditText etNombreCartas = findViewById(R.id.etNombreCartas);
        EditText etPuntosA = findViewById(R.id.etPuntosA);
        EditText etPuntosD = findViewById(R.id.etPuntosD);
        Button btnImagen = findViewById(R.id.btnImagen);
        Button btnMapa = findViewById(R.id.btnMapa);
        Button btnRegistradoCartas = findViewById(R.id.btnRegistradoCartas);
        ImageView imgA = findViewById(R.id.imgA);

        retrofitP = RetrofitU.build();
        btnRegistradoCartas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NomCartas = etNombreCartas.getText().toString();
                String PuntosA = etPuntosA.getText().toString();
                String PuntosD = etPuntosD.getText().toString();

                AppDatabase database = AppDatabase.getInstance(context);
                CartaRepository cartasRepository = database. cartaRepository();

                int lastId = cartasRepository.getLastId();

                //Crear un movimiento
                Cartas carta = new Cartas();
                carta.setId(lastId + 1);
                //Llenar
                carta.setNombre(NomCartas);
                carta.setPuntosdeataque(PuntosA);
                carta.setPuntosdefensa(PuntosD);

                cartasRepository.create(carta);

                Intent intent =  new Intent(RegistroCartas.this, ListaCartasActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}