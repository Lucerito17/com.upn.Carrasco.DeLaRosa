package com.example.comupncarrascodelarosa.Utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitU {
    public static Retrofit build() {
        return new Retrofit.Builder()
                .baseUrl("https://64a6b568096b3f0fcc8064bf.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
