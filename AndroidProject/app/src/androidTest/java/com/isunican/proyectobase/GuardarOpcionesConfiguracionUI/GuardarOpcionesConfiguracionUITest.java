package com.isunican.proyectobase.GuardarOpcionesConfiguracionUI;


import android.os.SystemClock;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.R;
import com.isunican.proyectobase.Views.MainActivity;


import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;

@RunWith(AndroidJUnit4.class)
public class GuardarOpcionesConfiguracionUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /* Botones de guardar la configuracion con el nombre correctamente */
    //author: Roberto González Jiménez
    @Test
    public void botonesGuardarConfiguracion(){
        //Primero vamos a la vista de seleccionar filtros y clickamos el boton de guardar configuracion
        onView(withId(R.id.btnListaFiltros)).perform(click());
        onView(withId(R.id.btnGuardarConfig)).perform(click());
        //Escogemos un nombre para la nueva configuracion
        onView(allOf(withClassName(endsWith("EditText")))).perform(typeText("Todas las marcas"), closeSoftKeyboard());
        //Comprobamos que existen los dos botones con los nombres correctos
        onView(withText("Aceptar")).check(matches(isDisplayed()));
        onView(withText("Cancelar")).check(matches(isDisplayed()));

        onView(withText("Cancelar")).perform(click());
    }


}
