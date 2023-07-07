package com.example.comupncarrascodelarosa.Repositories;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.comupncarrascodelarosa.Clases.Cartas;
import com.example.comupncarrascodelarosa.Clases.Duelistas;

import java.util.List;

@Dao
public interface CartaRepository {
    @Query("SELECT * FROM cartas")
    List<Cartas> getAll();
    @Insert
    void create(Cartas cartas);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Cartas> movimientos);

    @Query("SELECT * FROM Cartas WHERE bool = 0")
    List<Cartas> getUnsyncedCarta();

    @Update
    void updateCarta(Cartas cartas);

    @Query("SELECT * FROM Cartas WHERE idDuelista = :idDuelista")
    List<Cartas> getCartaDuelista(int idDuelista);

    @Query("SELECT MAX(id) FROM Cartas")
    int getLastId();
    @Query("SELECT * FROM Cartas WHERE id = :cartasId")
    Cartas findCartaById(int cartasId);
}
