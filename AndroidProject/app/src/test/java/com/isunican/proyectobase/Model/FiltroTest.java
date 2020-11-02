package com.isunican.proyectobase.Model;

import com.isunican.proyectobase.Presenter.PresenterGasolineras;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FiltroTest {
    private Filtro sut;

    @Before
    public void setUp(){
        sut = new Filtro();
    }

    /*
    Comprueba que al modificar el filtro de busqueda por tipo de combustible cambia
    correctamente los parametros de las gasolineras mostradas.
     */

    /* UT.1a */
    @Test
    public void combustibleMostradoPorDefectoTest(){

        assertEquals(sut.isGasoil(), true);
        assertEquals(sut.isGasolina(), false);
    }

    /* UT.1b */
    @Test
    public void modificaCombustibleTest(){
        sut.setGasoil(false);
        sut.setGasolina(true);
        assertEquals(sut.isGasoil(), false);
        assertEquals(sut.isGasolina(), true);
    }

    /* UT.1c */
    @Test
    public void quitarFiltrosTest(){
        sut.setGasoil(false);
        sut.setGasolina(false);
        assertEquals(sut.isGasoil(), false);
        assertEquals(sut.isGasolina(), false);
    }
}
