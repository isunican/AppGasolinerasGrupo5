package com.isunican.proyectobase.DataBase;

import org.junit.Before;
import org.junit.Test;



import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FiltroTest {
    private Filtro filtro;
    //Lista que usaremos para la selección de múltiples combustibles
    private ArrayList<String> combustibles= new ArrayList<String>();

    @Before
    public void setUp() {
        filtro =new Filtro();
        //Creación de una lista de combustibles para probar con varios combustibles seleccionados
        //a la vez, probando con cinco de las posibles opciones de combustibles.
        combustibles.add("GASOLEO A");
        combustibles.add("GASOLEO B");
        combustibles.add("GASOLEO PREMIUM");
        combustibles.add("GASOLINA 95 E10");
        combustibles.add("GASOLINA 95 E5");
    }

    /**
     * Test de seleccion de varios combustibles
     */
    @Test
    public void seleccionVariosCombustiblesTest()
    {
        filtro.setCombustibles(combustibles);
        assertEquals(filtro.getCombustibles().equals(combustibles), true);
        assertEquals(filtro.getCombustibles().get(0).equals("GASOLEO A"), true);
        assertEquals(filtro.getCombustibles().get(1).equals("GASOLEO B"), true);
        assertEquals(filtro.getCombustibles().get(2).equals("GASOLEO PREMIUM"), true);
        assertEquals(filtro.getCombustibles().get(3).equals("GASOLINA 95 E10"), true);
        assertEquals(filtro.getCombustibles().get(4).equals("GASOLINA 95 E5"), true);
    }

    /**
     * Test de deseleccion de varios combustibles
     */
    @Test
    public void quitaVariosCombustiblesTest()
    {
        combustibles.remove(4);
        combustibles.remove(3);
        combustibles.remove(2);
        filtro.setCombustibles(combustibles);
        assertEquals(filtro.getCombustibles().equals(combustibles), true);
        assertEquals(filtro.getCombustibles().get(0).equals("GASOLEO A"), true);
        assertEquals(filtro.getCombustibles().get(1).equals("GASOLEO B"), true);
    }

    /**
     * Test de comprobacion de manejo de NullPointerException si se le pasa null como
     * parametro
     */

    @Test
    public void compruebaNullTest()
    {
        try {
            filtro.setCombustibles(null);
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }

}
