package com.isunican.proyectobase;

import android.widget.CheckBox;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class CompararGasolinerasUITest
{
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void CompararDosGasolineras()
    {

        //Seleccionamos dos gasolineras a comparar(con gasoleo A por defecto)
        DataInteraction gasolinera1 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(0);
        DataInteraction chk1 = gasolinera1.onChildView(withId(R.id.checkGasolinera));
        chk1.perform(click());

        DataInteraction gasolinera2 = onData(anything()).inAdapterView(withId(R.id.listViewGasolineras)).atPosition(1);
        DataInteraction chk2 = gasolinera2.onChildView(withId(R.id.checkGasolinera));
        chk2.perform(click());

        //Comparamos las dos
        onView(withId(R.id.btnComparar)).perform(click());
        
    }//comparardosgasolineras

}
