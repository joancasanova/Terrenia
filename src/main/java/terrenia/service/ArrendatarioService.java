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
        try {
            Arrendatario arrendatario = new Arrendatario(DNI, nombre, edad, sexo, email, infoIngreso, fechaRegistro);
            return arrendatarioDAO.insertArrendatario(arrendatario);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        }
    }
    

    public String eliminarArrendatario(String DNI) {
        Arrendatario arrendatario = new Arrendatario();
        arrendatario.setDNI(DNI);
        try {
            return arrendatarioDAO.deleteArrendatario(arrendatario);
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        }
    }

    public String consultarArrendatario(String DNI) {
        Arrendatario arrendatario = new Arrendatario();
        arrendatario.setDNI(DNI);
        try {
            return arrendatarioDAO.findArrendatarioByDNI(arrendatario).toString();
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        } catch (NullPointerException e) {
            return "Error: No existe el arrendatario con DNI: " + DNI;
        }
    }

    public String modificarArrendatario(String DNI, String nombre, int edad, String sexo, String email, String infoIngreso) {
        try {
            Arrendatario arrendatario = new Arrendatario(DNI, nombre, edad, sexo, email, infoIngreso, null);
            return arrendatarioDAO.updateArrendatario(arrendatario);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        }
    }

}
