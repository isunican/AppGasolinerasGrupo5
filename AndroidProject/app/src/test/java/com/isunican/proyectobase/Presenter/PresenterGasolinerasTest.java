package com.isunican.proyectobase.Presenter;

import com.isunican.proyectobase.DataBase.Filtro;
import com.isunican.proyectobase.Model.Gasolinera;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PresenterGasolinerasTest {

    private PresenterGasolineras sut;
    private ArrayList<Gasolinera> gasolineras;
    private ArrayList<String> combustibles;

    /**
     * @author Víctor Martínez Vila
     */
    @Before
    public void setUp() {
        sut = new PresenterGasolineras();
        gasolineras = new ArrayList<Gasolinera>();
        Gasolinera g1 = new Gasolinera(1);
        g1.setGasoleoA(1);
        Gasolinera g2 = new Gasolinera(2);
        g2.setGasoleoA(2);
        Gasolinera g3 = new Gasolinera(3);
        g3.setGasoleoA(3);

        gasolineras.add(g1);
        gasolineras.add(g2);
        gasolineras.add(g3);
        sut.setGasolineras(gasolineras);

        combustibles = new ArrayList<String>();
        combustibles.add("BIODIESEL");
        combustibles.add("BIOETANOL");
        combustibles.add("GASOLEO A");
    }

    /**
     * @author Víctor Argueso Cano
     */
    @Test
    public void cargaDatosRemotosTest() {
        try {
            sut.cargaDatosRemotos(null);
            fail("No se ha lanzado la excepción RuntimeException");
        } catch (Exception e) {}

        try {
            sut.cargaDatosRemotos(PresenterGasolineras.URL_GASOLINERAS_SPAIN);

        } catch (Exception e) {
            assertTrue(true);

        }
    }

    /**
     * @author Víctor Martínez Vila
     */
    @Test
    public void ordenarPorPrecioMenorAMayorTest(){
        Filtro f = new Filtro("filtro",combustibles,"MARCA","MenorAMayor");

        //CASO 1 - Filtro nulo
        try {
            sut.ordenarPorPrecioMenorAMayor(null);
            fail("No se ha lanzado la excepción NullPointerException");
        } catch (NullPointerException e){}

        //CASO 2 - Caso correcto
        try {
            sut.ordenarPorPrecioMenorAMayor(f);
            //Comprobamos que están en orden
            assertTrue(sut.getGasolineras().get(0).getGasoleoA() <
                    sut.getGasolineras().get(1).getGasoleoA());
            assertTrue(sut.getGasolineras().get(1).getGasoleoA() <
                    sut.getGasolineras().get(2).getGasoleoA());

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
        Filtro f2 = new Filtro("filtro2",combustiblesNoValidos,"MARCA","MenorAMayor");

        try {
            sut.ordenarPorPrecioMenorAMayor(f2);
            fail("No ha saltado la excepcion CombustiblesInvalidos");
        } catch (PresenterGasolineras.CombustiblesInvalidos e){}

        //CASO 4 - Ordenacion no valida
        f = new Filtro("filtro",combustibles,"MARCA","PATATA");
        try {
            sut.ordenarPorPrecioMenorAMayor(f);
            fail("No ha saltado la excepcion OrdenacionNoValida");
        } catch (PresenterGasolineras.OrdenacionNoValida e){}
    }

    /**
     * @author Víctor Martínez Vila
     */
    @Test
    public void ordenarPorPrecioMayorAMenor(){
        Filtro f = new Filtro("filtro",combustibles,"MARCA","MayorAMenor");

        //CASO 1 - Filtro nulo
        try {
            sut.ordenarPorPrecioMayorAMenor(null);
            fail("No se ha lanzado la excepción NullPointerException");
        } catch (NullPointerException e){}

        //CASO 2 - Caso correcto
        try {
            sut.ordenarPorPrecioMayorAMenor(f);
            assertTrue(sut.getGasolineras().get(0).getGasoleoA() >
                    sut.getGasolineras().get(1).getGasoleoA());
            assertTrue(sut.getGasolineras().get(1).getGasoleoA() >
                    sut.getGasolineras().get(2).getGasoleoA());
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
        Filtro f2 = new Filtro("filtro2",combustiblesNoValidos,"MARCA","MayorAMenor");

        try {
            sut.ordenarPorPrecioMayorAMenor(f2);
            fail("No ha saltado la excepcion CombustiblesInvalidos");
        } catch (PresenterGasolineras.CombustiblesInvalidos e){}

        //CASO 4 - Ordenacion no valida
        f = new Filtro("filtro",combustibles,"MARCA","PATATA");
        try {
            sut.ordenarPorPrecioMayorAMenor(f);
            fail("No ha saltado la excepcion OrdenacionNoValida");
        } catch (PresenterGasolineras.OrdenacionNoValida e){}
    }

    /**
     * @author Víctor Argueso Cano
     */
    @Test
    public void ordenarPorPrecioTest(){
        Filtro f = new Filtro("filtro",combustibles, "MARCA","MayorAMenor");
        Filtro f1 = new Filtro("filtro",combustibles,"MARCA","MenorAMayor");
        //CASO 1 - Filtro nulo
        try {
            sut.ordenarPorPrecio(null);
            fail("No se ha lanzado la excepción NullPointerException");
        } catch (NullPointerException e){}

        //CASO 2 - Caso correcto (Mayor a menor)
        try {
            sut.ordenarPorPrecio(f);
            assertTrue(sut.getGasolineras().get(0).getGasoleoA() >
                    sut.getGasolineras().get(1).getGasoleoA());
            assertTrue(sut.getGasolineras().get(1).getGasoleoA() >
                    sut.getGasolineras().get(2).getGasoleoA());
        } catch (PresenterGasolineras.OrdenacionNoValida e){
            fail("Ha saltado la excepcion OrdenacionNoValida");
        } catch (PresenterGasolineras.CombustiblesInvalidos e){
            fail("Ha saltado la excepcion CombustiblesInvalidos");
        }

        //CASO 3 - Combustibles no válidos
        ArrayList<String> combustiblesIncorrectos = new ArrayList<String>();
        combustiblesIncorrectos.add("LUIS");
        combustiblesIncorrectos.add("GONZALO");
        combustiblesIncorrectos.add("ALBERTO");
        combustiblesIncorrectos.add("ROQUE");

        Filtro f2 = new Filtro("filtro2",combustiblesIncorrectos,"MARCA","MayorAMenor");

        try {
            sut.ordenarPorPrecio(f2);
            fail("No ha saltado la excepcion CombustiblesInvalidos");
        } catch (PresenterGasolineras.CombustiblesInvalidos e){}

        //CASO 4 - Ordenacion no valida
        f = new Filtro("filtro",combustibles,"MARCA","LUIS");
        try {
            sut.ordenarPorPrecio(f);
            fail("No ha saltado la excepcion OrdenacionNoValida");
        } catch (PresenterGasolineras.OrdenacionNoValida e){}


    //CASO 4 - Caso correcto (Menor a mayor)
        try {
        sut.ordenarPorPrecio(f1);
            assertTrue(sut.getGasolineras().get(0).getGasoleoA() <
                    sut.getGasolineras().get(1).getGasoleoA());
            assertTrue(sut.getGasolineras().get(1).getGasoleoA() <
                    sut.getGasolineras().get(2).getGasoleoA());
    } catch (PresenterGasolineras.OrdenacionNoValida e){
        fail("Ha saltado la excepcion OrdenacionNoValida");
    } catch (PresenterGasolineras.CombustiblesInvalidos e){
        fail("Ha saltado la excepcion CombustiblesInvalidos");
    }

    //CASO 3 - Combustibles no válidos

    Filtro f3 = new Filtro("filtro3",combustiblesIncorrectos,"MARCA","MenorAMayor");

        try {
        sut.ordenarPorPrecioMenorAMayor(f3);
        fail("No ha saltado la excepcion CombustiblesInvalidos");
    } catch (PresenterGasolineras.CombustiblesInvalidos e){}

    //CASO 4 - Ordenacion no valida
    f1 = new Filtro("filtro",combustibles,"MARCA","LUIS");
        try {
        sut.ordenarPorPrecioMenorAMayor(f);
        fail("No ha saltado la excepcion OrdenacionNoValida");
    } catch (PresenterGasolineras.OrdenacionNoValida e){}
}



}
