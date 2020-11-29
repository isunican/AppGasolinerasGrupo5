package com.isunican.proyectobase.ModificarPrecioCombustiblesNegativosUI;



import android.widget.ListView;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import com.isunican.proyectobase.R;
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
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class ModificarPrecioCombustiblesNegativosUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /* Prueba de interfaz de usuario, Modificar precio combustibles valor negativo:
        B) Algunos tipos de combustibles con otros filtros
    */
    //author: Roberto González Jiménez
    @Test
    public void precioCombustiblesNegativos(){
        //Primero vamos a la vista de seleccionar filtros y clickamos el boton de guardar configuracion
        onView(withId(R.id.btnListaFiltros)).perform(click());
        onView(withId(R.id.btnCombustibles)).perform(click());

        //Desactivamos la opcion por defecto, que es la del gasoleo A
        onData(allOf(is(instanceOf(String.class)), is("GASOLEO A"))).perform(click());

        //Activamos los checks con los combustibles a filtrar: "GASOLEO B", "GASOLINA 95 E5" y "BIODIESEL"
        onData(allOf(is(instanceOf(String.class)), is("GASOLEO B"))).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("GASOLINA 95 E5"))).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("BIODIESEL"))).perform(click());


        //Pulsamos aceptar para filtrar por esos combustibles
        onView(withText("ACEPTAR")).perform(click());

        //Tambien filtraremos por la marca GALP y aceptamos.
        onView(withId(R.id.spinnerMarca)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("GALP"))).perform(click());
        onView(withId(R.id.btnAceptar)).perform((click()));

        ListView vista = mActivityTestRule.getActivity().findViewById(R.id.listViewGasolineras);
        // Comprobamos que se muestra correctamente lo filtrado y que muestra N/D en los combustibles que no posea la gasolinera
        for (int i = 0; i < vista.getAdapter().getCount(); i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewRotulo))
                    .check(matches(withText("GALP")));
            onData(anything())
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewGasoleoB))
                    .check(matches(isDisplayed()));
            onData(anything())
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewGasolina95E5))
                    .check(matches(isDisplayed()));
            onData(anything()) //Estas gasolineras no tienen Biodiesel, por lo tanto comprobamos que tiene " N/D"
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewBiodiesel))
                    .check(matches(withText(" N/D")));
            onData(anything()) //Que no muestra otro tipo de combustible
                    .inAdapterView(withId(R.id.listViewGasolineras))
                    .atPosition(i)
                    .onChildView(withId(R.id.textViewGasoleoA))
                    .check(matches(not(isDisplayed())));
        }
    }


}
