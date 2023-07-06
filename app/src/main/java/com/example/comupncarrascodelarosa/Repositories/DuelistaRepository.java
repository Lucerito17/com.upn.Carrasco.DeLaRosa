package com.example.comupncarrascodelarosa.Repositories;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.comupncarrascodelarosa.Clases.Duelistas;

import java.util.List;

@Dao
public interface DuelistaRepository {
    @Query("SELECT * FROM duelistas")
    List<Duelistas> getAll();
    @Insert
    void create(Duelistas duelista);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Duelistas> duelistas);

    @Query("SELECT * FROM Duelistas WHERE bool = 0")
    List<Duelistas> getUnSincro();

    @Update
    void updateDuelista(Duelistas duelista);

    @Query("SELECT MAX(id) FROM Duelistas")
    int getLastId();

    @Query("SELECT * FROM Duelistas WHERE id = :Id")
    Duelistas findDuelistaById(int Id);
}
