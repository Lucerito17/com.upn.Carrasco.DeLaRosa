package com.example.comupncarrascodelarosa.BD;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.comupncarrascodelarosa.Clases.Cartas;
import com.example.comupncarrascodelarosa.Clases.Duelistas;
import com.example.comupncarrascodelarosa.Repositories.CartaRepository;
import com.example.comupncarrascodelarosa.Repositories.DuelistaRepository;

@Database(entities = {Duelistas.class, Cartas.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DuelistaRepository duelistaRepository();
    public abstract CartaRepository cartaRepository();

    public static AppDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "ExamenDB")
                .allowMainThreadQueries()
                .build();
    }
}
