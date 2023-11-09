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
        return parcelaDAO.insertParcela(id_terreno, tamaño, ubicacion, ubicacion, alquilada);
    }

    public String obtenerParcelaPorId(int idParcela) {
        try {
            return parcelaDAO.findParcelaById(idParcela).toString();
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public String actualizarParcela(int id_parcela, int id_terreno, double tamaño, String limites, String ubicacion, Boolean alquilada) {
        Parcela parcela = new Parcela(id_parcela, id_terreno, tamaño, ubicacion, ubicacion, alquilada);
        return parcelaDAO.updateParcela(parcela);
    }

    public String eliminarParcela(int idParcela) {
        return parcelaDAO.deleteParcela(idParcela);
    }
}
