package com.example.comupncarrascodelarosa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.comupncarrascodelarosa.BD.AppDatabase;
import com.example.comupncarrascodelarosa.Clases.Cartas;
import com.example.comupncarrascodelarosa.Repositories.CartaRepository;

public class DetallesCartasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_cartas);
        TextView tvNombreCarta = findViewById(R.id.tvNombreCartas);
        TextView tvPuntosA = findViewById(R.id.tvPuntosA);
        TextView tvPuntosD = findViewById(R.id.tvPuntosD);
        Button btnSincronizar = findViewById(R.id.btnSincronizar);

        int position = getIntent().getIntExtra("position", 0);

        AppDatabase db = AppDatabase.getInstance(this);
        CartaRepository repository = db.cartaRepository();
        Cartas carta = repository.findCartaById(position);
        tvNombreCarta.setText(carta.getNombre());
        tvPuntosA.setText(carta.getPuntosdeataque());
        tvPuntosD.setText(carta.getPuntosdefensa());

    }
}