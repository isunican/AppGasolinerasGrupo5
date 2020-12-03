package com.isunican.proyectobase.MostrarPorOrdenAlfabeticoFiltroDeMarcasUI;

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
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;


@RunWith(AndroidJUnit4.class)
public class MostrarPorOrdenAlfabeticoFiltroDeMarcasUITest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Test de interfaz para comprobar que estan ordenadas las marcas
     * UI1. CU Mostrar por orden alfabetico filtro de marcas
     * @Author Pablo Villegas Fernandez
     */
    @Test
    public void marcasOrdenadasUITest(){
        //Primero vamos a la vista de seleccionar filtros
        onView(withId(R.id.btnListaFiltros)).perform(click());
        // Seleccionamos el spinner parar ver las marcas
        onView(withId(R.id.spinnerMarca)).perform(click());

        // Comprobamos que estan ordenadas alfabeticamente
        onData(anything()).atPosition(0).check(matches(withText("Todas")));
        onData(anything()).atPosition(1).check(matches(withText(" SHELL")));
        onData(anything()).atPosition(2).check(matches(withText("(SIN RÓTULO)")));
        onData(anything()).atPosition(3).check(matches(withText("12241")));
        onData(anything()).atPosition(4).check(matches(withText("AGROCANTABRIA")));
        onData(anything()).atPosition(5).check(matches(withText("AREA DE SERVICIO LA AGÜERA")));
        onData(anything()).atPosition(6).check(matches(withText("AREA DE SERVICIO LA PALMERA")));
        onData(anything()).atPosition(7).check(matches(withText("AREA DE SERVICIO PAMANES")));
        onData(anything()).atPosition(8).check(matches(withText("AREA DE SERVICIO TORRELAVEGA")));
        onData(anything()).atPosition(9).check(matches(withText("AVIA")));
        onData(anything()).atPosition(10).check(matches(withText("BEROIL")));
        onData(anything()).atPosition(11).check(matches(withText("BP CASTRO URDIALES")));
        onData(anything()).atPosition(12).check(matches(withText("BP LA OLA MARINA")));
        onData(anything()).atPosition(13).check(matches(withText("BP MERCASANTANDER")));
        onData(anything()).atPosition(14).check(matches(withText("BP POLANCO")));
        onData(anything()).atPosition(15).check(matches(withText("CAMPIEZO -AVIA")));
        onData(anything()).atPosition(16).check(matches(withText("CAMPSA")));
        onData(anything()).atPosition(17).check(matches(withText("CARREFOUR")));
        onData(anything()).atPosition(18).check(matches(withText("CEPSA")));
        onData(anything()).atPosition(19).check(matches(withText("COBO")));
        onData(anything()).atPosition(20).check(matches(withText("COMBUSCAN")));
        onData(anything()).atPosition(21).check(matches(withText("E.S. ARGOMILLA")));
        onData(anything()).atPosition(22).check(matches(withText("E.S. CARBURANTES DE ARNUERO S.L.")));
        onData(anything()).atPosition(23).check(matches(withText("E.S. ISLARES S.L.")));
        onData(anything()).atPosition(24).check(matches(withText("E.S. MARJA")));
        onData(anything()).atPosition(25).check(matches(withText("E.S. SOMAHOZ")));
        onData(anything()).atPosition(26).check(matches(withText("E.S. TRECEÑO S.L.")));
        onData(anything()).atPosition(27).check(matches(withText("E.S. TRINIDAD")));
        onData(anything()).atPosition(28).check(matches(withText("E.S.NUEVA MONTAÑA")));
        onData(anything()).atPosition(29).check(matches(withText("E.S.SOLBAS")));
        onData(anything()).atPosition(30).check(matches(withText("EASYGAS")));
        onData(anything()).atPosition(31).check(matches(withText("EESS LA CAVADA")));
        onData(anything()).atPosition(32).check(matches(withText("EESSMAS, S.L.")));
        onData(anything()).atPosition(33).check(matches(withText("EL CENTRO")));
        onData(anything()).atPosition(34).check(matches(withText("EROSKI")));
        onData(anything()).atPosition(35).check(matches(withText("ES FERMAN LIAÑO")));
        onData(anything()).atPosition(36).check(matches(withText("ESTACION DE SERVICIO LIEBANA S.L")));
        onData(anything()).atPosition(37).check(matches(withText("EXPOMARINA")));
        onData(anything()).atPosition(38).check(matches(withText("G2")));
        onData(anything()).atPosition(39).check(matches(withText("GALP")));
        onData(anything()).atPosition(40).check(matches(withText("MEROIL")));
        onData(anything()).atPosition(41).check(matches(withText("NIEVES")));
        onData(anything()).atPosition(42).check(matches(withText("PARTE")));
        onData(anything()).atPosition(43).check(matches(withText("PETROLEOS DE CUDEYO")));
        onData(anything()).atPosition(44).check(matches(withText("PETRONOR")));
        onData(anything()).atPosition(45).check(matches(withText("REPOSTA BEZANA")));
        onData(anything()).atPosition(46).check(matches(withText("REPSOL")));
        onData(anything()).atPosition(47).check(matches(withText("RUYCO PETROL")));
        onData(anything()).atPosition(48).check(matches(withText("RUYCOPETROL, S.L.")));
        onData(anything()).atPosition(49).check(matches(withText("SHELL")));
        onData(anything()).atPosition(50).check(matches(withText("SHELL ")));
        onData(anything()).atPosition(51).check(matches(withText("STAR PETROLEUM")));
        onData(anything()).atPosition(52).check(matches(withText("VALLE DE LIENDO")));
        }
    }

