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


    public Arrendatario(String DNI, String nombre, int edad, String sexo, String email, String info_ingreso, Date fechaRegistro) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.email = email;
        this.info_ingreso = info_ingreso;
        this.fechaRegistro = fechaRegistro;
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
        this.info_ingreso = info_ingreso;
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
