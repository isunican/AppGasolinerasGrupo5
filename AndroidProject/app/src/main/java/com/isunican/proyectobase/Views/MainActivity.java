package com.isunican.proyectobase.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
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

import java.util.ArrayList;
import java.util.List;


/*
------------------------------------------------------------------
    Vista principal

    Presenta los datos de las gasolineras en formato lista.

------------------------------------------------------------------
*/
public class MainActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    PresenterGasolineras presenterGasolineras;

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
     * con cumpla con los filtros seleccionados.
     *
     * @param view
     */
    private void representarFiltros(View view) {
        View viewGasoilAPrecio = view.findViewById(R.id.textViewGasoleoA);
        View viewGasoilALabel = view.findViewById(R.id.textViewGasoleoALabel);
        View viewGasoilBPrecio = view.findViewById(R.id.textViewGasoleoB);
        View viewGasoilBLabel = view.findViewById(R.id.textViewGasoleoBLabel);
        View viewGasoilPremiumPrecio = view.findViewById(R.id.textViewGasoleoPremium);
        View viewGasoilPremiumLabel = view.findViewById(R.id.textViewGasoleoPremiumLabel);
        View viewGasolina95E10Precio = view.findViewById(R.id.textViewGasolina95E10);
        View viewGasolina95E10Label = view.findViewById(R.id.textViewGasolina95E10Label);
        View viewGasolina95E5Precio = view.findViewById(R.id.textViewGasolina95E5);
        View viewGasolina95E5Label = view.findViewById(R.id.textViewGasolina95E5Label);
        View viewGasolina95E5PremiumPrecio = view.findViewById(R.id.textViewGasolina95E5Premium);
        View viewGasolina95E5PremiumLabel = view.findViewById(R.id.textViewGasolina95E5PremiumLabel);
        View viewGasolina98E10Precio = view.findViewById(R.id.textViewGasolina98E10);
        View viewGasolina98E10Label = view.findViewById(R.id.textViewGasolina98E10Label);
        View viewGasolina98E5Precio = view.findViewById(R.id.textViewGasolina98E5);
        View viewGasolina98E5Label = view.findViewById(R.id.textViewGasolina98E5Label);
        View viewBiodieselPrecio = view.findViewById(R.id.textViewBiodiesel);
        View viewBiodieselLabel = view.findViewById(R.id.textViewBiodieselLabel);
        View viewBioetanolPrecio = view.findViewById(R.id.textViewBioetanol);
        View viewBioetanolLabel = view.findViewById(R.id.textViewBioetanolLabel);
        View viewGasNaturalComprimidoPrecio = view.findViewById(R.id.textViewGasNaturalComprimido);
        View viewGasNaturalComprimidoLabel = view.findViewById(R.id.textViewGasNaturalComprimidoLabel);
        View viewGasNaturalLicuadoPrecio = view.findViewById(R.id.textViewGasNaturalLicuado);
        View viewGasNaturalLicuadoLabel = view.findViewById(R.id.textViewGasNaturalLicuadoLabel);
        View viewGasesLicuadosPetroleoPrecio = view.findViewById(R.id.textViewGasesLicuadosPetroleo);
        View viewGasesLicuadosPetroleoLabel = view.findViewById(R.id.textViewGasesLicuadosPetroleoLabel);
        View viewHidrogenoPrecio = view.findViewById(R.id.textViewHidrogeno);
        View viewHidrogenoLabel = view.findViewById(R.id.textViewHidrogenoLabel);

        for (String combustible : filtro.getCombustibles()) {
            switch (combustible) {
                case "TODOS":
                    viewGasoilAPrecio.setVisibility(View.VISIBLE);
                    viewGasoilALabel.setVisibility(View.VISIBLE);
                    viewGasoilBPrecio.setVisibility(View.VISIBLE);
                    viewGasoilBLabel.setVisibility(View.VISIBLE);
                    viewGasoilPremiumPrecio.setVisibility(View.VISIBLE);
                    viewGasoilPremiumLabel.setVisibility(View.VISIBLE);
                    viewGasolina95E10Precio.setVisibility(View.VISIBLE);
                    viewGasolina95E10Label.setVisibility(View.VISIBLE);
                    viewGasolina95E5Precio.setVisibility(View.VISIBLE);
                    viewGasolina95E5Label.setVisibility(View.VISIBLE);
                    viewGasolina95E5PremiumPrecio.setVisibility(View.VISIBLE);
                    viewGasolina95E5PremiumLabel.setVisibility(View.VISIBLE);
                    viewGasolina98E10Precio.setVisibility(View.VISIBLE);
                    viewGasolina98E10Label.setVisibility(View.VISIBLE);
                    viewGasolina98E5Precio.setVisibility(View.VISIBLE);
                    viewGasolina98E5Label.setVisibility(View.VISIBLE);
                    viewBiodieselPrecio.setVisibility(View.VISIBLE);
                    viewBiodieselLabel.setVisibility(View.VISIBLE);
                    viewBioetanolPrecio.setVisibility(View.VISIBLE);
                    viewBioetanolLabel.setVisibility(View.VISIBLE);
                    viewGasNaturalComprimidoPrecio.setVisibility(View.VISIBLE);
                    viewGasNaturalComprimidoLabel.setVisibility(View.VISIBLE);
                    viewGasNaturalLicuadoPrecio.setVisibility(View.VISIBLE);
                    viewGasNaturalLicuadoLabel.setVisibility(View.VISIBLE);
                    viewGasesLicuadosPetroleoPrecio.setVisibility(View.VISIBLE);
                    viewGasesLicuadosPetroleoLabel.setVisibility(View.VISIBLE);
                    viewHidrogenoPrecio.setVisibility(View.VISIBLE);
                    viewHidrogenoLabel.setVisibility(View.VISIBLE);
                    break;
                case "GASOLEO A":
                    viewGasoilAPrecio.setVisibility(View.VISIBLE);
                    viewGasoilALabel.setVisibility(View.VISIBLE);
                    break;
                case "GASOLEO B":
                    viewGasoilBPrecio.setVisibility(View.VISIBLE);
                    viewGasoilBLabel.setVisibility(View.VISIBLE);
                    break;
                case "GASOLEO PREMIUM":
                    viewGasoilPremiumPrecio.setVisibility(View.VISIBLE);
                    viewGasoilPremiumLabel.setVisibility(View.VISIBLE);
                    break;
                case "GASOLINA 95 E10":
                    viewGasolina95E10Precio.setVisibility(View.VISIBLE);
                    viewGasolina95E10Label.setVisibility(View.VISIBLE);
                    break;
                case "GASOLINA 95 E5":
                    viewGasolina95E5Precio.setVisibility(View.VISIBLE);
                    viewGasolina95E5Label.setVisibility(View.VISIBLE);
                    break;
                case "GASOLINA 95 E5 PREMIUM":
                    viewGasolina95E5PremiumPrecio.setVisibility(View.VISIBLE);
                    viewGasolina95E5PremiumLabel.setVisibility(View.VISIBLE);
                    break;
                case "GASOLINA 98 E10":
                    viewGasolina98E10Precio.setVisibility(View.VISIBLE);
                    viewGasolina98E10Label.setVisibility(View.VISIBLE);
                    break;
                case "GASOLINA 98 E5":
                    viewGasolina98E5Precio.setVisibility(View.VISIBLE);
                    viewGasolina98E5Label.setVisibility(View.VISIBLE);
                    break;
                case "BIODISEL":
                    viewBiodieselPrecio.setVisibility(View.VISIBLE);
                    viewBiodieselLabel.setVisibility(View.VISIBLE);
                    break;
                case "BIOETANOL":
                    viewBioetanolPrecio.setVisibility(View.VISIBLE);
                    viewBioetanolLabel.setVisibility(View.VISIBLE);
                    break;
                case "GAS NATURAL COMPRIMIDO":
                    viewGasNaturalComprimidoPrecio.setVisibility(View.VISIBLE);
                    viewGasNaturalComprimidoLabel.setVisibility(View.VISIBLE);
                    break;
                case "GAS NATURAL LICUADO":
                    viewGasNaturalLicuadoPrecio.setVisibility(View.VISIBLE);
                    viewGasNaturalLicuadoLabel.setVisibility(View.VISIBLE);
                    break;
                case "GASES LICUADOS PETROLEO":
                    viewGasesLicuadosPetroleoPrecio.setVisibility(View.VISIBLE);
                    viewGasesLicuadosPetroleoLabel.setVisibility(View.VISIBLE);
                    break;
                case "HIDROGENO":
                    viewHidrogenoPrecio.setVisibility(View.VISIBLE);
                    viewHidrogenoLabel.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
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

            // Obtiene el elemento que se está mostrando
            Gasolinera gasolinera = listaGasolineras.get(position);

            // Indica el layout a usar en cada elemento de la lista
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_gasolinera, null);

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


            // se modifica el view para que cumpla con los filtros seleccionados.
            representarFiltros(view);

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