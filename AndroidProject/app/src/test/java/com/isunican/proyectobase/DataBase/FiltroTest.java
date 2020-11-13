package com.isunican.proyectobase.DataBase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FiltroTest {
    private Filtro filtroConNombre;
    private Filtro filtroDefecto;

    /*
    @Before
    public void setUp() {
        filtroConNombre=new Filtro("Gasolina solo", false, true);
        filtroDefecto=new Filtro();
    }
    */
     /*
    Comprueba que al modificar el filtro de busqueda por tipo de combustible cambia
    correctamente los parametros de las gasolineras mostradas.
     */

    /* UT.1a */
    /*
    @Test
    public void combustibleMostradoPorDefectoTest(){

        assertEquals(filtroDefecto.isGasoil(), true);
        assertEquals(filtroDefecto.isGasolina(), false);
    }

    /* UT.1b */
    /*
    @Test
    public void modificaCombustibleTest(){
        filtroDefecto.setGasoil(false);
        filtroDefecto.setGasolina(true);
        assertEquals(filtroDefecto.isGasoil(), false);
        assertEquals(filtroDefecto.isGasolina(), true);
    }

    /* UT.1c */
    /*
    @Test
    public void quitarFiltrosTest(){
        filtroDefecto.setGasoil(false);
        filtroDefecto.setGasolina(false);
        assertEquals(filtroDefecto.isGasoil(), false);
        assertEquals(filtroDefecto.isGasolina(), false);
    }

    /**
     * Comprobamos si el nombre del filtro a anhadir escrito es correcto
     */
    /*
    @Test
    public void filtroCorrectoTest() {
        //Caso en el que el nombre elegido es correcto
        assertEquals(filtroConNombre.getNombre(),"Gasolina solo");

        //Caso en el que el nombre por defecto es correcto
        assertEquals(filtroDefecto.getNombre(),"DEFECTO");
    }

     */
}
