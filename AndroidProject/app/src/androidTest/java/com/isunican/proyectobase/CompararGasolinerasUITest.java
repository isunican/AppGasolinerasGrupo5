/**
 * Clase que implementa las pruebas de interfaz definidas en el plan de pruebas de la historia de usuario CompararGasolineras
 * @author Javier Gomez Serrano
 * @version Diciembre 2020
 */
package com.isunican.proyectobase;



import androidx.test.espresso.DataInteraction;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;



import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.assertion.ViewAssertions.matches;


import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class CompararGasolinerasUITest
{
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Metodo que verifica que dos gasolineras comparadas sin ningun filtro aplicado de combustible son las esperadas y por defecto tienen gasoleo A
     * @author Javier Gomez Serrano
     * @version Diciembre 2020
     */
    @Test
    public void CompararDosGasolinerasSinFiltros()
    {

        //Seleccionamos dos gasolineras a comparar(con gasoleo A por defecto)
        DataInteraction gasolinera1 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(0);
        DataInteraction chk1 = gasolinera1.onChildView(withId(R.id.checkGasolinera));
        chk1.perform(click());
        DataInteraction gasolinera2 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(1);
        DataInteraction chk2 = gasolinera2.onChildView(withId(R.id.checkGasolinera));
        chk2.perform(click());

        //Comparamos las dos
        onView(withId(R.id.btnComparar)).check(matches(isDisplayed())).perform(click());
        //Comprobamos que en la lista de comparadas el gasoleo es A y son las mismas que las otras
        onData(allOf(is(instanceOf(String.class)), is("GASOLEO A")));
        onData(anything()).inAdapterView(withId(R.id.listViewComparadas)).atPosition(0).equals(gasolinera1);
        onData(anything()).inAdapterView(withId(R.id.listViewComparadas)).atPosition(1).equals(gasolinera2);


    }//comparardosgasolineras

    /**
     * Metodo que verifica que cinco gasolineras comparadas con varios combustibles seleccionados son las esperadas y tienen esos combustibles
     * @author Javier Gomez Serrano
     * @version Diciembre 2020
     */
    @Test
    public void compararCincoGasolinerasConFiltros()
    {
        //Seleccionamos dos combustibles, sumados al predefinido GASOLEO A
        onView(withId(R.id.btnListaFiltros)).perform(click());
        onView(withId(R.id.btnCombustibles)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("GASOLEO B"))).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("GASOLEO PREMIUM"))).perform(click());
        onView(withText("ACEPTAR")).perform(click());
        onView(withId(R.id.btnAceptar)).perform(click());

        //Seleccionamos cinco gasolineras
        DataInteraction gasolinera1 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(0);
        DataInteraction chk1 = gasolinera1.onChildView(withId(R.id.checkGasolinera));
        chk1.perform(click());
        DataInteraction gasolinera2 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(1);
        DataInteraction chk2 = gasolinera2.onChildView(withId(R.id.checkGasolinera));
        chk2.perform(click());
        DataInteraction gasolinera3 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(2);
        DataInteraction chk3 = gasolinera3.onChildView(withId(R.id.checkGasolinera));
        chk3.perform(click());
        DataInteraction gasolinera4 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(3);
        DataInteraction chk4 = gasolinera4.onChildView(withId(R.id.checkGasolinera));
        chk4.perform(click());
        DataInteraction gasolinera5 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(4);
        DataInteraction chk5 = gasolinera5.onChildView(withId(R.id.checkGasolinera));
        chk5.perform(click());

        //Comparamos las cinco
        onView(withId(R.id.btnComparar)).check(matches(isDisplayed())).perform(click());

        //Comprobamos que en la lista de comparadas el gasoleo es A, B y PREMIUM y son las mismas que las otras
        onData(allOf(is(instanceOf(String.class)), is("GASOLEO A")));
        onData(allOf(is(instanceOf(String.class)), is("GASOLEO B")));
        onData(allOf(is(instanceOf(String.class)), is("GASOLEO PREMIUM")));
        onData(anything()).inAdapterView(withId(R.id.listViewComparadas)).atPosition(0).equals(gasolinera1);
        onData(anything()).inAdapterView(withId(R.id.listViewComparadas)).atPosition(1).equals(gasolinera2);
        onData(anything()).inAdapterView(withId(R.id.listViewComparadas)).atPosition(2).equals(gasolinera3);
        onData(anything()).inAdapterView(withId(R.id.listViewComparadas)).atPosition(3).equals(gasolinera4);
        onData(anything()).inAdapterView(withId(R.id.listViewComparadas)).atPosition(4).equals(gasolinera5);

    }

}
