package com.isunican.proyectobase.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FiltroDao {

    @Query("SELECT * FROM filtro")
    List<Filtro> getFiltros();

    @Query("SELECT * FROM filtro WHERE id IN (:filtroIds)")
    Filtro getFiltro(int id);

    @Insert
    void addFiltro(Filtro filtro);

    @Update
    void deleteFiltro(Filtro filtro);

    @Delete
    void updateFiltro(Filtro filtro);

}
