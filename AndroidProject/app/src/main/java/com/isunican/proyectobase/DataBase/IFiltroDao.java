package com.isunican.proyectobase.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Esta clase es la interfaz dao de filtro.
 */
@Dao
public interface IFiltroDao {

    @Query("SELECT * FROM filtro")
    List<Filtro> getFiltros();

    @Query("SELECT * FROM filtro WHERE id IN (:filtroIds)")
    Filtro getFiltro(int filtroIds);

    @Query("SELECT * FROM filtro WHERE nombre LIKE :nombre")
    Filtro getFiltro(String nombre);

    @Insert
    void addFiltro(Filtro filtro);

    @Update
    void updateFiltro(Filtro filtro);

    @Delete
    void deleteFiltro(Filtro filtro);

}

