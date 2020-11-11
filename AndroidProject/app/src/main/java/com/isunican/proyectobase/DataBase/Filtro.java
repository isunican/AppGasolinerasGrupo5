package com.isunican.proyectobase.DataBase;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Clase que representa la tabla filtro.
 */
@Entity(tableName = "filtro")
public class Filtro implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nombre")
    public String nombre;
    @ColumnInfo(name = "gasoil")
    public boolean gasoil;
    @ColumnInfo(name = "gasolina")
    public boolean gasolina;
    @ColumnInfo(name = "ordenarPorPrecio")
    public String ordenarPorPrecio;

    public Filtro(){
        nombre = "DEFECTO";
        gasoil = true;
        gasolina = false;
        ordenarPorPrecio = "";
    }

    public Filtro(String nombre, boolean gasoil, boolean gasolina, String ordenarPorPrecio) {
        this.nombre = nombre;
        this.gasoil = gasoil;
        this.gasolina = gasolina;
        this.ordenarPorPrecio = ordenarPorPrecio;
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

    public boolean isGasoil() {
        return gasoil;
    }

    public void setGasoil(boolean gasoil) {
        this.gasoil = gasoil;
    }

    public boolean isGasolina() {
        return gasolina;
    }

    public void setGasolina(boolean gasolina) {
        this.gasolina = gasolina;
    }

    public String getOrdenarPorPrecio() { return ordenarPorPrecio;}

    public void setOrdenarPorPrecio(String ordenarPorPrecio) {this.ordenarPorPrecio = ordenarPorPrecio; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeByte((byte) (gasoil ? 1 : 0));
        dest.writeByte((byte) (gasolina ? 1 : 0));
        dest.writeString(ordenarPorPrecio);
    }

    protected Filtro(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        gasoil = in.readByte() != 0;
        gasolina = in.readByte() != 0;
        ordenarPorPrecio = in.readString();
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
