package com.isunican.proyectobase.Views;

import androidx.appcompat.app.AppCompatActivity;
import com.isunican.proyectobase.Presenter.*;
import com.isunican.proyectobase.Model.*;
import com.isunican.proyectobase.R;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    Spinner spinnerMarca;
    Spinner spinnerProvincia;
    ArrayList<Gasolinera> gasolineras;
    ArrayList<String> marcas;
    ArrayList<String> provincias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        spinnerMarca = findViewById(R.id.spinnerMarca);
        spinnerProvincia = findViewById(R.id.spinnerProvincia);

        marcas = new ArrayList<String>();
        provincias = new ArrayList<String>();

        Bundle datos = this.getIntent().getExtras();
        gasolineras = (ArrayList<Gasolinera>) getIntent().getSerializableExtra("list_gasolineras");

        marcas.add("Ninguna");
        provincias.add("Ninguna");

        for (Gasolinera g: gasolineras) {
            if (!marcas.contains(g.getRotulo())) {
                marcas.add(g.getRotulo());
            }

            if(!provincias.contains(g.getProvincia())){
                provincias.add(g.getProvincia());
            }
        }

        ArrayAdapter<CharSequence> adpMarca = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, marcas);
        spinnerMarca.setAdapter(adpMarca);
        //Opción por defecto: ninguna marca seleccionada
        spinnerMarca.setSelection(0);

        spinnerMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  TODO
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO
            }
        });

        ArrayAdapter<CharSequence> adpProvincia = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, provincias);
        spinnerProvincia.setAdapter(adpProvincia);
        //Opción por defecto: ninguna provincia seleccionada
        spinnerProvincia.setSelection(0);

        spinnerProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  TODO
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO
            }
        });

    }
}