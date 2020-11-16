package com.isunican.proyectobase.DataBase;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la tabla filtro.
 */
@Entity(tableName = "filtro")
public class Filtro implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nombre")
    public String nombre;
    @TypeConverters(Converters.class)
    @ColumnInfo(name = "combustibles")
    public List<String> combustibles;
    @ColumnInfo(name = "marca")
    public String marca;
    @ColumnInfo(name = "ordenarPorPrecio")
    public String ordenarPorPrecio;

    @Ignore
    public Filtro() {
        nombre = "DEFECTO";
        combustibles = new ArrayList<>();
        combustibles.add("GASOLEO A");
        marca = "";
        ordenarPorPrecio = "";
    }

    public Filtro(String nombre, List<String> combustibles, String marca, String ordenarPorPrecio) {
        this.nombre = nombre;
        this.combustibles = combustibles;
        this.ordenarPorPrecio = ordenarPorPrecio;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getCombustibles() {
        return combustibles;
    }

    public void setCombustibles(List<String> combustibles) {
        this.combustibles = combustibles;
    }

    public String getOrdenarPorPrecio() {
        return ordenarPorPrecio;
    }

    public void setOrdenarPorPrecio(String ordenarPorPrecio) {
        this.ordenarPorPrecio = ordenarPorPrecio;
    }


    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeList(combustibles);
        dest.writeString(ordenarPorPrecio);
        dest.writeString(marca);
    }

    protected Filtro(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        combustibles = in.readArrayList(null);
        ordenarPorPrecio = in.readString();
        marca = in.readString();
    }

    public static final Creator<Filtro> CREATOR = new Creator<Filtro>() {
        @Override
        public Filtro createFromParcel(Parcel in) {
            return new Filtro(in);
        }

        @Override
        public Filtro[] newArray(int size) {
            return new Filtro[size];
        }
    };
}
