package terrenia.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class Recibo {
    private int idRecibo;
    private int idParcela;
    private String tipoTerreno;
    private Date fechaEmision;
    private BigDecimal importe;
    private int IVA;
    private int IRPF;
    private String email;
    private Boolean cobrado;

    public Recibo() {
    }

    public Recibo(int idRecibo, int idParcela, String tipoTerreno, Date fechaEmision, BigDecimal importe, int IVA, int IRPF,
            String email, Boolean cobrado) {
        this.idRecibo = idRecibo;
        this.idParcela = idParcela;
        this.tipoTerreno = tipoTerreno;
        this.fechaEmision = fechaEmision;
        this.importe = importe;
        this.IVA = IVA;
        this.IRPF = IRPF;
        this.email = email;
        this.cobrado = cobrado;
    }

    public int getIdRecibo() {
        return this.idRecibo;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public int getIdParcela() {
        return this.idParcela;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }

    public String getTipoTerreno() {
        return this.tipoTerreno;
    }

    public void setTipoTerreno(String tipoTerreno) throws IllegalArgumentException {
        if ("latifundio".equals(tipoTerreno) || "finca".equals(tipoTerreno)) {
            this.tipoTerreno = tipoTerreno;
        } else {
            throw new IllegalArgumentException("Tipo de terreno no valido. Debe ser 'latifundio' o 'finca'.");
        }
    }

    public Date getFechaEmision() {
        return this.fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public BigDecimal getImporte() {
        return this.importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public int getIVA() {
        return this.IVA;
    }

    public void setIVA(int IVA) {
        this.IVA = IVA;
    }

    public int getIRPF() {
        return this.IRPF;
    }

    public void setIRPF(int IRPF) {
        this.IRPF = IRPF;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isCobrado() {
        return this.cobrado;
    }

    public Boolean getCobrado() {
        return this.cobrado;
    }

    public void setCobrado(Boolean cobrado) {
        this.cobrado = cobrado;
    }

    @Override
    public String toString() {
        return "{" +
                " idRecibo='" + getIdRecibo() + "'" +
                ", idParcela='" + getIdParcela() + "'" +
                ", tipoTerreno='" + getTipoTerreno() + "'" +
                ", fechaEmision='" + getFechaEmision() + "'" +
                ", importe='" + getImporte() + "'" +
                ", IVA='" + getIVA() + "'" +
                ", IRPF='" + getIRPF() + "'" +
                ", email='" + getEmail() + "'" +
                ", cobrado='" + isCobrado() + "'" +
                "}";
    }

}
