package com.isunican.proyectobase.Views;

import com.isunican.proyectobase.R;
import com.isunican.proyectobase.Model.*;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;



/*
------------------------------------------------------------------
    Vista de detalle

    Presenta datos de detalle de una Gasolinera concreta.
    La gasolinera a mostrar se le pasa directamente a la actividad
    en la llamada por intent (usando putExtra / getExtra)
    Para ello Gasolinera implementa la interfaz Parcelable
------------------------------------------------------------------
*/
public class DetailActivity extends AppCompatActivity {

    TextView rotulo;
    TextView direccion;
    TextView localidad;
    TextView precioGasoil;
    TextView precioGasolina;

    Button btnFavoritos;
    Button btnMapa;

    Gasolinera g;

    /**
     * onCreate
     *
     * Crea los elementos que conforman la actividad
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // muestra el logo en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.por_defecto_mod);

        // captura el TextView
        // obtiene el objeto Gasolinera a mostrar
        // y lo introduce en el TextView convertido a cadena de texto
        rotulo = findViewById(R.id.textView2);
        direccion = findViewById(R.id.textView4);
        localidad = findViewById(R.id.textView6);
        precioGasolina = findViewById(R.id.textView8);
        precioGasoil = findViewById(R.id.textView10);

        g = getIntent().getExtras().getParcelable(getResources().getString(R.string.pasoDatosGasolinera));
        //CAMPOS DE LA TABLA
        rotulo.setText(g.getRotulo());
        direccion.setText(g.getDireccion());
        localidad.setText(g.getLocalidad());
        precioGasolina.setText(Double.toString(g.getGasolina95()));
        precioGasoil.setText(Double.toString(g.getGasoleoA()));

        btnFavoritos = findViewById(R.id.btnFavoritos);
        btnMapa = findViewById(R.id.btnMapa);
        //AQUI LES ASIGNARIAMOS UN ACTION LISTENER


    }
}