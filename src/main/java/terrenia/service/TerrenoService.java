package terrenia.service;

import java.sql.SQLException;

import terrenia.dao.TerrenoDAO;
import terrenia.dto.Terreno;

public class TerrenoService {
    private TerrenoDAO terrenoDAO;

    public TerrenoService() {
        this.terrenoDAO = new TerrenoDAO();
    }

    // R1 - Agregar terreno
    public String agregarTerreno(double tamaño, String tipoDeTerreno, String ubicación) {   
        try {
            Terreno terreno = new Terreno();
            terreno.setTamaño(tamaño);
            terreno.setUbicación(ubicación);
            terreno.setTipoDeTerreno(tipoDeTerreno);

            return terrenoDAO.insertTerreno(terreno);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        }
    }

    // R2 - Eliminar terreno
    public String eliminarTerreno(int id) {
        Terreno terreno = new Terreno();
        terreno.setId(id);
        try {
            return terrenoDAO.deleteTerreno(terreno);
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        }
    }

    // R3 - Modificar terreno
    /*     
    public String modificarTerreno(int id, double tamaño, String tipoDeTerreno, String ubicación) {
        try {
            Terreno terreno = new Terreno(id, tamaño, tipoDeTerreno, ubicación);
            return terrenoDAO.updateTerreno(terreno);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        }
    } */

    // R4 - Consultar terreno
    public String consultarTerreno(int id) {
        Terreno terreno = new Terreno();
        terreno.setId(id);
        try {
            return terrenoDAO.findTerrenoById(terreno).toString();
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        } catch (NullPointerException e) {
            return "Error: No existe el terreno con id: " + id;
        }
    }
}
