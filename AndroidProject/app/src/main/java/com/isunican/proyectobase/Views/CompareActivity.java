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

import com.isunican.proyectobase.Model.Gasolinera;
import com.isunican.proyectobase.R;
import com.isunican.proyectobase.Views.MainActivity.GasolineraArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class CompareActivity extends AppCompatActivity {

    ListView listViewComparadas;
    ArrayList<Gasolinera> gasolinerasSeleccionadas;
    GasolineraArrayAdapter adapter;
    Toast toast;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        // Muestra el logo en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.por_defecto_mod);


        listViewComparadas = findViewById(R.id.listViewComparadas);
        gasolinerasSeleccionadas = (ArrayList<Gasolinera>) getIntent().getSerializableExtra("list_gasolineras_seleccionadas");

        adapter = new GasolineraArrayAdapter(this,0,gasolinerasSeleccionadas);

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
        private LayoutInflater inflater;

        // Constructor
        public GasolineraArrayAdapter(Context context, int resource, List<Gasolinera> objects) {
            super(context, resource, objects);
            this.context = context;
            this.listaGasolineras = objects;
        }

        // Llamado al renderizar la lista
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // Indica el layout a usar en cada elemento de la lista
            inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_gasolinera, null);
            View view = convertView;

            // Obtiene el elemento que se está mostrando
            Log.d("position", ""+position);
            if(position>=listaGasolineras.size()){
                return view;
            }
            Gasolinera gasolinera = listaGasolineras.get(position);

            Log.d("Gasolinera", "" + gasolinera.getDireccion());

            // Asocia las variables de dicho layout
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
            CheckBox checkGasolinera = view.findViewById(R.id.checkGasolinera);
            checkGasolinera.setVisibility(View.INVISIBLE);

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

            // Si las dimensiones de la pantalla son menores
            // reducimos el texto de las etiquetas para que se vea correctamente
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics.widthPixels < 720) {
                TextView tv = view.findViewById(R.id.textViewGasoleoALabel);
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
    }
}
