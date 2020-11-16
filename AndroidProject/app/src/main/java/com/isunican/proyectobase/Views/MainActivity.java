package com.isunican.proyectobase.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.isunican.proyectobase.DataBase.Filtro;
import com.isunican.proyectobase.DataBase.FiltroDAO;
import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
import com.isunican.proyectobase.R;

/*
------------------------------------------------------------------
    Vista principal

    Presenta los datos de las gasolineras en formato lista.

------------------------------------------------------------------
*/
public class MainActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    PresenterGasolineras presenterGasolineras;

    private static final String TODOS = "TODOS";
    private static final String GASOLEO_A = "GASOLEO A";
    private static final String GASOLEO_B = "GASOLEO B";
    private static final String GASOLEO_PREMIUM = "GASOLEO PREMIUM";
    private static final String GASOLINA_95_E10 = "GASOLINA 95 E10";
    private static final String GASOLINA_95_E5 = "GASOLINA 95 E5";
    private static final String GASOLINA_95_E5_PREMIUM = "GASOLINA 95 E5 PREMIUM";
    private static final String GASOLINA_98_E10 = "GASOLINA 98 E10";
    private static final String GASOLINA_98_E5 = "GASOLINA 98 E5";
    private static final String BIODIESEL = "BIODIESEL";
    private static final String BIOETANOL = "BIOETANOL";
    private static final String GAS_NATURAL_COMPRIMIDO = "GAS NATURAL COMPRIMIDO";
    private static final String GAS_NATURAL_LICUADO = "GAS NATURAL LICUADO";
    private static final String GASES_LICUADOS_PETROLEO = "GASES LICUADOS PETROLEO";
    private static final String HIDROGENO = "HIDROGENO";


    Button listaFiltros;

    // Vista de lista y adaptador para cargar datos en ella
    ListView listViewGasolineras;
    ArrayAdapter<Gasolinera> adapter;

    // Barra de progreso circular para mostar progeso de carga
    ProgressBar progressBar;

    // Swipe and refresh (para recargar la lista con un swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;

    // Se crea el filtro
    private Filtro filtro;
    ArrayList<Gasolinera> gasolineras = new ArrayList<Gasolinera>();

    /**
     * onCreate
     * <p>
     * Crea los elementos que conforman la actividad
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.presenterGasolineras = new PresenterGasolineras();

        // Barra de progreso
        // https://materialdoc.com/components/progress/
        progressBar = new ProgressBar(MainActivity.this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        RelativeLayout layout = findViewById(R.id.activity_precio_gasolina);
        layout.addView(progressBar, params);

        // Muestra el logo en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.por_defecto_mod);

        // Filtro
        FiltroDAO filtroDAO = FiltroDAO.get(this);
        filtro = new Filtro();
        // Si la base de datos esta vacía, eso quiere decir que es la primera vez que se usa esta app
        // en este dispositivo, se carga en la base de datos el filtro por defecto
        if (filtroDAO.getFiltro("DEFECTO") == null) {
            filtroDAO.addFiltro(filtro);
        }

        // Swipe and refresh
        // Al hacer swipe en la lista, lanza la tarea asíncrona de carga de datos
        mSwipeRefreshLayout = findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new CargaDatosGasolinerasTask(MainActivity.this).execute();
            }
        });

        // Al terminar de inicializar todas las variables
        // se lanza una tarea para cargar los datos de las gasolineras
        // Esto se ha de hacer en segundo plano definiendo una tarea asíncrona
        new CargaDatosGasolinerasTask(this).execute();

        listaFiltros = findViewById(R.id.btnListaFiltros);
        listaFiltros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilterActivity();
            }
        });

    }

    //Abrir menú filtros
    public void openFilterActivity() {
        Intent intentFilterActivity = new Intent(this, FilterActivity.class);
        //Le pasamos la lista de gasolineras para que la nueva ventana tenga la información
        //completa de las marcas y provincias con las que se trabaja.
        ArrayList<Gasolinera> gs = new ArrayList<>(presenterGasolineras.getGasolineras());
        intentFilterActivity.putExtra("list_gasolineras", gs);
        intentFilterActivity.putExtra("filtro", filtro);
        startActivityForResult(intentFilterActivity, SECOND_ACTIVITY_REQUEST_CODE);
    }

    // Despues de aceptar los filtros a aplicar de FilterActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            // Se ha aceptado una configuracion de filtro
            filtro = data.getExtras().getParcelable("filtro");
            // Se cargan otra vez los datos con el filtro seleccionado
            new CargaDatosGasolinerasTask(this).execute();

        }
    }

    /**
     * Se encarga de modificar el view correspondiente para que se muestre la lista de gasolineras
     * que cumpla con los filtros seleccionados.
     *
     * @param view
     */
    private void representarFiltros(View view) {
        presenterGasolineras.filtrarPorCombustible(view, filtro);
        ordenarPorPrecio();
    }

    private void ordenarPorPrecio() {
        String ordenarPorPrecio = filtro.getOrdenarPorPrecio();
        switch (ordenarPorPrecio) {
            case "":
                // No se ha especificado nada, entonces no se ordena de ninguna manera por precio
                break;
            case "MayorAMenor":
                ordenarPorPrecioMayorAMenor();
                break;
            case "MenorAMayor":
                ordenarPorPrecioMenorAMayor();
                break;

        }
    }

    private void ordenarPorPrecioMenorAMayor() {
        List<String> combustiblesAFiltar = filtro.getCombustibles();
        if (combustiblesAFiltar.contains(TODOS) || combustiblesAFiltar.contains(GASOLEO_A)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getGasoleoA(), g2.getGasoleoA());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLEO_B)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getGasoleoB(), g2.getGasoleoB());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLEO_PREMIUM)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getGasoleoPremium(), g2.getGasoleoPremium());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLINA_95_E10)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getGasolina95E10(), g2.getGasolina95E10());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLINA_95_E5)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getGasolina95E5(), g2.getGasolina95E5());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLINA_95_E5_PREMIUM)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getGasolina95E5Premium(), g2.getGasolina95E5Premium());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLINA_98_E10)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getGasolina98E10(), g2.getGasolina98E10());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLINA_98_E5)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getGasolina98E5(), g2.getGasolina98E5());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(BIODIESEL)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getBiodiesel(), g2.getBiodiesel());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(BIOETANOL)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getBioetanol(), g2.getBioetanol());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GAS_NATURAL_COMPRIMIDO)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getGasNaturalComprimido(), g2.getGasNaturalComprimido());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GAS_NATURAL_LICUADO)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getGasNaturalLicuado(), g2.getGasNaturalLicuado());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASES_LICUADOS_PETROLEO)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getGasesLicuadosPetroleo(), g2.getGasesLicuadosPetroleo());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(HIDROGENO)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g1.getHidrogeno(), g2.getHidrogeno());
                }
            });
            return;
        }
    }

    private void ordenarPorPrecioMayorAMenor() {
        List<String> combustiblesAFiltar = filtro.getCombustibles();
        if (combustiblesAFiltar.contains(TODOS) || combustiblesAFiltar.contains(GASOLEO_A)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getGasoleoA(), g1.getGasoleoA());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLEO_B)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getGasoleoB(), g1.getGasoleoB());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLEO_PREMIUM)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getGasoleoPremium(), g1.getGasoleoPremium());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLINA_95_E10)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getGasolina95E10(), g1.getGasolina95E10());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLINA_95_E5)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getGasolina95E5(), g1.getGasolina95E5());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLINA_95_E5_PREMIUM)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getGasolina95E5Premium(), g1.getGasolina95E5Premium());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLINA_98_E10)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getGasolina98E10(), g1.getGasolina98E10());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASOLINA_98_E5)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getGasolina98E5(), g1.getGasolina98E5());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(BIODIESEL)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getBiodiesel(), g1.getBiodiesel());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(BIOETANOL)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getBioetanol(), g1.getBioetanol());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GAS_NATURAL_COMPRIMIDO)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getGasNaturalComprimido(), g1.getGasNaturalComprimido());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GAS_NATURAL_LICUADO)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getGasNaturalLicuado(), g1.getGasNaturalLicuado());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(GASES_LICUADOS_PETROLEO)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getGasesLicuadosPetroleo(), g1.getGasesLicuadosPetroleo());
                }
            });
            return;
        }
        if (combustiblesAFiltar.contains(HIDROGENO)) {
            Collections.sort(presenterGasolineras.getGasolineras(), new Comparator<Gasolinera>() {
                @Override
                public int compare(Gasolinera g1, Gasolinera g2) {
                    return Double.compare(g2.getHidrogeno(), g1.getHidrogeno());
                }
            });
            return;
        }
    }

    /**
     * Menú action bar
     * <p>
     * Redefine métodos para el uso de un menú de tipo action bar.
     * <p>
     * onCreateOptionsMenu
     * Carga las opciones del menú a partir del fichero de recursos menu/menu.xml
     * <p>
     * onOptionsItemSelected
     * Define las respuestas a las distintas opciones del menú
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemActualizar) {
            mSwipeRefreshLayout.setRefreshing(true);
            new CargaDatosGasolinerasTask(this).execute();
        } else if (item.getItemId() == R.id.itemInfo) {
            Intent myIntent = new Intent(MainActivity.this, InfoActivity.class);
            MainActivity.this.startActivity(myIntent);
        }
        return true;
    }


    /**
     * CargaDatosGasolinerasTask
     * <p>
     * Tarea asincrona para obtener los datos de las gasolineras
     * en segundo plano.
     * <p>
     * Redefinimos varios métodos que se ejecutan en el siguiente orden:
     * onPreExecute: activamos el dialogo de progreso
     * doInBackground: solicitamos que el presenter cargue los datos
     * onPostExecute: desactiva el dialogo de progreso,
     * muestra las gasolineras en formato lista (a partir de un adapter)
     * y define la acción al realizar al seleccionar alguna de ellas
     * <p>
     * http://www.sgoliver.net/blog/tareas-en-segundo-plano-en-android-i-thread-y-asynctask/
     */
    private class CargaDatosGasolinerasTask extends AsyncTask<Void, Void, Boolean> {

        Activity activity;

        /**
         * Constructor de la tarea asincrona
         *
         * @param activity
         */
        public CargaDatosGasolinerasTask(Activity activity) {
            this.activity = activity;
        }

        /**
         * onPreExecute
         * <p>
         * Metodo ejecutado de forma previa a la ejecucion de la tarea definida en el metodo doInBackground()
         * Muestra un diálogo de progreso
         */
        @Deprecated
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);  //To show ProgressBar
        }

        /**
         * doInBackground
         * <p>
         * Tarea ejecutada en segundo plano
         * Llama al presenter para que lance el método de carga de los datos de las gasolineras
         *
         * @param params
         * @return boolean
         */
        @Override
        protected Boolean doInBackground(Void... params) {
            return presenterGasolineras.cargaDatosGasolineras();
        }

        /**
         * onPostExecute
         * <p>
         * Se ejecuta al finalizar doInBackground
         * Oculta el diálogo de progreso.
         * Muestra en una lista los datos de las gasolineras cargadas,
         * creando un adapter y pasándoselo a la lista.
         * Define el manejo de la selección de los elementos de la lista,
         * lanzando con una intent una actividad de detalle
         * a la que pasamos un objeto Gasolinera
         *
         * @param res
         */
        @Deprecated
        @Override
        protected void onPostExecute(Boolean res) {
            Toast toast;

            // Si el progressDialog estaba activado, lo oculta
            progressBar.setVisibility(View.GONE);     // To Hide ProgressBar

            mSwipeRefreshLayout.setRefreshing(false);

            // Si se ha obtenido resultado en la tarea en segundo plano
            if (Boolean.TRUE.equals(res)) {
                // Definimos el array adapter
                adapter = new GasolineraArrayAdapter(activity, 0, (ArrayList<Gasolinera>) presenterGasolineras.getGasolineras());

                // Obtenemos la vista de la lista
                listViewGasolineras = findViewById(R.id.listViewGasolineras);

                // Cargamos los datos en la lista
                if (!presenterGasolineras.getGasolineras().isEmpty()) {
                    // datos obtenidos con exito
                    listViewGasolineras.setAdapter(adapter);
                    toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_exito), Toast.LENGTH_LONG);
                } else {
                    // los datos estan siendo actualizados en el servidor, por lo que no son actualmente accesibles
                    // sucede en torno a las :00 y :30 de cada hora
                    toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_no_accesibles), Toast.LENGTH_LONG);
                }
            } else {
                // error en la obtencion de datos desde el servidor
                toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_no_obtenidos), Toast.LENGTH_LONG);
            }

            // Muestra el mensaje del resultado de la operación en un toast
            if (toast != null) {
                toast.show();
            }

            /*
             * Define el manejo de los eventos de click sobre elementos de la lista
             * En este caso, al pulsar un elemento se lanzará una actividad con una vista de detalle
             * a la que le pasamos el objeto Gasolinera sobre el que se pulsó, para que en el
             * destino tenga todos los datos que necesita para mostrar.
             * Para poder pasar un objeto Gasolinera mediante una intent con putExtra / getExtra,
             * hemos tenido que hacer que el objeto Gasolinera implemente la interfaz Parcelable
             */
            listViewGasolineras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                    /* Obtengo el elemento directamente de su posicion,
                     * ya que es la misma que ocupa en la lista
                     * Alternativa 1: a partir de posicion obtener algun atributo int opcionSeleccionada = ((Gasolinera) a.getItemAtPosition(position)).getIdeess();
                     * Alternativa 2: a partir de la vista obtener algun atributo String opcionSeleccionada = ((TextView)v.findViewById(R.id.textViewRotulo)).getText().toString();
                     */
                    Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
                    myIntent.putExtra(getResources().getString(R.string.pasoDatosGasolinera),
                            presenterGasolineras.getGasolineras().get(position));
                    MainActivity.this.startActivity(myIntent);

                }
            });
        }
    }


    /*
    ------------------------------------------------------------------
        GasolineraArrayAdapter

        Adaptador para inyectar los datos de las gasolineras
        en el listview del layout principal de la aplicacion
    ------------------------------------------------------------------
    */
    class GasolineraArrayAdapter extends ArrayAdapter<Gasolinera> {

        private Context context;
        private List<Gasolinera> listaGasolineras;

        // Constructor
        public GasolineraArrayAdapter(Context context, int resource, List<Gasolinera> objects) {
            super(context, resource, objects);
            this.context = context;
            this.listaGasolineras = objects;
        }

        // Llamado al renderizar la lista
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Indica el layout a usar en cada elemento de la lista
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_gasolinera, null);

            // Se modifica el view para que cumpla con los filtros seleccionados.
            presenterGasolineras.filtrarPorMarca(listaGasolineras, filtro);
            representarFiltros(view);

            // Obtiene el elemento que se está mostrando
            Log.d("position", ""+position);
            if(position>=listaGasolineras.size()){
                return view;
            }
            Gasolinera gasolinera = listaGasolineras.get(position);
            Log.d("Gasolinera", "" + gasolinera.getDireccion());

            // Asocia las variables de dicho layout
            ImageView logo = view.findViewById(R.id.imageViewLogo);
            TextView rotulo = view.findViewById(R.id.textViewRotulo);
            TextView direccion = view.findViewById(R.id.textViewDireccion);
            TextView gasoleoA = view.findViewById(R.id.textViewGasoleoA);
            TextView gasoleoB = view.findViewById(R.id.textViewGasoleoB);
            TextView gasoleoPremium = view.findViewById(R.id.textViewGasoleoPremium);
            TextView gasolina95E10 = view.findViewById(R.id.textViewGasolina95E10);
            TextView gasolina95E5 = view.findViewById(R.id.textViewGasolina95E5);
            TextView gasolina95E5Premium = view.findViewById(R.id.textViewGasolina95E5Premium);
            TextView gasolina98E10 = view.findViewById(R.id.textViewGasolina98E10);
            TextView gasolina98E5 = view.findViewById(R.id.textViewGasolina98E5);
            TextView biodiesel = view.findViewById(R.id.textViewBiodiesel);
            TextView bioetanol = view.findViewById(R.id.textViewBioetanol);
            TextView gasNaturalComprimido = view.findViewById(R.id.textViewGasNaturalComprimido);
            TextView gasNaturalLicuado = view.findViewById(R.id.textViewGasNaturalLicuado);
            TextView gasesLicuadosPetroleo = view.findViewById(R.id.textViewGasesLicuadosPetroleo);
            TextView hidrogeno = view.findViewById(R.id.textViewHidrogeno);

            // Y carga los datos del item
            rotulo.setText(gasolinera.getRotulo());
            direccion.setText(gasolinera.getDireccion());
            gasoleoA.setText(" " + gasolinera.getGasoleoA() + getResources().getString(R.string.moneda));
            gasoleoB.setText(" " + gasolinera.getGasoleoB() + getResources().getString(R.string.moneda));
            gasoleoPremium.setText(" " + gasolinera.getGasoleoPremium() + getResources().getString(R.string.moneda));
            gasolina95E10.setText(" " + gasolinera.getGasolina95E10() + getResources().getString(R.string.moneda));
            gasolina95E5.setText(" " + gasolinera.getGasolina95E5() + getResources().getString(R.string.moneda));
            gasolina95E5Premium.setText(" " + gasolinera.getGasolina95E5Premium() + getResources().getString(R.string.moneda));
            gasolina98E10.setText(" " + gasolinera.getGasolina98E10() + getResources().getString(R.string.moneda));
            gasolina98E5.setText(" " + gasolinera.getGasolina98E5() + getResources().getString(R.string.moneda));
            biodiesel.setText(" " + gasolinera.getBiodiesel() + getResources().getString(R.string.moneda));
            bioetanol.setText(" " + gasolinera.getBioetanol() + getResources().getString(R.string.moneda));
            gasNaturalComprimido.setText(" " + gasolinera.getGasNaturalComprimido() + getResources().getString(R.string.moneda));
            gasNaturalLicuado.setText(" " + gasolinera.getGasNaturalLicuado() + getResources().getString(R.string.moneda));
            gasesLicuadosPetroleo.setText(" " + gasolinera.getGasesLicuadosPetroleo() + getResources().getString(R.string.moneda));
            hidrogeno.setText(" " + gasolinera.getHidrogeno() + getResources().getString(R.string.moneda));


            // carga icono
            cargaIcono(logo, gasolinera);


            // Si las dimensiones de la pantalla son menores
            // reducimos el texto de las etiquetas para que se vea correctamente
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics.widthPixels < 720) {
                TextView tv = view.findViewById(R.id.textViewGasoleoALabel);
                RelativeLayout.LayoutParams params = ((RelativeLayout.LayoutParams) tv.getLayoutParams());
                params.setMargins(15, 0, 0, 0);
                tv.setTextSize(11);
                TextView tmp;
                tmp = view.findViewById(R.id.textViewGasolina95E5Label);
                tmp.setTextSize(11);
                tmp = view.findViewById(R.id.textViewGasoleoA);
                tmp.setTextSize(11);
                tmp = view.findViewById(R.id.textViewGasoleoB);
                tmp.setTextSize(11);
                tmp = view.findViewById(R.id.textViewGasoleoBLabel);
                tmp.setTextSize(11);
                tmp = view.findViewById(R.id.textViewGasolina95E5);
                tmp.setTextSize(11);
            }

            return view;
        }

        public void cargaIcono(ImageView logo, Gasolinera gasolinera) {
            String rotuleImageID = gasolinera.getRotulo().toLowerCase();

            // Tengo que protegerme ante el caso en el que el rotulo solo tiene digitos.
            // En ese caso getIdentifier devuelve esos digitos en vez de 0.
            int imageID = context.getResources().getIdentifier(rotuleImageID,
                    "drawable", context.getPackageName());

            if (imageID == 0 || TextUtils.isDigitsOnly(rotuleImageID)) {
                imageID = context.getResources().getIdentifier(getResources().getString(R.string.pordefecto),
                        "drawable", context.getPackageName());
            }
            logo.setImageResource(imageID);
        }
    }

}