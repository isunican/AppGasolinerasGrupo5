package com.isunican.proyectobase.Views;


import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.DataBase.Filtro;
import com.isunican.proyectobase.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MostrarListaFiltrosUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /* Comprueba si la aplicación muestra los filtros que se están utilizando actualmente */
    @Test
    public void listaFiltros() {
        //Entramos en la lista de Filtros
        onView(withId(R.id.btnListaFiltros)).perform(click());
        //Filtramos por gasolina y gasoil
        onView(withId(R.id.switchGasoil)).perform(click());
        onView(withId(R.id.switchGasolina)).perform(click());
        //Aceptamos
        onView(withId(R.id.btnAceptar)).perform((click()));

        //Filtro d = mActivityTestRule.getActivity().getIntent().getExtras().getParcelable("filtro");

        //Volvemos a entrar en la lista de filtros
        onView(withId(R.id.btnListaFiltros)).perform(click());
        //Comprobamos que los switches switchGasolina y switchGasoil están activados
        onView(withId(R.id.switchGasolina)).check(matches(isSelected()));
        onView(withId(R.id.switchGasoil)).check(matches(isSelected()));

        //TODO Extender este Test para cuando haya más filtros implementados
    }
}
