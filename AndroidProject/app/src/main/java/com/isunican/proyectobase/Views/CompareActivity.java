package com.isunican.proyectobase.Views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.isunican.proyectobase.DataBase.Filtro;
import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.Presenter.PresenterGasolineras;
import com.isunican.proyectobase.R;
import com.isunican.proyectobase.Views.MainActivity.GasolineraArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class CompareActivity extends AppCompatActivity {

    ListView listViewComparadas;
    ArrayList<Gasolinera> gasolinerasSeleccionadas;
    GasolineraComparacionArrayAdapter adapter;
    Toast toast;
    Button btnVolver;
    PresenterGasolineras presenterGasolineras;
    Filtro filtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        presenterGasolineras = new PresenterGasolineras();

        filtro = getIntent().getExtras().getParcelable("filtro");

        // Muestra el logo en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.por_defecto_mod);


        listViewComparadas = findViewById(R.id.listViewComparadas);
        gasolinerasSeleccionadas = (ArrayList<Gasolinera>) getIntent().getSerializableExtra("list_gasolineras_seleccionadas");

        adapter = new GasolineraComparacionArrayAdapter(this, 0, gasolinerasSeleccionadas);

        if (!gasolinerasSeleccionadas.isEmpty()) {
            // se han seleccionado gasolineras para ser comparadas
            listViewComparadas.setAdapter(adapter);
        } else {
            // No se han seleccionado gasolineras para comparar
            toast = Toast.makeText(getApplicationContext(), "No se han seleccionado gasolineras para comparar", Toast.LENGTH_LONG);
            toast.show();
        }

        btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * Se encarga de modificar el view correspondiente para que se muestre la lista de gasolineras
     * que cumpla con los filtros seleccionados.
     *
     * @param view
     */
    private void representarFiltros(View view) {
        Log.d("Compare", "view: "+view.toString()+" Filtro: "+filtro.getCombustibles().get(0));
        presenterGasolineras.filtrarPorCombustible(view, filtro);

    }

    /*
    ------------------------------------------------------------------
        GasolineraArrayAdapter

        Adaptador para inyectar los datos de las gasolineras
        en el listview del layout principal de la aplicacion
    ------------------------------------------------------------------
    */
    class GasolineraComparacionArrayAdapter extends ArrayAdapter<Gasolinera> {

        private Context context;
        private List<Gasolinera> listaGasolineras;
        private LayoutInflater inflater;

        // Constructor
        public GasolineraComparacionArrayAdapter(Context context, int resource, List<Gasolinera> objects) {
            super(context, resource, objects);
            this.context = context;
            this.listaGasolineras = objects;
        }

        // Llamado al renderizar la lista
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // Indica el layout a usar en cada elemento de la lista
            inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_compare_gasolinera, null);

            // Se modifica el view para que cumpla con los filtros seleccionados.
            representarFiltros(view);

            Gasolinera gasolinera = listaGasolineras.get(position);

            Log.d("Gasolinera", "" + gasolinera.getDireccion());

            // Asocia las variables de dicho layout
            ImageView logo = view.findViewById(R.id.imagenLogoCompare);
            TextView rotulo = view.findViewById(R.id.textViewRotuloCompare2);
            TextView direccion = view.findViewById(R.id.textViewDireccionCompare2);
            TextView localidad = view.findViewById(R.id.textViewLocalidaCompare2);
            TextView provincia = view.findViewById(R.id.textViewProvinciaCompare2);
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
            localidad.setText(gasolinera.getLocalidad());
            provincia.setText(gasolinera.getProvincia());
            gasoleoA.setText(" " + gasolinera.getGasoleoA() + getResources().getString(R.string.moneda) + "  ");
            gasoleoB.setText(" " + gasolinera.getGasoleoB() + getResources().getString(R.string.moneda) + "  ");
            gasoleoPremium.setText(" " + gasolinera.getGasoleoPremium() + getResources().getString(R.string.moneda) + "  ");
            gasolina95E10.setText(" " + gasolinera.getGasolina95E10() + getResources().getString(R.string.moneda) + "  ");
            gasolina95E5.setText(" " + gasolinera.getGasolina95E5() + getResources().getString(R.string.moneda) + "  ");
            gasolina95E5Premium.setText(" " + gasolinera.getGasolina95E5Premium() + getResources().getString(R.string.moneda) + "  ");
            gasolina98E10.setText(" " + gasolinera.getGasolina98E10() + getResources().getString(R.string.moneda) + "  ");
            gasolina98E5.setText(" " + gasolinera.getGasolina98E5() + getResources().getString(R.string.moneda) + "  ");
            biodiesel.setText(" " + gasolinera.getBiodiesel() + getResources().getString(R.string.moneda) + "  ");
            bioetanol.setText(" " + gasolinera.getBioetanol() + getResources().getString(R.string.moneda) + "  ");
            gasNaturalComprimido.setText(" " + gasolinera.getGasNaturalComprimido() + getResources().getString(R.string.moneda) + "  ");
            gasNaturalLicuado.setText(" " + gasolinera.getGasNaturalLicuado() + getResources().getString(R.string.moneda) + "  ");
            gasesLicuadosPetroleo.setText(" " + gasolinera.getGasesLicuadosPetroleo() + getResources().getString(R.string.moneda) + "  ");
            hidrogeno.setText(" " + gasolinera.getHidrogeno() + getResources().getString(R.string.moneda) + "  ");

            // carga icono
            cargaIcono(logo, gasolinera);

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
