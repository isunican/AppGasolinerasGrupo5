package com.isunican.proyectobase.Model;

import android.os.Parcel;
import android.os.Parcelable;


/*
------------------------------------------------------------------
    Clase que almacena la informacion de una gasolinera
    Implementa la interfaz Parceable, que permite que luego podamos
    pasar objetos de este tipo entre activities a traves de una llamada intent
------------------------------------------------------------------
*/

public class Gasolinera implements Parcelable {
    private int ideess;
    private String localidad;
    private String provincia;
    private String direccion;
    private double biodiesel;
    private double bioetanol;
    private double gasNaturalComprimido;
    private double gasNaturalLicuado;
    private double gasesLicuadosPetroleo;
    private double gasoleoA;
    private double gasoleoB;
    private double gasoleoPremium;
    private double gasolina95E10;
    private double gasolina95E5;
    private double gasolina95E5Premium;
    private double gasolina98E10;
    private double gasolina98E5;
    private double hidrogeno;
    private String rotulo;

    private boolean checked;

    /**
     * Constructor, getters y setters
     */
    public Gasolinera (int ideess){
        this.ideess = ideess;
    }


    public int getIdeess() { return ideess; }
    public void setIdeess(int ideess) { this.ideess = ideess; }

    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double getGasoleoA() { return gasoleoA; }
    public void setGasoleoA(double gasoleoA) { this.gasoleoA = gasoleoA; }

    public String getRotulo() { return rotulo; }
    public void setRotulo(String rotulo) { this.rotulo = rotulo; }

    public double getGasolina95E5() { return gasolina95E5; }
    public void setGasolina95E5(double gasolina95E5) { this.gasolina95E5 = gasolina95E5; }

    public double getBiodiesel(){ return biodiesel; }
    public void setBiodiesel(double biodiesel) { this.biodiesel = biodiesel; }

    public double getBioetanol(){ return bioetanol; }
    public void setBioetanol(double bioetanol) { this.bioetanol = bioetanol; }

    public double getGasesLicuadosPetroleo() { return gasesLicuadosPetroleo; }
    public void setGasesLicuadosPetroleo(double gasesLicuadosPetroleo) { this.gasesLicuadosPetroleo = gasesLicuadosPetroleo; }

    public double getGasNaturalComprimido() { return gasNaturalComprimido; }
    public void setGasNaturalComprimido(double gasNaturalComprimido) { this.gasNaturalComprimido = gasNaturalComprimido; }

    public double getGasNaturalLicuado() { return gasNaturalLicuado; }
    public void setGasNaturalLicuado(double gasNaturalLicuado) { this.gasNaturalLicuado = gasNaturalLicuado; }

    public double getGasoleoB() { return gasoleoB; }
    public void setGasoleoB(double gasoleoB) { this.gasoleoB = gasoleoB; }

    public double getGasoleoPremium() { return gasoleoPremium; }
    public void setGasoleoPremium(double gasoleoPremium) { this.gasoleoPremium = gasoleoPremium; }

    public double getGasolina95E5Premium() { return gasolina95E5Premium; }
    public void setGasolina95E5Premium(double gasolina95E5Premium) { this.gasolina95E5Premium = gasolina95E5Premium; }

    public double getGasolina95E10() { return gasolina95E10; }
    public void setGasolina95E10(double gasolina95E10) { this.gasolina95E10 = gasolina95E10; }

    public double getGasolina98E5() { return gasolina98E5; }
    public void setGasolina98E5(double gasolina98E5) { this.gasolina98E5 = gasolina98E5; }

    public double getGasolina98E10() { return gasolina98E10; }
    public void setGasolina98E10(double gasolina98E10) { this.gasolina98E10 = gasolina98E10; }

    public double getHidrogeno() { return hidrogeno; }
    public void setHidrogeno(double hidrogeno) { this.hidrogeno = hidrogeno; }

    public boolean getChecked(){ return checked; }
    public void setChecked(boolean checked){ this.checked=checked; }

