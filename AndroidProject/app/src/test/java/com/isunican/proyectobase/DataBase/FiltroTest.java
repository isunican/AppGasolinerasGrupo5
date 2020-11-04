package com.isunican.proyectobase.DataBase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FiltroTest {
    private Filtro filtroConNombre;
    private Filtro filtroDefecto;

    @Before
    public void setUp() {
        filtroConNombre=new Filtro("Gasolina solo", false, true);
        filtroDefecto=new Filtro();
    }

    /**
     * Comprobamos si el nombre del filtro a anhadir escrito es correcto
     */
    @Test
    public void filtroCorrectoTest() {
        //Caso en el que el nombre elegido es correcto
        assertEquals(filtroConNombre.getNombre(),"Gasolina solo");

        //Caso en el que el nombre por defecto es correcto
        assertEquals(filtroDefecto.getNombre(),"DEFECTO");
    }
}
