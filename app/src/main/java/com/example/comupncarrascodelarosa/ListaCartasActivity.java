package com.example.comupncarrascodelarosa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.comupncarrascodelarosa.Adapters.DuelistaAdapter;
import com.example.comupncarrascodelarosa.BD.AppDatabase;
import com.example.comupncarrascodelarosa.Clases.Duelistas;
import com.example.comupncarrascodelarosa.Repositories.DuelistaRepository;
import com.example.comupncarrascodelarosa.Service.DuelistaService;
import com.example.comupncarrascodelarosa.Utilities.RetrofitU;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListaCartasActivity extends AppCompatActivity {

    Retrofit cRetrofit;
    RecyclerView mRcLista;
    boolean mIsLoading = false;
    int cPage = 1;
    List<Duelistas> cdata = new ArrayList<>();
    DuelistaAdapter cAdapter = new DuelistaAdapter(cdata, this);
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cartas);

        cRetrofit = RetrofitU.build();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRcLista =  findViewById(R.id.rvListaCartas);
        mRcLista.setLayoutManager(layoutManager);
        mRcLista.setAdapter(cAdapter);

        mRcLista.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!mIsLoading) {

                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == cdata.size() - 1) {
                        cPage++;
                        loadMore(cPage);
                    }
                }

            }
        });
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void uploadToWebService(int nextPage) {

        AppDatabase db = AppDatabase.getInstance(context);
        db.clearAllTables();

        DuelistaService service = cRetrofit.create(DuelistaService.class);
        service.getAllDuelistas(100, nextPage).enqueue(new Callback<List<Duelistas>>() {
            @Override
            public void onResponse(Call<List<Duelistas>> call, Response<List<Duelistas>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Inserta los datos en la base de datos
                    AppDatabase db = AppDatabase.getInstance(ListaCartasActivity.this);
                    DuelistaRepository repository = db.duelistaRepository();
                    repository.insertAll(response.body());

                    // Actualiza los datos en el adaptador y notifica los cambios
                    List<Duelistas> newData = repository.getAll();
                    cAdapter.setDuelistas(newData);
                    cAdapter.notifyDataSetChanged();
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

        cdata.add(null);
        cAdapter.notifyItemInserted(cdata.size() - 1);

        DuelistaService service =cRetrofit.create(DuelistaService.class);
        Log.i("MAIN_APP  Page:", String.valueOf(nextPage));
        service.getAllDuelistas(100, nextPage).enqueue(new Callback<List<Duelistas>>() { // Cambia el número de registros por página según tus necesidades
            @Override
            public void onResponse(Call<List<Duelistas>> call, Response<List<Duelistas>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cdata.remove(cdata  .size() - 1);
                    cAdapter.notifyItemRemoved(cdata.size() - 1);

                    cdata.addAll(response.body());
                    cAdapter.notifyDataSetChanged();
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