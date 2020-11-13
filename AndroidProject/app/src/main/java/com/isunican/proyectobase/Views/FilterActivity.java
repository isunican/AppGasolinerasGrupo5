package com.isunican.proyectobase.Views;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
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

public class FilterActivity extends AppCompatActivity {

    Button btnGuardarConfig;
    Button btnAplicarFiltros;
    Button btnCombustibles;

    Spinner spinnerMarca;
    Spinner spinnerProvincia;
    ArrayList<Gasolinera> gasolineras;
    ArrayList<String> marcas;
    ArrayList<String> provincias;

    CheckBox checkDistancia;
    CheckBox checkPrecio;
    CheckBox checkFavoritos;

    RadioButton rdistanciaMayorA;
    RadioButton rdistanciaMenorA;

    RadioButton rprecioMayorAMenor;
    RadioButton rprecioMenorrAMayor;

    EditText textNumberDistancia;


    // Se crea el filtro
    private FiltroDAO filtroDAO;
    private Filtro filtro;

    private String nombre = "";
    private ArrayList<String> combustiblesSeleccionados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        spinnerMarca = findViewById(R.id.spinnerMarca);
        spinnerProvincia = findViewById(R.id.spinnerProvincia);

        // Filtro
        filtroDAO = FiltroDAO.get(this);
        filtro = getIntent().getExtras().getParcelable("filtro");
        combustiblesSeleccionados = filtro.getCombustibles();

        marcas = new ArrayList<>();
        provincias = new ArrayList<>();

        gasolineras = (ArrayList<Gasolinera>) getIntent().getSerializableExtra("list_gasolineras");

        //La primera opción debe ser "nada seleccionado"
        marcas.add("Ninguna");
        provincias.add("Ninguna");

        //Rellenamos las listas locales para que tengan toda la información de marcas
        //y provincias.
        for (Gasolinera g : gasolineras) {
            if (!marcas.contains(g.getRotulo())) {
                marcas.add(g.getRotulo());
            }

            if (!provincias.contains(g.getProvincia())) {
                provincias.add(g.getProvincia());
            }
        }

