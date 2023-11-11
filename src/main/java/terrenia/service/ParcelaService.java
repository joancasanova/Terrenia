package terrenia.service;

import java.sql.SQLException;

import terrenia.dao.ParcelaDAO;
import terrenia.dto.Parcela;

public class ParcelaService {

    private ParcelaDAO parcelaDAO;

    public ParcelaService() {
        this.parcelaDAO = new ParcelaDAO();
    }

    public String agregarParcela(int id_terreno, double tamaño, String limites, String ubicacion, Boolean alquilada) {
        try {
            Parcela parcela = new Parcela();
            parcela.setId_terreno(id_terreno);
            parcela.setTamaño(tamaño);
            parcela.setLimites(limites);
            parcela.setUbicacion(ubicacion);
            parcela.setAlquilada(alquilada);   

            return parcelaDAO.insertParcela(parcela);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        }
    }

    public String obtenerParcelaPorId(int idParcela) {
        Parcela parcela = new Parcela();
        parcela.setId_parcela(idParcela);
        try {
            return parcelaDAO.findParcelaById(parcela).toString();
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        } catch (NullPointerException e) {
            return "Error: No existe la parcela con id: " + idParcela;
        }
    }

    public String actualizarParcela(int id_parcela, double tamaño, String limites, String ubicacion, Boolean alquilada) {
        try {
            Parcela parcela = new Parcela();
            parcela.setTamaño(tamaño);
            parcela.setLimites(limites);
            parcela.setUbicacion(ubicacion);
            parcela.setAlquilada(alquilada);   
            parcela.setId_parcela(id_parcela);
            return parcelaDAO.updateParcela(parcela);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        }
    }
    

    public String eliminarParcela(int idParcela) {
        Parcela parcela = new Parcela();
        parcela.setId_parcela(idParcela);
        try {
            return parcelaDAO.deleteParcela(parcela);
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        }
    }
}
