package com.example.comupncarrascodelarosa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import com.example.comupncarrascodelarosa.Adapters.DuelistaAdapter;
import com.example.comupncarrascodelarosa.Clases.Duelistas;
import com.example.comupncarrascodelarosa.Utilities.RetrofitU;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class ListaDuelistaActivity extends AppCompatActivity {
    Retrofit dRetrofit;
    RecyclerView mRvLista;
    boolean mIsLoading = false;
    int mPage = 1;
    List<Duelistas> ddata = new ArrayList<>();
    DuelistaAdapter dAdapter = new DuelistaAdapter(ddata, this);
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_duelista);

        dRetrofit = RetrofitU.build();

        Button btnRegistro = findViewById(R.id.btnRegistrarNuevoD);
        Button btnActualizar = findViewById(R.id.btnActualizarD);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRvLista =  findViewById(R.id.rvListaDuelista);
        mRvLista.setLayoutManager(layoutManager);
        mRvLista.setAdapter(dAdapter);

    }
}