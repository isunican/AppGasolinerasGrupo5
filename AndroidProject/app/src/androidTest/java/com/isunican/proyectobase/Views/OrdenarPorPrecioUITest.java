package com.isunican.proyectobase.Views;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class OrdenarPorPrecioUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /* Ordenar por precio, comprobamos que ordena correctamente de mayor a menor y viceversa */
    //author: Roberto González Jiménez
    @Test
    public void ordenaPorPrecio() throws InterruptedException {
        //Primero vamos a la vista de seleccionar filtros y activamos el de ordenar por precio
        onView(withId(R.id.btnListaFiltros)).perform(click());
        onView(ViewMatchers.withId(R.id.scrollTabla)).perform(ViewActions.swipeUp());
        onView(withId(R.id.checkPrecio)).perform(click());
        //Vamos a comprobar primero si los ordena de mayor a menor
        onView(withId(R.id.radioButtonAsc)).perform(click());
        onView(withId(R.id.btnAceptar)).perform((click()));

        /*
         Podemos observar que en la lista de gasolineras se muestran ordenados de mayor a menor
         bajamos un poco el scroll y con unas pausas se puede ver que estan ordenados
         */
        Thread.sleep(2000);
        onView(ViewMatchers.withId(R.id.listViewGasolineras)).perform(ViewActions.swipeUp());
        Thread.sleep(2000);
        onView(ViewMatchers.withId(R.id.listViewGasolineras)).perform(ViewActions.swipeUp());
        Thread.sleep(2000);

        //Vemos que se ha guardado la opcion de ordenar al volver a clickar a la lista de filtros
        onView(withId(R.id.btnListaFiltros)).perform(click());
        onView(ViewMatchers.withId(R.id.scrollTabla)).perform(ViewActions.swipeUp());
        //Ahora realizamos lo mismo pero ordenando de menor a mayor precio
        onView(withId(R.id.radioButtonDesc)).perform(click());
        onView(withId(R.id.btnAceptar)).perform((click()));

        /*
         Podemos observar que en la lista de gasolineras se muestran ordenados de menor a mayor
         bajamos un poco el scroll y con unas pausas se puede ver que estan ordenados
         */
        Thread.sleep(2000);
        onView(ViewMatchers.withId(R.id.listViewGasolineras)).perform(ViewActions.swipeUp());
        Thread.sleep(2000);
        onView(ViewMatchers.withId(R.id.listViewGasolineras)).perform(ViewActions.swipeUp());
        Thread.sleep(2000);

        //Vemos que se ha guardado la opcion de ordenar al volver a clickar a la lista de filtros
        onView(withId(R.id.btnListaFiltros)).perform(click());
        onView(ViewMatchers.withId(R.id.scrollTabla)).perform(ViewActions.swipeUp());
    }
}
