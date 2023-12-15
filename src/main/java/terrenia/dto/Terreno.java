package terrenia.dto;

public class Terreno {
    private int id;
    private double tamaño; // Hectareas
    private String tipoTerreno; // Latifundio o Finca
    private String ubicación; // Municipio

    public Terreno() {
    }

    public Terreno(int id, double tamaño, String tipoDeTerreno, String ubicación) throws IllegalArgumentException {
        setId(id);
        setTamaño(tamaño);
        setTipoTerreno(tipoDeTerreno);
        setUbicación(ubicación);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTamaño() {
        return this.tamaño;
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }

    public String getTipoTerreno() {
        return this.tipoTerreno;
    }

    public void setTipoTerreno(String tipoDeTerreno) throws IllegalArgumentException {
        if ("latifundio".equals(tipoDeTerreno) || "finca".equals(tipoDeTerreno)) {
            this.tipoTerreno = tipoDeTerreno;
        } else {
            throw new IllegalArgumentException("Tipo de terreno no valido. Debe ser 'latifundio' o 'finca'.");
        }
    }
    
    public String getUbicación() {
        return this.ubicación;
    }

    public void setUbicación(String ubicación) {
        this.ubicación = ubicación;
    }

    public Terreno id(int id) {
        setId(id);
        return this;
    }

    public Terreno tamaño(double tamaño) {
        setTamaño(tamaño);
        return this;
    }

    public Terreno tipoDeTerreno(String tipoDeTerreno) {
        setTipoTerreno(tipoDeTerreno);
        return this;
    }

    public Terreno ubicación(String ubicación) {
        setUbicación(ubicación);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", tamaño='" + getTamaño() + "'" +
            ", tipoDeTerreno='" + getTipoTerreno() + "'" +
            ", ubicación='" + getUbicación() + "'" +
            "}";
    }
    
}
