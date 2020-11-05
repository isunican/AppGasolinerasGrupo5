package com.isunican.proyectobase.Presenter;

import com.isunican.proyectobase.Model.Gasolinera;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class PresenterGasolinerasTest {

    private PresenterGasolineras sut;

    @Before
    public void setUp(){
        sut = new PresenterGasolineras();
    }

    /*
    Comprueba que se cargan los datos de las 5 gasolineras correctamente
     */

    /* UT.2a */
    @Test
    public void cargaDatosDummyTest(){
        sut.cargaDatosDummy();
        assertEquals(sut.getGasolineras().size(), 5);
    }

    /* UT.2b */
    @Test
    public void PresenterSinGasolinerasTest(){
        assertEquals(sut.getGasolineras().size(), 0);
    }
}
