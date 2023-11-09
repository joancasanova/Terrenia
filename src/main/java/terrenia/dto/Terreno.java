package terrenia.dto;

public class Terreno {
    private int id;
    private double tamaño;
    private String tipoDeTerreno;
    private String ubicación;

    public Terreno(int id, double tamaño, String tipoDeTerreno, String ubicación) {
        this.id = id;
        this.tamaño = tamaño;
        this.tipoDeTerreno = tipoDeTerreno;
        this.ubicación = ubicación;
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

    public String getTipoDeTerreno() {
        return this.tipoDeTerreno;
    }

    public void setTipoDeTerreno(String tipoDeTerreno) {
        this.tipoDeTerreno = tipoDeTerreno;
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
        setTipoDeTerreno(tipoDeTerreno);
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
            ", tipoDeTerreno='" + getTipoDeTerreno() + "'" +
            ", ubicación='" + getUbicación() + "'" +
            "}";
    }
    
}
