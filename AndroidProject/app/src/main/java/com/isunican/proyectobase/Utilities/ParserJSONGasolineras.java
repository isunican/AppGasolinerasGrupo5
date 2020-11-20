package com.isunican.proyectobase.Utilities;

import com.isunican.proyectobase.Model.*;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/*
------------------------------------------------------------------
    Clase con los metodos necesarios para
    parsear un stream de datos en formato JSON
    en este caso, un stream con gasolineras
------------------------------------------------------------------
*/

public class ParserJSONGasolineras {

    private ParserJSONGasolineras(){}

    /**
     * parseaArrayGasolineras
     *
     * Se le pasa un stream de datos en formato JSON que contiene gasolineras.
     * Crea un JsonReader para el stream de datos y llama a un método auxiliar que lo analiza
     * y extrae una lista de objetos Gasolinera que es la que se devuelve
     *
     * @param in Inputsream Stream de datos JSON
     * @return List<Gasolinera> Lista de objetos Gasolinera con los datos obtenidas tras parsear el JSON
     * @throws IOException
     */
    public static List<Gasolinera> parseaArrayGasolineras (InputStream in) throws IOException {

        try (JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"))){
            return readArrayGasolineras(reader);
        } catch (IOException e){
            return Collections.emptyList();
        }
    }

    /**
     * readArrayGasolineras
     *
     * Se le pasa un objeto JsonReader con el stream de datos JSON a analizar.
     * Crea una lista de gasolineras.
     * Va leyendo elementos hasta encontrar la cabecera "ListaEESSPrecio"
     * ya que de ella cuelga el array de gasolineras.
     * Mientras haya elementos los analiza con un método auxiliar
     * que analiza los datos de una gasolinera concreta
     * y devuelve un objeto Gasolinera que añadimos a la lista de gasolineras.
     * Finalmente se devuelve la lista de gasolineras.
     *
     * @param //in JsonReader Stream de datos JSON apuntando al comienzo del stream
     * @return List Lista de objetos Gasolinera con los datos obtenidas tras parsear el JSON
     * @throws IOException
     */
    public static List<Gasolinera> readArrayGasolineras (JsonReader reader) throws IOException {
        List<Gasolinera> listGasolineras = new ArrayList<>();

        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            Log.d("ENTRA", "Nombre del elemento: "+name);
            if(name.equals("ListaEESSPrecio")){
                reader.beginArray();
                while (reader.hasNext()){
                    listGasolineras.add(readGasolinera(reader));
                }
                reader.endArray();
            }else{
                reader.skipValue();
            }
        }
        reader.endObject();
        return listGasolineras;
    }

    /**
     * readGasolinera
     *
     * Se le pasa un objeto JsonReader con el stream de datos JSON a analizar
     * que está apuntando a una gasolinera concreta.
     * Va procesando este stream buscando las etiquetas de cada elemento
     * que se desea extraer, como "Rótulo" o "Localidad"
     * y almacena la cadena de su valor en un atributo del tipo adecuado,
     * parseándolo a entero, doble, etc. si es necesario.
     * Una vez extraidos todos los atributos, crea un objeto Gasolinera con ellos
     * y lo devuelve.
     *
     * @param //in JsonReader stream de datos JSON
     * @return Gasolinera Objetos Gasolinera con los datos obtenidas tras parsear el JSON
     * @throws IOException
     */
    public static Gasolinera readGasolinera (JsonReader reader) throws IOException {
        reader.beginObject();
        String rotulo = "";
        String localidad = "";
        String provincia = "";
        String direccion = "";
        int id = -1;
        double gasoleoA = 0.0;
        double gasolina95E5 = 0.0;
        double biodiesel = 0.0;
        double bioetanol = 0.0;
        double gasNaturalComprimido = 0.0;
        double gasNaturalLicuado = 0.0;
        double gasesLicuadosPetroleo = 0.0;
        double gasoleoB = 0.0;
        double gasoleoPremium = 0.0;
        double gasolina95E10 = 0.0;
        double gasolina95E5Premium = 0.0;
        double gasolina98E10 = 0.0;
        double gasolina98E5 = 0.0;
        double hidrogeno = 0.0;

        while(reader.hasNext()){
            String name = reader.nextName();

            if (name.equals("Rótulo") && reader.peek() != JsonToken.NULL) {
                rotulo = reader.nextString();
            }else if (name.equals("Localidad")) {
                localidad = reader.nextString();
            }else if(name.equals("Provincia")){
                provincia = reader.nextString();
            }else if(name.equals("IDEESS")){
                id = reader.nextInt();
            }else if(name.equals("Precio Gasoleo A") && reader.peek() != JsonToken.NULL) {
                gasoleoA = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Gasolina 95 E5") && reader.peek() != JsonToken.NULL) {
                gasolina95E5 = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Dirección")) {
                direccion = reader.nextString();
            }else if(name.equals("Precio Biodiesel")){
                biodiesel = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Bioetanol")){
                bioetanol = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Gas Natural Comprimido")){
                gasNaturalComprimido = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Gas Natural Licuado")){
                gasNaturalLicuado = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Gases licuados del petróleo")){
                gasesLicuadosPetroleo = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Gasoleo B")){
                gasoleoB = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Gasoleo Premium")){
                gasoleoPremium = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Gasolina 95 E10")){
                gasolina95E10 = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Gasolina 95 E5")){
                gasolina95E5 = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Gasolina 98 E10")){
                gasolina98E10 = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Gasolina 98 E5")){
                gasolina98E5 = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Hidrogeno")){
                hidrogeno = parseDouble(reader.nextString().replace(",", "."));
            }else if(name.equals("Precio Gasolina 95 E5 Premium")){
                gasolina95E5Premium = parseDouble(reader.nextString().replace(",", "."));
            }else{
                reader.skipValue();
            }

        }
        reader.endObject();
        Gasolinera g = new Gasolinera(id);

        g.setLocalidad(localidad);
        g.setProvincia(provincia);
        g.setDireccion(direccion);
        g.setBiodiesel(biodiesel);
        g.setBioetanol(bioetanol);
        g.setGasNaturalComprimido(gasNaturalComprimido);
        g.setGasNaturalLicuado(gasNaturalLicuado);
        g.setGasesLicuadosPetroleo(gasesLicuadosPetroleo);
        g.setGasoleoA(gasoleoA);
        g.setGasoleoB(gasoleoB);
        g.setGasoleoPremium(gasoleoPremium);
        g.setGasolina95E10(gasolina95E10);
        g.setGasolina95E5(gasolina95E5);
        g.setGasolina95E5Premium(gasolina95E5Premium);
        g.setGasolina98E10(gasolina98E10);
        g.setGasolina98E5(gasolina98E5);
        g.setHidrogeno(hidrogeno);
        g.setRotulo(rotulo);
        return g;
    }

    private static double parseDouble(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        } else {
            return Double.parseDouble(str.replace(",", "."));
        }
    }
}