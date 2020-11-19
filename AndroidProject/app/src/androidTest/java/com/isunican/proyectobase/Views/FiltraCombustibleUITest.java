package com.isunican.proyectobase.Views;

import android.widget.ListView;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.R;

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
import static org.hamcrest.Matchers.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

 /**
 Victor Argueso Cano
 * */
@RunWith(AndroidJUnit4.class)
public class FiltraCombustibleUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /* Filtra por combustible: gasolina */
    @Test
    public void filtraCombustible() {
        //comprobamos la opcion por defecto que es GasoleoA
        onView(withId(R.id.btnListaFiltros)).perform(click());
        onView(withId(R.id.btnCombustibles)).perform(click());

        onView(withText("Aceptar")).perform(click());

        onView(ViewMatchers.withId(R.id.scrollTabla)).perform(ViewActions.swipeUp());
        onView(withId(R.id.btnAceptar)).perform((click()));


        ListView vista = mActivityTestRule.getActivity().findViewById(R.id.listViewGasolineras);
        // Comprobamos que se muestra solo el precio del GasoleoA
        for (int i = 0; i < vista.getAdapter().getCount(); i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewGasoleoA))
                    .check(matches((isDisplayed())));
            onData(anything())
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewGasoleoB))
                    .check(matches(not(isDisplayed())));
        }
        // Filtramos por Todas

        onView(withId(R.id.btnListaFiltros)).perform(click());
        onView(withId(R.id.btnCombustibles)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("GASOLEO A"))).perform(click());

        onData(allOf(is(instanceOf(String.class)), is("TODOS"))).perform(click());
        onView(withText("Aceptar")).perform(click());

        onView(ViewMatchers.withId(R.id.scrollTabla)).perform(ViewActions.swipeUp());
        onView(withId(R.id.btnAceptar)).perform((click()));


        // Comprobamos que se muestran todos los precios
        for (int i = 0; i < vista.getAdapter().getCount(); i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewGasoleoA))
                    .check(matches((isDisplayed())));
            onData(anything())
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewGasoleoB))
                    .check(matches((isDisplayed())));
        }
        onView(withId(R.id.btnListaFiltros)).perform(click());
        onView(withId(R.id.btnCombustibles)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("TODOS"))).perform(click());
        onView(withText("Aceptar")).perform(click());

        onView(ViewMatchers.withId(R.id.scrollTabla)).perform(ViewActions.swipeUp());
        onView(withId(R.id.btnAceptar)).perform((click()));


        //filtramos con varios combustibles
        onView(withId(R.id.btnListaFiltros)).perform(click());
        onView(withId(R.id.btnCombustibles)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("GASOLEO B"))).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("GASOLINA 95 E10"))).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("GAS NATURAL COMPRIMIDO"))).perform(click());
        onView(withText("Aceptar")).perform(click());
        onView(ViewMatchers.withId(R.id.scrollTabla)).perform(ViewActions.swipeUp());
        onView(withId(R.id.btnAceptar)).perform((click()));
        //Comprobamos que se muestran los 3 seleccionados y que no se muestran algunos de los demás
        for (int i = 0; i < vista.getAdapter().getCount(); i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewGasoleoA))
                    .check(matches(not(isDisplayed())));
            onData(anything())
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewBiodiesel))
                    .check(matches(not(isDisplayed())));
            onData(anything())
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewBioetanol))
                    .check(matches(not(isDisplayed())));
            onData(anything())
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewGasoleoB))
                    .check(matches((isDisplayed())));
            onData(anything())
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewGasolina95E10))
                    .check(matches((isDisplayed())));
            onData(anything())
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewGasNaturalComprimido))
                    .check(matches((isDisplayed())));
        }

    }
}
