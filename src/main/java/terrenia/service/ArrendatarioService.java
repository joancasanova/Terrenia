package terrenia.service;

import terrenia.dao.ArrendatarioDAO;
import terrenia.dto.Arrendatario;
import java.sql.SQLException;
import java.sql.Date;

public class ArrendatarioService {

    private ArrendatarioDAO arrendatarioDAO;

    public ArrendatarioService() {
        this.arrendatarioDAO = new ArrendatarioDAO();
    }

    public String agregarArrendatario(String DNI, String nombre, int edad, String sexo, String email, String infoIngreso, Date fechaRegistro) {
        Arrendatario arrendatario = new Arrendatario(DNI, nombre, edad, sexo, email, infoIngreso, fechaRegistro);
        return arrendatarioDAO.insertArrendatario(arrendatario);
    }

    public String eliminarArrendatario(String DNI) {
        return arrendatarioDAO.deleteArrendatario(DNI);
    }

    public String consultarArrendatario(String DNI) {
        try {
            return arrendatarioDAO.findArrendatarioByDNI(DNI).toString();
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public String modificarArrendatario(String DNI, String nombre, int edad, String sexo, String email, String infoIngreso) {
        Arrendatario arrendatario = new Arrendatario(DNI, nombre, edad, sexo, email, infoIngreso, null);
        return arrendatarioDAO.updateArrendatario(arrendatario);
    }

}
