package com.isunican.proyectobase.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * Esta clase representa la baase de datos.
 */
@Database(entities = {Filtro.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class FiltroDatabase extends RoomDatabase {
    public abstract IFiltroDao getFiltroDao();
}
