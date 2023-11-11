package terrenia.dto;

import java.sql.Date;

public class Arrendatario {
    private String DNI;
    private String nombre;
    private int edad;
    private String sexo;
    private String email;
    private String info_ingreso;
    private Date fechaRegistro;


    public Arrendatario() {
    }

    public Arrendatario(String DNI, String nombre, int edad, String sexo, String email, String info_ingreso, Date fechaRegistro) throws IllegalArgumentException {
        setDNI(DNI);
        setNombre(nombre);
        setEdad(edad);
        setEmail(email);
        setInfo_ingreso(info_ingreso);
        setFechaRegistro(fechaRegistro);
        setSexo(sexo);
    }

    public String getDNI() {
        return this.DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo_ingreso() {
        return this.info_ingreso;
    }

    public void setInfo_ingreso(String info_ingreso) {
        if ("nomina".equals(info_ingreso) || "contrato".equals(info_ingreso) || "aval_bancario".equals(info_ingreso) || "aval_persona".equals(info_ingreso)) {
            this.info_ingreso = info_ingreso;
        } else {
            throw new IllegalArgumentException("Informacion de ingreso no valida. Debe ser nomina, contrato, aval de trabajo, aval bancario.");
        }
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "{" +
            " DNI='" + getDNI() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", edad='" + getEdad() + "'" +
            ", sexo='" + getSexo() + "'" +
            ", email='" + getEmail() + "'" +
            ", info_ingreso='" + getInfo_ingreso() + "'" +
            ", fechaRegistro='" + getFechaRegistro() + "'" +
            "}";
    }

}
