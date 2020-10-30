package com.isunican.proyectobase.Views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.isunican.proyectobase.Model.*;
import com.isunican.proyectobase.R;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {

    Button btnGuardarConfig;

    Spinner spinnerMarca;
    Spinner spinnerProvincia;
    ArrayList<Gasolinera> gasolineras;
    ArrayList<String> marcas;
    ArrayList<String> provincias;

    CheckBox checkDistancia;
    CheckBox checkPrecio;
    CheckBox checkFavoritos;

    RadioButton r_distancia_mayorA;
    RadioButton r_distancia_menorA;

    RadioButton r_precio_mayorAMenor;
    RadioButton r_precio_menorAMayor;

    EditText textNumberDistancia;

    Switch switchGasoil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        spinnerMarca = findViewById(R.id.spinnerMarca);
        spinnerProvincia = findViewById(R.id.spinnerProvincia);

        switchGasoil = findViewById(R.id.switchGasoil);
        //La opción de filtro por defecto debe ser Gasoil
        switchGasoil.setChecked(true);

        marcas = new ArrayList<String>();
        provincias = new ArrayList<String>();

        gasolineras = (ArrayList<Gasolinera>) getIntent().getSerializableExtra("list_gasolineras");

        //La primera opción debe ser "nada seleccionado"
        marcas.add("Ninguna");
        provincias.add("Ninguna");

        //Rellenamos las listas locales para que tengan toda la información de marcas
        //y provincias.
        for (Gasolinera g: gasolineras) {
            if (!marcas.contains(g.getRotulo())) {
                marcas.add(g.getRotulo());
            }

            if(!provincias.contains(g.getProvincia())){
                provincias.add(g.getProvincia());
            }
        }

        //Construimos el adapter del spinner de manera que éste tenga de posibles opciones seleccionables todas las marcas
        ArrayAdapter<CharSequence> adpMarca = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, marcas);
        spinnerMarca.setAdapter(adpMarca);
        //Opción por defecto: ninguna marca seleccionada
        spinnerMarca.setSelection(0);

        //Construimos el adapter del spinner de manera que éste tenga de posibles opciones seleccionables todas las marcas
        ArrayAdapter<CharSequence> adpProvincia = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, provincias);
        spinnerProvincia.setAdapter(adpProvincia);
        //Opción por defecto: ninguna provincia seleccionada
        spinnerProvincia.setSelection(0);

        //Campo de texto en el que se especifica la distancia
        textNumberDistancia = findViewById(R.id.editTextDistancia);
        //Botones "mayor que" y "menor que"
        r_distancia_mayorA = findViewById(R.id.radioButtonMayor);
        r_distancia_menorA = findViewById(R.id.radioButtonMenor);

        //Botones "mayor a menor" y "menor a mayor"
        r_precio_mayorAMenor = findViewById(R.id.radioButtonAsc);
        r_precio_menorAMayor = findViewById(R.id.radioButtonDesc);

        //Inicialmente están desactivados, ya que no se ha especificado ningún filtro.
        r_distancia_mayorA.setEnabled(false);
        r_distancia_menorA.setEnabled(false);
        textNumberDistancia.setEnabled(false);

        r_precio_mayorAMenor.setEnabled(false);
        r_precio_menorAMayor.setEnabled(false);

        //CheckBox que indica si se está filtrando por distancia
        checkDistancia = findViewById(R.id.checkDistancia);
        //CheckBox que indica si se está filtrando por precio
        checkPrecio = findViewById(R.id.checkPrecio);
        //CheckBox que indica si se está filtrando por gasolineras guardadas como favoritas
        checkFavoritos = findViewById(R.id.checkFavoritos);
        //TODO De momento esta función está desactivada
        checkFavoritos.setEnabled(false);

        //Si el usuario indica que quiere filtrar por distancia, se habilitan los botones
        //de selección de orden y la caja de texto.
        checkDistancia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    r_distancia_mayorA.setEnabled(true);
                    r_distancia_menorA.setEnabled(true);
                    textNumberDistancia.setEnabled(true);
                } else {
                    r_distancia_mayorA.setEnabled(false);
                    r_distancia_menorA.setEnabled(false);
                    textNumberDistancia.setEnabled(false);
                }
            }
        });

        //Si el usuario indica que quiere ordenar por precio, se habilitan los botones
        //de selección de orden.
        checkPrecio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    r_precio_mayorAMenor.setEnabled(true);
                    r_precio_menorAMayor.setEnabled(true);
                } else {
                    r_precio_mayorAMenor.setEnabled(false);
                    r_precio_menorAMayor.setEnabled(false);
                }
            }
        });

        btnGuardarConfig = findViewById(R.id.btnGuardarConfig);


        btnGuardarConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createConfirmationDialog();
            }
        });

    }

    public void createConfirmationDialog(){
        //Este AlertDialog será el que guarde el nombre que el usuario quiera darle a su configuración de filtros
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Introduzca nombre para su configuración");
        builder.setTitle("Nombre de la configuración");
        final EditText nombreConfiguracion = new EditText(FilterActivity.this);
        builder.setView(nombreConfiguracion);
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nombre = nombreConfiguracion.getText().toString();
                Toast toast;
                if(nombre.isEmpty()){
                    toast = Toast.makeText(getApplicationContext(),"Introduzca un nombre", Toast.LENGTH_LONG);
                } else {
                    //TODO Habría que guardar ese nombre en algún lado
                    toast = Toast.makeText(getApplicationContext(), "Configuración guardada",Toast.LENGTH_LONG);
                }
                toast.show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.create().show();
    }
}