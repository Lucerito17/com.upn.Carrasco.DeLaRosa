package com.example.comupncarrascodelarosa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistroDuelista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_duelista);

        EditText etNombreDuelista = findViewById(R.id.etNombreDuelista);
        Button btnRegistrado = findViewById(R.id.btnRegistrado);

        btnRegistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context;
                Intent intent =  new Intent(context, ListaDuelistaActivity.class);
                context.startActivity(intent);
            }
        });
    }
}