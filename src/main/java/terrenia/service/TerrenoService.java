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
        return terrenoDAO.insertTerreno(tamaño, tipoDeTerreno, ubicación);
    }

    // R2 - Eliminar terreno
    public String eliminarTerreno(int id) {
        return terrenoDAO.deleteTerreno(id);
    }

    // R3 - Modificar terreno
    public String modificarTerreno(int id, double tamaño, String tipoDeTerreno, String ubicación) {
        Terreno terreno = new Terreno(id, tamaño, tipoDeTerreno, ubicación);
        return terrenoDAO.updateTerreno(terreno);
    }

    // R4 - Consultar terreno
    public String consultarTerreno(int id) {
        try {
            return terrenoDAO.findTerrenoById(id).toString();
        } catch (SQLException e) {
            return e.toString();
        }
    }
}
