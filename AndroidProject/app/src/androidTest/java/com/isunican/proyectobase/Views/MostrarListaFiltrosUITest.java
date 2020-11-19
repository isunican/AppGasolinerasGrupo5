package com.isunican.proyectobase.Views;



import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MostrarListaFiltrosUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /* Comprueba si la aplicación muestra la lista de filtros que pueden seleccionarse */
    @Test
    public void listaFiltros() {
        //Entramos en la lista de Filtros
        onView(withId(R.id.btnListaFiltros)).perform(click());
        //Comprobamos el estado de los componentes
        onView(withId(R.id.switchGasoil)).check(matches(withText("Gasoil")));
        onView(withId(R.id.switchGasolina)).check(matches(withText("Gasolina")));

        onView(withId(R.id.switchGasoil)).check(matches(isChecked()));
        onView(withId(R.id.switchGasolina)).check(matches(isNotChecked()));

        onView(withId(R.id.checkFavoritos)).check(matches(isNotChecked()));

        onView(withId(R.id.spinnerMarca)).check(matches(isDisplayed()));
        onView(withId(R.id.spinnerProvincia)).check(matches(isDisplayed()));

        onView(withId(R.id.checkDistancia)).check(matches(isNotChecked()));
        onView(withId(R.id.checkPrecio)).check(matches(isNotChecked()));
    }
}

