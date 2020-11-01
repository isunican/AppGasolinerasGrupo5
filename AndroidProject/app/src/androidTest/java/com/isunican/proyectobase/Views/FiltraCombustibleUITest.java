package com.isunican.proyectobase.Views;

import android.content.Context;


import com.isunican.proyectobase.R;
import com.isunican.proyectobase.Views.MainActivity;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.Views.FilterActivity;
import com.isunican.proyectobase.Model.Filtro;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.util.EnumSet.allOf;
import static java.util.regex.Pattern.matches;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FiltraCombustibleUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /* Filtra por combustible: gasolina */
    @Test
    public void filtraCombustible(){
        onView(withId(R.id.btnListaFiltros)).perform(click());
        onView(withId(R.id.switchGasoil)).perform(click());
        onView(withId(R.id.switchGasolina)).perform(click());
        onView(withId(R.id.btnAceptar)).perform((click()));

        //onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(0)
        //       .onChildView(withId(R.id.switchGasoil)).check(matches(withText("asd")));

        //String texto = getString(R.string)
        //onData(withItemContent(0)).perform(click());
        //onData(allOf(is(instanceOf(String.class)), hasEntry(equalTo("Gasolina"), is(0)))).perform(click());
        //onView(withId(R.id.listViewGasolineras)).check(matches(withText("Gasolina")));
        //R.id.listViewGasolineras.check(matches(withText(containsString("Gasolina"))))
        //onView(withId(R.id.listViewGasolineras)).check(matches(not(isDisplayed())));
        //onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).
        //onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition()
        //onView(withId(R.id.listViewGasolineras)).check(matches(isDisplayed()));
        //onData( allOf(is(instanceOf(String.class)), is("Diesel") ) );
        //onView(withId(R.id.listViewGasolineras)).check(matches(withText(containsString("Gasolina")))));
    }

    /*@Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.isunican.proyectobase", appContext.getPackageName());
    }*/
}
