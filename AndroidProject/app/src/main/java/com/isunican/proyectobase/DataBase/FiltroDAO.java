package com.isunican.proyectobase.DataBase;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import java.util.List;

/**
 * Esta clase hace uso de la interfaz IFiltroDAO e interactúa con la base de datos.
 */
public class FiltroDAO {

    @SuppressLint("StaticFieldLeak")
    private static FiltroDAO filtroDAO;

    private IFiltroDao dao;

    private FiltroDAO(Context context){
        Context appContext = context.getApplicationContext();
        FiltroDatabase database = Room.databaseBuilder(appContext, FiltroDatabase.class, "filtro")
                .allowMainThreadQueries().build();
        dao = database.getFiltroDao();
    }

    public static FiltroDAO get(Context context) {
        if (filtroDAO == null) {
            filtroDAO = new FiltroDAO(context);
        }
        return filtroDAO;
    }

    public List<Filtro> getFiltros() {
        return dao.getFiltros();
    }

    public Filtro getFiltro(int id) {
        return dao.getFiltro(id);
    }

    public Filtro getFiltro(String nombre) {
        return dao.getFiltro(nombre);
    }

    public void addFiltro(Filtro filtro) {
        dao.addFiltro(filtro);
    }

    public void updateFiltro(Filtro filtro) {
        dao.updateFiltro(filtro);
    }

    public void deleteFiltro(Filtro filtro) {
        dao.deleteFiltro(filtro);
    }
}
