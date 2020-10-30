package com.isunican.proyectobase.Model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Filtro implements Parcelable {

    private boolean gasoil;
    private boolean gasolina;

    public Filtro(){
        gasoil=true;
        gasolina=false;
    }

    public Filtro(boolean gasoil, boolean gasolina) {
        this.gasoil = gasoil;
        this.gasolina = gasolina;
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

    protected Filtro(Parcel in) {
        gasoil = in.readByte() != 0;
        gasolina = in.readByte() != 0;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (gasoil ? 1 : 0));
        dest.writeByte((byte) (gasolina ? 1 : 0));
    }
}