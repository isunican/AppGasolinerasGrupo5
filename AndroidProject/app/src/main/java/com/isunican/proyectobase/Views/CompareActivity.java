package com.isunican.proyectobase.Views;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.R;
import com.isunican.proyectobase.Views.MainActivity.GasolineraArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class CompareActivity extends AppCompatActivity {

    ListView listViewComparadas;
    ArrayList<Gasolinera> gasolinerasSeleccionadas;
    GasolineraArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        // Muestra el logo en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.por_defecto_mod);

        //List<Gasolinera> gasolinerasAComparar =

        listViewComparadas = findViewById(R.id.listViewComparadas);
        gasolinerasSeleccionadas = (ArrayList<Gasolinera>) getIntent().getSerializableExtra("list_gasolineras_seleccionadas");

        //adapter = new GasolineraArrayAdapter(this,0,gasolinerasSeleccionadas);


    }
}
