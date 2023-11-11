package terrenia.service;

import terrenia.dao.AlquilerDAO;
import terrenia.dto.Alquiler;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

public class AlquilerService {

    private AlquilerDAO alquilerDAO;

    public AlquilerService() {
        this.alquilerDAO = new AlquilerDAO();
    }

    public String registrarAlquiler(int idParcela, String idArrendatario, Date fechaInicio, BigDecimal importe, Integer periodo) {
        try {
            // Crear un objeto Alquiler con los datos proporcionados
            Alquiler alquiler = new Alquiler(idParcela, idArrendatario, fechaInicio, importe, periodo);

            // Comprobar disponibilidad de la parcela
            if (alquilerDAO.isParcelaDisponible(alquiler)) {
                // Insertar el alquiler en la base de datos
                return alquilerDAO.insertAlquiler(alquiler);
            } else {
                return "La parcela no está disponible para las fechas y periodo solicitados";
            }
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        }
    }

    public String finalizarAlquiler(int idParcela, String idArrendatario) {
        // Utilizar el DAO para eliminar el alquiler de la base de datos
        Alquiler alquiler = new Alquiler();
        alquiler.setIdParcela(idParcela);
        alquiler.setIdArrendatario(idArrendatario);
        try {
            return alquilerDAO.deleteAlquiler(alquiler);
        } catch (SQLException e) {
            return "Error en la base de datos: " + e.getMessage();
        }
    }
}
