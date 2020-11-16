package com.isunican.proyectobase.Presenter;

import android.util.Log;
import android.view.View;

import com.isunican.proyectobase.DataBase.Filtro;
import com.isunican.proyectobase.Model.*;
import com.isunican.proyectobase.R;
import com.isunican.proyectobase.Utilities.ParserJSONGasolineras;
import com.isunican.proyectobase.Utilities.RemoteFetch;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;

/*
------------------------------------------------------------------
    Clase presenter con la logica de gasolineras
    Mantiene un objeto ListaGasolineras que es el que mantendrá
    los datos de las gasolineras cargadas en nuestra aplicación
    Incluye métodos para gestionar la lista de gasolineras y
    cargar datos en ella.
------------------------------------------------------------------
*/
public class PresenterGasolineras {

    private List<Gasolinera> gasolineras;

    //URLs para obtener datos de las gasolineras
    //https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/help
    public static final String URL_GASOLINERAS_SPAIN="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/";
    public static final String URL_GASOLINERAS_CANTABRIA="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroCCAA/06";
    public static final String URL_GASOLINERAS_SANTANDER="https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/FiltroMunicipio/5819";
    public static final String SANTANDER="Santander";

    /**
     * Constructor, getters y setters
     */
    public PresenterGasolineras(){
        gasolineras = new ArrayList<>();
    }

    public List<Gasolinera> getGasolineras(){
        return gasolineras;
    }

    public void setGasolineras(List<Gasolinera> l) {
        this.gasolineras = l;
    }


    /**
     * cargaDatosGasolineras
     *
     * Carga los datos de las gasolineras en la lista de gasolineras de la clase.
     * Para ello llama a métodos de carga de datos internos de la clase ListaGasolineras.
     * En este caso realiza una carga de datos remotos dada una URL
     *
     * Habría que mejorar el método para que permita pasar un parámetro
     * con los datos a cargar (id de la ciudad, comunidad autónoma, etc.)
     *
     * @param
     * @return boolean Devuelve true si se han podido cargar los datos
     */
    public boolean cargaDatosGasolineras() {
        return cargaDatosRemotos(URL_GASOLINERAS_CANTABRIA);
    }

    /**
     * cargaDatosDummy
     *
     * Carga en la lista de gasolineras varias gasolineras definidas a "mano"
     * para hacer pruebas de funcionamiento
     *
     * @param
     * @return boolean
     */
    /**
    public boolean cargaDatosDummy(){
        this.gasolineras.add(new Gasolinera(1000,SANTANDER,SANTANDER, "Av Valdecilla", 1.299,1.359,"AVIA"));
        this.gasolineras.add(new Gasolinera(1053,SANTANDER,SANTANDER, "Plaza Matias Montero", 1.270,1.349,"CAMPSA"));
        this.gasolineras.add(new Gasolinera(420,SANTANDER,SANTANDER, "Area Arrabal Puerto de Raos", 1.249,1.279,"E.E.S.S. MAS, S.L."));
        this.gasolineras.add(new Gasolinera(9564,SANTANDER,SANTANDER, "Av Parayas", 1.189,1.269,"EASYGAS"));
        this.gasolineras.add(new Gasolinera(1025,SANTANDER,SANTANDER, "Calle el Empalme", 1.259,1.319,"CARREFOUR"));
        return true;
    }**/

    /**
     * cargaDatosLocales
     *
     * A partir de la dirección de un fichero JSON pasado como parámetro:
     * Parsea la información para obtener una lista de gasolineras.
     * Finalmente, dicha lista queda almacenada en la clase.
     *
     * @param String Nombre del fichero
     * @return boolean Devuelve true si se han podido cargar los datos
     */
    public boolean cargaDatosLocales(String fichero){
        return(fichero != null);
    }

    /**
     * cargaDatosRemotos
     *
     * A partir de la dirección pasada como parámetro:
     * Utiliza RemoteFetch para cargar el fichero JSON ubicado en dicha URL
     * en un stream de datos.
     * Luego utiliza ParserJSONGasolineras para parsear dicho stream
     * y extraer una lista de gasolineras.
     * Finalmente, dicha lista queda almacenada en la clase.
     *
     * @param String Dirección URL del JSON con los datos
     * @return boolean Devuelve true si se han podido cargar los datos
     */
    public boolean cargaDatosRemotos(String direccion){
        try {
            BufferedInputStream buffer = RemoteFetch.cargaBufferDesdeURL(direccion);
            gasolineras = ParserJSONGasolineras.parseaArrayGasolineras(buffer);
            Log.d("ENTRA", "Obten gasolineras:" + gasolineras.size());
            return true;
        } catch (Exception e) {
            Log.e("ERROR", "Error en la obtención de gasolineras: " + e.getMessage());
            return false;
        }
    }

    public void filtrarPorCombustible(View view, Filtro filtro) {
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
}
