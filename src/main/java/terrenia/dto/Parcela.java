package terrenia.dto;

public class Parcela {
    private int id_parcela;
    private int id_terreno;
    private double tamaño;
    private String limites;
    private String ubicacion;
    private Boolean alquilada;

    public Parcela(int id_parcela, int id_terreno, double tamaño, String limites, String ubicacion, Boolean alquilada) {
        this.id_parcela = id_parcela;
        this.id_terreno = id_terreno;
        this.tamaño = tamaño;
        this.limites = limites;
        this.ubicacion = ubicacion;
        this.alquilada = alquilada;
    }

    public int getId_parcela() {
        return this.id_parcela;
    }

    public void setId_parcela(int id_parcela) {
        this.id_parcela = id_parcela;
    }

    public int getId_terreno() {
        return this.id_terreno;
    }

    public void setId_terreno(int id_terreno) {
        this.id_terreno = id_terreno;
    }

    public double getTamaño() {
        return this.tamaño;
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }

    public String getLimites() {
        return this.limites;
    }

    public void setLimites(String limites) {
        this.limites = limites;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Boolean isAlquilada() {
        return this.alquilada;
    }

    public Boolean getAlquilada() {
        return this.alquilada;
    }

    public void setAlquilada(Boolean alquilada) {
        this.alquilada = alquilada;
    }

    public Parcela id_parcela(int id_parcela) {
        setId_parcela(id_parcela);
        return this;
    }

    public Parcela id_terreno(int id_terreno) {
        setId_terreno(id_terreno);
        return this;
    }

    public Parcela tamaño(double tamaño) {
        setTamaño(tamaño);
        return this;
    }

    public Parcela limites(String limites) {
        setLimites(limites);
        return this;
    }

    public Parcela ubicacion(String ubicacion) {
        setUbicacion(ubicacion);
        return this;
    }

    public Parcela alquilada(Boolean alquilada) {
        setAlquilada(alquilada);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id_parcela='" + getId_parcela() + "'" +
            ", id_terreno='" + getId_terreno() + "'" +
            ", tamaño='" + getTamaño() + "'" +
            ", limites='" + getLimites() + "'" +
            ", ubicacion='" + getUbicacion() + "'" +
            ", alquilada='" + isAlquilada() + "'" +
            "}";
    }

}
