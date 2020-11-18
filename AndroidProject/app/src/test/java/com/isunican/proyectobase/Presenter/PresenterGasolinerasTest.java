package com.isunican.proyectobase.Presenter;

import com.isunican.proyectobase.DataBase.Filtro;
import com.isunican.proyectobase.Model.Gasolinera;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class PresenterGasolinerasTest {

    private PresenterGasolineras sut;
    private ArrayList<Gasolinera> gasolineras;
    private ArrayList<String> combustibles;

    @Before
    public void setUp() {
        sut = new PresenterGasolineras();
        gasolineras = new ArrayList<Gasolinera>();
        Gasolinera g1 = new Gasolinera(1,"SANTANDER","CANTABRIA","AA",
                1, 2, 3, 4, 5,
                6, 7,
                8, 9, 10, 11, 12,
                13, 14, "CEPSA");
        Gasolinera g2 = new Gasolinera(2, "MALIAÑO", "CANTABRIA",
                "AA2", 14,13, 12, 11,
                10, 9, 8, 7, 6, 5,
                4, 3, 2, 1, "CEPSA");

        Gasolinera g3 = new Gasolinera(3,"ASTILLERO","CANTABRIA","AA3",
                24,23,22,21,20,19,
                18,17,16,15,14,13,
                12,11,"CAMPSA");
        gasolineras.add(g1);
        gasolineras.add(g2);
        gasolineras.add(g3);
        sut.setGasolineras(gasolineras);

        combustibles = new ArrayList<String>();
        combustibles.add("BIODIESEL");
        combustibles.add("BIOETANOL");
        combustibles.add("GASOLEO A");
    }

    @Test
    public void cargaDatosRemotosTest(){
        assertTrue(true);
    }

    @Test
    public void ordenarPorPrecioMenorAMayorTest(){
        Filtro f = new Filtro("filtro",combustibles,"MenorAMayor");

        //CASO 1 - Filtro nulo
        try {
            sut.ordenarPorPrecioMenorAMayor(null);
            fail("No se ha lanzado la excepción NullPointerException");
        } catch (NullPointerException e){}

        //CASO 2 - Caso correcto
        try {
            sut.ordenarPorPrecioMenorAMayor(f);
        } catch (PresenterGasolineras.OrdenacionNoValida e){
            fail("Ha saltado la excepcion OrdenacionNoValida");
        } catch (PresenterGasolineras.CombustiblesInvalidos e){
            fail("Ha saltado la excepcion CombustiblesInvalidos");
        }

        //CASO 3 - Combustibles no válidos
        ArrayList<String> combustiblesNoValidos = new ArrayList<String>();
        combustiblesNoValidos.add("PATATA");
        combustiblesNoValidos.add("TOMATE");
        combustiblesNoValidos.add("ATUN");
        Filtro f2 = new Filtro("filtro2",combustiblesNoValidos,"MenorAMayor");

        try {
            sut.ordenarPorPrecioMenorAMayor(f2);
            fail("No ha saltado la excepcion CombustiblesInvalidos");
        } catch (PresenterGasolineras.CombustiblesInvalidos e){}

        //CASO 4 - Ordenacion no valida
        f = new Filtro("filtro",combustibles,"PATATA");
        try {
            sut.ordenarPorPrecioMenorAMayor(f);
            fail("No ha saltado la excepcion OrdenacionNoValida");
        } catch (PresenterGasolineras.OrdenacionNoValida e){}
    }

    @Test
    public void ordenarPorPrecioMayorAMenor(){
        Filtro f = new Filtro("filtro",combustibles,"MayorAMenor");

        //CASO 1 - Filtro nulo
        try {
            sut.ordenarPorPrecioMayorAMenor(null);
            fail("No se ha lanzado la excepción NullPointerException");
        } catch (NullPointerException e){}

        //CASO 2 - Caso correcto
        try {
            sut.ordenarPorPrecioMayorAMenor(f);
        } catch (PresenterGasolineras.OrdenacionNoValida e){
            fail("Ha saltado la excepcion OrdenacionNoValida");
        } catch (PresenterGasolineras.CombustiblesInvalidos e){
            fail("Ha saltado la excepcion CombustiblesInvalidos");
        }

        //CASO 3 - Combustibles no válidos
        ArrayList<String> combustiblesNoValidos = new ArrayList<String>();
        combustiblesNoValidos.add("PATATA");
        combustiblesNoValidos.add("TOMATE");
        combustiblesNoValidos.add("ATUN");
        Filtro f2 = new Filtro("filtro2",combustiblesNoValidos,"MayorAMenor");

        try {
            sut.ordenarPorPrecioMayorAMenor(f2);
            fail("No ha saltado la excepcion CombustiblesInvalidos");
        } catch (PresenterGasolineras.CombustiblesInvalidos e){}

        //CASO 4 - Ordenacion no valida
        f = new Filtro("filtro",combustibles,"PATATA");
        try {
            sut.ordenarPorPrecioMayorAMenor(f);
            fail("No ha saltado la excepcion OrdenacionNoValida");
        } catch (PresenterGasolineras.OrdenacionNoValida e){}
    }

    @Test
    public void ordenarPorPrecioTest(){
        assertTrue(true);
    }


}
