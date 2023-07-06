package com.example.comupncarrascodelarosa.Clases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cartas")
public class Cartas {

    @PrimaryKey()
    public int id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "puntosdeataque")
    private String puntosdeataque;
    @ColumnInfo(name = "puntosdefensa")
    private String puntosdefensa;
    @ColumnInfo(name = "imagen")
    private String imagen;
    @ColumnInfo(name = "longitud")
    private String longitud;
    @ColumnInfo(name = "latitud")
    private String latitud;
    @ColumnInfo(name = "bool")
    private Boolean bool;
    @ColumnInfo(name = "idDuelista")
    private String idDuelista;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() { return nombre;}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuntosdeataque() {
        return puntosdeataque;
    }

    public void setPuntosdeataque(String puntosdeataque) {
        this.puntosdeataque = puntosdeataque;
    }

    public String getPuntosdefensa() {
        return puntosdefensa;
    }

    public void setPuntosdefensa(String puntosdefensa) {
        this.puntosdefensa = puntosdefensa;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public String getIdDuelista() {
        return idDuelista;
    }

    public void setIdDuelista(String idDuelista) {
        this.idDuelista = idDuelista;
    }
}