        //Construimos el adapter del spinner de manera que éste tenga de posibles opciones seleccionables todas las marcas
        ArrayAdapter<CharSequence> adpMarca = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, marcas);
        spinnerMarca.setAdapter(adpMarca);
        //Opción por defecto: ninguna marca seleccionada
        spinnerMarca.setSelection(0);

        /**spinnerMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //  TODO Acción para cuando se seleccione una opción del spinner
        }

        @Override public void onNothingSelected(AdapterView<?> parent) {
        //TODO Acción para cuando se seleccione una opción del spinner
        }
        });**/

        //Construimos el adapter del spinner de manera que éste tenga de posibles opciones seleccionables todas las marcas
        ArrayAdapter<CharSequence> adpProvincia = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, provincias);
        spinnerProvincia.setAdapter(adpProvincia);
        //Opción por defecto: ninguna provincia seleccionada
        spinnerProvincia.setSelection(0);

        /**spinnerProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //  TODO Acción para cuando se seleccione una opción del spinner
        }

        @Override public void onNothingSelected(AdapterView<?> parent) {
        //TODO Acción para cuando se seleccione una opción del spinner
        }
        });**/

        //Campo de texto en el que se especifica la distancia
        textNumberDistancia = findViewById(R.id.editTextDistancia);
        //Botones "mayor que" y "menor que"
        rdistanciaMayorA = findViewById(R.id.radioButtonMayor);
        rdistanciaMenorA = findViewById(R.id.radioButtonMenor);

        //Botones "mayor a menor" y "menor a mayor"
        rprecioMayorAMenor = findViewById(R.id.radioButtonAsc);
        rprecioMenorrAMayor = findViewById(R.id.radioButtonDesc);

        //Inicialmente están desactivados, ya que no se ha especificado ningún filtro.
        rdistanciaMayorA.setEnabled(false);
        rdistanciaMenorA.setEnabled(false);
        textNumberDistancia.setEnabled(false);

        rprecioMayorAMenor.setEnabled(false);
        rprecioMenorrAMayor.setEnabled(false);

        //CheckBox que indica si se está filtrando por distancia
        checkDistancia = findViewById(R.id.checkDistancia);
        //CheckBox que indica si se está filtrando por precio
        checkPrecio = findViewById(R.id.checkPrecio);
        //CheckBox que indica si se está filtrando por gasolineras guardadas como favoritas
        checkFavoritos = findViewById(R.id.checkFavoritos);
        //TODO De momento estas funciones están desactivadas
        checkFavoritos.setEnabled(false);
        checkDistancia.setEnabled(false);
        spinnerProvincia.setEnabled(false);
        spinnerMarca.setEnabled(false);

        //Si el usuario indica que quiere filtrar por distancia, se habilitan los botones
        //de selección de orden y la caja de texto.
        checkDistancia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rdistanciaMayorA.setEnabled(true);
                    rdistanciaMenorA.setEnabled(true);
                    textNumberDistancia.setEnabled(true);
                } else {
                    rdistanciaMayorA.setEnabled(false);
                    rdistanciaMenorA.setEnabled(false);
                    textNumberDistancia.setEnabled(false);
                }
            }
        });

        //Si el usuario indica que quiere ordenar por precio, se habilitan los botones
        //de selección de orden.
        checkPrecio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rprecioMayorAMenor.setEnabled(true);
                    rprecioMenorrAMayor.setEnabled(true);
                } else {
                    rprecioMayorAMenor.setEnabled(false);
                    rprecioMenorrAMayor.setEnabled(false);
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


        btnAplicarFiltros = findViewById(R.id.btnAceptar);
        btnAplicarFiltros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aceptarFiltros();
            }
        });

        btnCombustibles = findViewById(R.id.btnCombustibles);
        btnCombustibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarListaCombustibles();
            }
        });
    }

    /**
     * Se activa onClick() cuando se presiona el boton de Aceptar. Se pasa la configuracion de
     * filtro seleccionada al view correspondiente(MainActivity).
     * Y se cierra esta activity(FilterActivity).
     */
    public void aceptarFiltros() {
        Filtro filtroSeleccionado = new Filtro(nombre, combustiblesSeleccionados);
        Intent intent = new Intent().putExtra("filtro", filtroSeleccionado);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void mostrarListaCombustibles() {
        final String[] combustibles = new String[]{"TODOS", "GASOLEO A", "GASOLEO B",
                "GASOLEO PREMIUM", "GASOLINA 95 E10", "GASOLINA 95 E5",
                "GASOLINA 95 E5 PREMIUM", "GASOLINA 98 E10", "GASOLINA 98 E5",
                "BIODIESEL", "BIOETANOL", "GAS NATURAL COMPRIMIDO", "GAS NATURAL LICUADO",
                "GASES LICUADOS PETROLEO", "HIDROGENO"};

        final ArrayList<Integer> itemsSelected = new ArrayList<Integer>();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(("Combustibles"));

        //Opción por defecto: TODOS
        final boolean[] checked = new boolean[]{false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false,};

        //Mantiene las cajas seleccionadas
        for (String combustible : combustiblesSeleccionados) {
            switch (combustible) {
                case "TODOS":
                    itemsSelected.add(0); // es la opción todos
                    checked[0] = true;
                    break;
                case "GASOLEO A":
                    itemsSelected.add(1); // es la opción todos
                    checked[1] = true;
                    break;
                case "GASOLINA 95 E5":
                    itemsSelected.add(5); // es la opción todos
                    checked[5] = true;
                    break;
            }
        }


        builder.setMultiChoiceItems(combustibles, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int selectedItemId, boolean isChecked) {
                if (isChecked) {
                    itemsSelected.add(selectedItemId);
                } else if (itemsSelected.contains(selectedItemId)) {
                    itemsSelected.remove(Integer.valueOf(selectedItemId));
                }
            }
        });

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                combustiblesSeleccionados = new ArrayList<>();
                Log.d("COMBUSTIBLE", "length" + combustiblesSeleccionados.size());
                for (int i = 0; i < itemsSelected.size(); i++) {
                    Log.d("COMBUSTIBLE", "SELECCIONADO " + combustibles[itemsSelected.get(i)]);
                    combustiblesSeleccionados.add(combustibles[itemsSelected.get(i)]);
                }


            }
        });

        builder.setNegativeButton("Atrás", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Aquí habría que hacer cosas con las opciones que se hayan seleccionado
            }
        });


        AlertDialog dialogCombustibles = builder.create();
        dialogCombustibles.setCanceledOnTouchOutside(false);
        dialogCombustibles.show();
    }

    public void createConfirmationDialog() {
        //Este AlertDialog será el que guarde el nombre que el usuario quiera darle a su configuración de filtros
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Introduzca nombre para su configuración");
        builder.setTitle("Nombre de la configuración");
        final EditText nombreConfiguracion = new EditText(FilterActivity.this);
        builder.setView(nombreConfiguracion);
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nombre = nombreConfiguracion.getText().toString();
                Toast toast;
                if (nombre.isEmpty()) {
                    toast = Toast.makeText(getApplicationContext(), "Introduzca un nombre", Toast.LENGTH_LONG);
                } else {
                    // Añade nueva configuración de filtro a la base de datos de filtros
                    filtro.setNombre(nombre);
                    filtroDAO.addFiltro(filtro);
                    toast = Toast.makeText(getApplicationContext(), "Configuración guardada", Toast.LENGTH_LONG);
                }
                toast.show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog confirmation = builder.create();
        confirmation.show();
    }
}