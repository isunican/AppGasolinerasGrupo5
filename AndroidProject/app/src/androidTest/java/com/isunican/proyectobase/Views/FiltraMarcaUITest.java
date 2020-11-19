package com.isunican.proyectobase.Views;

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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class FiltraMarcaUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /* Filtrar por una marca, seleccionada del spinner */
    //author: Roberto González Jiménez
    @Test
    public void filtraMarca(){
        //Primero vamos a la vista de seleccionar filtros y clickamos en el spinner de las marcas.
        onView(withId(R.id.btnListaFiltros)).perform(click());
        onView(withId(R.id.spinnerMarca)).perform(click());
        //Aqui podemos filtrar por la marca que queramos o por todas, por ejemplo elegimos "CARREFOUR" y aplicamos filtros.
        onData(allOf(is(instanceOf(String.class)), is("CARREFOUR"))).perform(click());
        onView(withId(R.id.btnAceptar)).perform((click()));

        //Podemos observar que en la lista de gasolineras se muestran solo las de CARREFOUR.
        onView(ViewMatchers.withId(R.id.listViewGasolineras)).perform(ViewActions.swipeUp());

        //Vemos que la marca que está en el spinner por defecto es la seleccionada anteriormente.
        onView(withId(R.id.btnListaFiltros)).perform(click());
        onView(withId(R.id.spinnerMarca)).perform(click());
    }
}