    /**
     * toString
     *
     * Redefine el método toString para obtener los datos
     * de una Gasolinera en formato texto
     *
     * @param
     * @return String
     */
    @Override
    public String toString(){
        String textoGasolineras = "";
        textoGasolineras +=
                getRotulo() + "\n"+
                getDireccion() + "\n" +
                getLocalidad() + "\n" +
                "Precio Gasoleo A: " + getGasoleoA() + " " + "\n" +
                "Precio Gasolina 95 E5: " + getGasolina95E5() + " " + "\n" +
                        "Precio Biodiesel: " + getBiodiesel() + " " + "\n" +
                        "Precio Bioetanol: " + getBioetanol() + " " + "\n" +
                        "Precio Gases licuados petróleo: " + getGasesLicuadosPetroleo() + " " + "\n" +
                        "Precio Gas Natural Comprimido: " + getGasNaturalComprimido() + " " + "\n" +
                        "Precio Gas Natural Licuado: " + getGasNaturalLicuado() + " " + "\n" +
                        "Precio Gasoleo B: " + getGasoleoB() + " " + "\n" +
                        "Precio Gasoleo Premium: " + getGasoleoPremium() + " " + "\n" +
                        "Precio Gasolina 95 E5 Premium: " + getGasolina95E5Premium() + " " + "\n" +
                        "Precio Gasolina 95 E10: " + getGasolina95E10() + " " + "\n" +
                        "Precio Gasolina 98 E5: " + getGasolina98E5() + " " + "\n" +
                        "Precio Gasolina 98 E10: " + getGasolina98E10() + " " + "\n" +
                        "Precio Hidrogeno: " + getHidrogeno() + " " + "\n\n";

        return textoGasolineras;
    }


    /**
     * interfaz Parcelable
     *
     * Métodos necesarios para implementar la interfaz Parcelable
     * que nos permitirá pasar objetos del tipo Gasolinera
     * directamente entre actividades utilizando intents
     * Se enviarían utilizando putExtra
     * myIntent.putExtra("id", objeto gasolinera);
     * y recibiéndolos con
     * Gasolinera g = getIntent().getExtras().getParcelable("id")
     */
    protected Gasolinera(Parcel in) {
        ideess = in.readInt();
        localidad = in.readString();
        provincia = in.readString();
        direccion = in.readString();
        biodiesel = in.readDouble();
        bioetanol = in.readDouble();
        gasNaturalComprimido = in.readDouble();
        gasNaturalLicuado = in.readDouble();
        gasesLicuadosPetroleo = in.readDouble();
        gasoleoA = in.readDouble();
        gasoleoB = in.readDouble();
        gasoleoPremium = in.readDouble();
        gasolina95E10 = in.readDouble();
        gasolina95E5 = in.readDouble();
        gasolina95E5Premium = in.readDouble();
        gasolina98E10 = in.readDouble();
        gasolina98E5 = in.readDouble();
        hidrogeno = in.readDouble();
        rotulo = in.readString();
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ideess);
        dest.writeString(localidad);
        dest.writeString(provincia);
        dest.writeString(direccion);
        dest.writeDouble(biodiesel);
        dest.writeDouble(bioetanol);
        dest.writeDouble(gasNaturalComprimido);
        dest.writeDouble(gasNaturalLicuado);
        dest.writeDouble(gasesLicuadosPetroleo);
        dest.writeDouble(gasoleoA);
        dest.writeDouble(gasoleoB);
        dest.writeDouble(gasoleoPremium);
        dest.writeDouble(gasolina95E10);
        dest.writeDouble(gasolina95E5);
        dest.writeDouble(gasolina95E5Premium);
        dest.writeDouble(gasolina98E10);
        dest.writeDouble(gasolina98E5);
        dest.writeDouble(hidrogeno);
        dest.writeString(rotulo);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Gasolinera> CREATOR = new Parcelable.Creator<Gasolinera>() {
        @Override
        public Gasolinera createFromParcel(Parcel in) {
            return new Gasolinera(in);
        }

        @Override
        public Gasolinera[] newArray(int size) {
            return new Gasolinera[size];
        }
    };
}