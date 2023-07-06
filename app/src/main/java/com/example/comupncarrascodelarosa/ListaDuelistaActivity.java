package com.example.comupncarrascodelarosa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.comupncarrascodelarosa.Adapters.DuelistaAdapter;
import com.example.comupncarrascodelarosa.BD.AppDatabase;
import com.example.comupncarrascodelarosa.Clases.Duelistas;
import com.example.comupncarrascodelarosa.Repositories.DuelistaRepository;
import com.example.comupncarrascodelarosa.Service.DuelistaService;
import com.example.comupncarrascodelarosa.Utilities.RetrofitU;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

        mRvLista.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!mIsLoading) {

                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == ddata.size() - 1) {
                        mPage++;
                        loadMore(mPage);
                    }
                }

            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(ListaDuelistaActivity.this, RegistroDuelista.class);
                startActivity(intent);
                finish();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadToWebService(1);
            }
        });
        AppDatabase db = AppDatabase.getInstance(context);
        DuelistaRepository repository = db.duelistaRepository();
        List<Duelistas> users = repository.getAll();
        Log.i("MAIN_APP: DB", new Gson().toJson(users));
        dAdapter.setDuelistas(users);
        dAdapter.notifyDataSetChanged();
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void uploadToWebService(int nextPage) {

        AppDatabase db = AppDatabase.getInstance(context);
        db.clearAllTables();

        DuelistaService service = dRetrofit.create(DuelistaService.class);
        service.getAllDuelistas(100, nextPage).enqueue(new Callback<List<Duelistas>>() {
            @Override
            public void onResponse(Call<List<Duelistas>> call, Response<List<Duelistas>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Inserta los datos en la base de datos
                    AppDatabase db = AppDatabase.getInstance(ListaDuelistaActivity.this);
                    DuelistaRepository repository = db.duelistaRepository();
                    repository.insertAll(response.body());

                    // Actualiza los datos en el adaptador y notifica los cambios
                    List<Duelistas> newData = repository.getAll();
                    dAdapter.setDuelistas(newData);
                    dAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Duelistas>> call, Throwable t) {
                // Maneja el error de la llamada al servicio MockAPI
            }
        });
    }
    private void loadMore(int nextPage) {
        mIsLoading = true;

        ddata.add(null);
        dAdapter.notifyItemInserted(ddata.size() - 1);

        DuelistaService service =dRetrofit.create(DuelistaService.class);
        Log.i("MAIN_APP  Page:", String.valueOf(nextPage));
        service.getAllDuelistas(100, nextPage).enqueue(new Callback<List<Duelistas>>() { // Cambia el número de registros por página según tus necesidades
            @Override
            public void onResponse(Call<List<Duelistas>> call, Response<List<Duelistas>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ddata.remove(ddata  .size() - 1);
                    dAdapter.notifyItemRemoved(ddata.size() - 1);

                    ddata.addAll(response.body());
                    dAdapter.notifyDataSetChanged();
                    mIsLoading = false;

                    // Si hay más registros disponibles, cargar la siguiente página
                    if (response.body().size() >= 100) { // Cambia el número de registros por página según tus necesidades
                        loadMore(nextPage + 1);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Duelistas>> call, Throwable t) {
                // Manejar error de la llamada al servicio MockAPI
            }
        });
    }
}