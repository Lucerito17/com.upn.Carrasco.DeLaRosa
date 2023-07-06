package com.example.comupncarrascodelarosa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetallesDuelistasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_duelistas);
        TextView tvNombreDuelista = findViewById(R.id.tvNombreDuelista);
        Button BtnVerCartas = findViewById(R.id.BtnVerCartas);
        Button BtnRegistrarCartas = findViewById(R.id.BtnRegistrarCartas);

        //tvNombreDuelista.setText(etNombreDuelista);

        BtnVerCartas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListaCartasActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        BtnRegistrarCartas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegistroCartas.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}