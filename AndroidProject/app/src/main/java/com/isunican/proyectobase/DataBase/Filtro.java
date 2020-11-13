package com.isunican.proyectobase.DataBase;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;

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
    public ArrayList<String> combustibles;


    public Filtro(){
        nombre = "DEFECTO";
        combustibles = new ArrayList<>();
        combustibles.add("GASOLEO A");
    }

    public Filtro(String nombre, ArrayList<String> combustibles) {
        this.nombre = nombre;
        this.combustibles = combustibles;
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

    public ArrayList<String> getCombustibles() {
        return combustibles;
    }

    public void setCombustibles(ArrayList<String> combustibles) {
        this.combustibles = combustibles;
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
    }

    protected Filtro(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        combustibles = in.readArrayList(null);
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
