package com.example.comupncarrascodelarosa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BtnRegistrarDuelista = findViewById(R.id.btnRegistrarDuelista);
        Button BtnListaDuelista = findViewById(R.id.btnListaDuelista);

        BtnRegistrarDuelista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegistroDuelista.class);
                view.getContext().startActivity(intent);
            }
        });

        BtnListaDuelista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListaDuelistaActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}