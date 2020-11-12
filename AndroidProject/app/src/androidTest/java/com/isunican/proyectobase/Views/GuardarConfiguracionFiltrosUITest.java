package com.isunican.proyectobase.Views;

import android.content.Context;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
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
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class GuardarConfiguracionFiltrosUITest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.isunican.proyectobase", appContext.getPackageName());
    }

    @Test
    public void guardaConfiguracionTest (){

        //Selección del boton lista filtros para mostrar la lista de filtros
        onView(withId(R.id.btnListaFiltros)).check(matches(withText("MOSTRAR FILTROS")));
        onView(withId(R.id.btnListaFiltros)).perform(click());
        //Posible Selección de filtros
        onView(withId(R.id.btnGuardarConfig)).check(matches(withText("GUARDAR CONFIGURACIÓN")));
        //onView(withId(R.id.switchGasoil)).perform(click());
        //onView(withId(R.id.switchGasolina)).perform(click());
        /**onView(withId(R.id.spinnerProvincia)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CANTABRIA"))).perform(click());
        onView(withId(R.id.spinnerMarca)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CEPSA"))).perform(click());
        onView(withId(R.id.checkDistancia)).perform(click());
        onView(withId(R.id.radioButtonMenor)).perform(click());
        onView(withId(R.id.editTextDistancia)).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.checkPrecio)).perform(click());
        onView(withId(R.id.radioButtonAsc)).perform(click());*/
        //Guardar selección de filtros con nombre
        onView(ViewMatchers.withId(R.id.scrollTabla)).perform(ViewActions.swipeUp());
        onView(withId(R.id.btnGuardarConfig)).perform(click());
        onView(allOf(withClassName(endsWith("EditText")))).perform(typeText("Gasolina"), closeSoftKeyboard());
        onView(withText("Sí")).perform(click());
    }
}
