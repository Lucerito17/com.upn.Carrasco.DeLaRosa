package com.example.comupncarrascodelarosa.Service;

import com.example.comupncarrascodelarosa.Clases.Duelistas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DuelistaService {
    @GET("Duelistas")
    Call<List<Duelistas>> getAllDuelistas(@Query("limit") int limit, @Query("page") int page);

    @GET("Duelistas/{id}")
    Call<Duelistas> findDuelistas(@Path("id") int id);

    @POST("Duelistas")
    Call<Duelistas> create(@Body Duelistas duelistas);

    @PUT("Duelistas/{id}")
    Call<Duelistas> update(@Path("id") int id, @Body Duelistas duelistas);

    @DELETE("Duelistas/{id}")
    Call<Void> delete(@Path("id") int id);

    @POST("Duelistas/upload")
    Call<Void> uploadDuelistas(@Body List<Duelistas> duelistas);
}
