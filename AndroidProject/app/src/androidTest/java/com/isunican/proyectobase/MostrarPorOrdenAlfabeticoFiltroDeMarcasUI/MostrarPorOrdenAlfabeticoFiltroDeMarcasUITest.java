package com.isunican.proyectobase.MostrarPorOrdenAlfabeticoFiltroDeMarcasUI;

import android.widget.ListView;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.R;
import com.isunican.proyectobase.Views.FilterActivity;
import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.app.PendingIntent.getActivity;
import static android.content.Intent.getIntent;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.anything;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.isunican.proyectobase.DataBase.Filtro;
import com.isunican.proyectobase.DataBase.FiltroDAO;
import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(AndroidJUnit4.class)
public class MostrarPorOrdenAlfabeticoFiltroDeMarcasUITest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void marcaPorDefectoUITest(){
        //Primero vamos a la vista de seleccionar filtros y clickamos el boton de guardar configuracion
        onView(withId(R.id.btnListaFiltros)).perform(click());

        for(int i = 0; i < 53; i++){
            //Comprobar que el primero es "Todas"
            onView(withId(R.id.spinnerMarca)).perform(click());
            onData(anything()).atPosition(i).perform(scrollTo(), click());

            switch(i){
                case 0:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("Todas"))));
                    break;
                case 1:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("SHELL"))));
                    break;
                case 2:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("(SIN RÓTULO)"))));
                    break;
                case 3:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("12241"))));
                    break;
                case 4:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("AGROCANTABRIA"))));
                    break;
                case 5:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("AREA DE SERVICIO LA AGÜERA"))));
                    break;
                case 6:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("AREA DE SERVICIO LA PALMERA"))));
                    break;
                case 7:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("AREA DE SERVICIO PAMANES"))));
                    break;
                case 8:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("AREA DE SERVICIO TORRELAVEGA"))));
                    break;
                case 9:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("AVIA"))));
                    break;
                case 10:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("BEROIL"))));
                    break;
                case 11:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("BP CASTRO URDIALES"))));
                    break;
                case 12:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("BP LA OLA MARINA"))));
                    break;
                case 13:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("BP MERCASANTANDER"))));
                    break;
                case 14:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("BP POLANCO"))));
                    break;
                case 15:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("CAMPIEZO -AVIA"))));
                    break;
                case 16:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("CAMPSA"))));
                    break;
                case 17:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("CARREFOUR"))));
                    break;
                case 18:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("CEPSA"))));
                    break;
                case 19:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("COBO"))));
                    break;
                case 20:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("COMBUSCAN"))));
                    break;
                case 21:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("E.S. ARGOMILLA"))));
                    break;
                case 22:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("E.S. CARBURANTES DE ARNUERO S.L."))));
                    break;
                case 23:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("E.S. ISLARES S.L."))));
                    break;
                case 24:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("E.S. MARJA"))));
                    break;
                case 25:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("E.S. SOMAHOZ"))));
                    break;
                case 26:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("E.S. TRECEÑO S.L."))));
                    break;
                case 27:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("E.S. TRINIDAD"))));
                    break;
                case 28:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("E.S.NUEVA MONTAÑA"))));
                    break;
                case 29:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("E.S.SOLBAS"))));
                    break;
                case 30:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("EASYGAS"))));
                    break;
                case 31:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("EESS LA CAVADA"))));
                    break;
                case 32:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("EESSMAS, S.L."))));
                    break;
                case 33:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("EL CENTRO"))));
                    break;
                case 34:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("EROSKI"))));
                    break;
                case 35:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("ES FERMAN LIAÑO"))));
                    break;
                case 36:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("ESTACION DE SERVICIO LIEBANA S.L"))));
                    break;
                case 37:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("EXPOMARINA"))));
                    break;
                case 38:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("G2"))));
                    break;
                case 39:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("GALP"))));
                    break;
                case 40:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("MEROIL"))));
                    break;
                case 41:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("NIEVES"))));
                    break;
                case 42:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("PARTE"))));
                    break;
                case 43:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("PETROLEOS DE CUDEYO"))));
                    break;
                case 44:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("PETRONOR"))));
                    break;
                case 45:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("REPOSTA BEZANA"))));
                    break;
                case 46:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("REPSOL"))));
                    break;
                case 47:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("RUYCO PETROL"))));
                    break;
                case 48:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("RUYCOPETROL, S.L."))));
                    break;
                case 49:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("SHELL"))));
                    break;
                case 50:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("SHELL"))));
                    break;
                case 51:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("STAR PETROLEUM"))));
                    break;
                case 52:
                    onView(withId(R.id.spinnerMarca)).check(matches(withSpinnerText(containsString("VALLE DE LIENDO"))));
                    break;
            }
        }
    }
}

