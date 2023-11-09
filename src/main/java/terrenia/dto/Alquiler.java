package terrenia.dto;
import java.math.BigDecimal;
import java.sql.Date;

public class Alquiler {
    private int idParcela;
    private String idArrendatario;
    private Date fechaInicio;
    private BigDecimal importe;
    private String periodo;

    public Alquiler() {
    }
    
    // Constructor
    public Alquiler(int idParcela, String idArrendatario, Date fechaInicio, BigDecimal importe, String periodo) {
        this.idParcela = idParcela;
        this.idArrendatario = idArrendatario;
        this.fechaInicio = fechaInicio;
        this.importe = importe;
        this.periodo = periodo;
    }

    // Getters y Setters
    public int getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }

    public String getIdArrendatario() {
        return idArrendatario;
    }

    public void setIdArrendatario(String idArrendatario) {
        this.idArrendatario = idArrendatario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    // Método toString para representar la información del alquiler como String
    @Override
    public String toString() {
        return "Alquiler{" +
                "idParcela=" + idParcela +
                ", idArrendatario='" + idArrendatario + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", importe=" + importe +
                ", periodo='" + periodo + '\'' +
                '}';
    }
}
