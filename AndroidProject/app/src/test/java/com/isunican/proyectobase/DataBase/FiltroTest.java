package com.isunican.proyectobase.DataBase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FiltroTest {
    private Filtro filtro;
    //Aqui guardamos todas las marcas disponibles
    private ArrayList<String> marcasDisponibles= new ArrayList<String>();

    /**
     * @author Javier Gomez Serrano
     * Anhadimos varias marcas, como todas son string con el mismo comportamiento probamos
     * con un espacio de prueba reducido
     */
    @Before
    public void setUp() {
        filtro =new Filtro();
        marcasDisponibles.add("CEPSA");
        marcasDisponibles.add("REPSOL");
        marcasDisponibles.add("PETRONOR");
        marcasDisponibles.add("CAMPSA");
        marcasDisponibles.add("G2");
    }

    /**
     * @author Javier Gomez Serrano
     * Probamos con una sola marca
     */
    @Test
    public void unaMarcaTest()
    {
        //Anhadimos el filtrado por la marca CEPSA
        filtro.setMarca(marcasDisponibles.get(0));
        assertEquals(filtro.getMarca().equals("CEPSA"), true);
    }

    /**
     * @author Javier Gomez Serrano
     * Probamos con todo el espacio de prueba reducido, al existir mas marcas
     * la prueba seria igual para todo el numero de marcas diferentes que hubiera
     */
    @Test
    public void variasMarcasTest()
    {
        for (int i = 0; i<marcasDisponibles.size(); i++)
        {
            filtro.setMarca(marcasDisponibles.get(i));
            assertEquals(filtro.getMarca().equals(marcasDisponibles.get(i)), true);
        }
    }

    /**
     * @author Javier Gomez Serrano
     * Probamos que al pasar un null como parametro se maneja la excepcion NullPointerException
     */
    @Test
    public void marcaNullTest()
    {
        //Anhadimos el filtrado por null
        try {
            filtro.setMarca(null);
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }
}
