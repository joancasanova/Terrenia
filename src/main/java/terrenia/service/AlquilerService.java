package terrenia.service;

import terrenia.dao.AlquilerDAO;
import terrenia.dto.Alquiler;
import java.math.BigDecimal;
import java.sql.Date;

public class AlquilerService {

    private AlquilerDAO alquilerDAO;

    public AlquilerService() {
        this.alquilerDAO = new AlquilerDAO();
    }

    public String registrarAlquiler(int idParcela, String idArrendatario, Date fechaInicio, BigDecimal importe, String periodo) {
        // Crear un objeto Alquiler con los datos proporcionados
        Alquiler alquiler = new Alquiler();
        alquiler.setIdParcela(idParcela);
        alquiler.setIdArrendatario(idArrendatario);
        alquiler.setFechaInicio(fechaInicio);
        alquiler.setImporte(importe);
        alquiler.setPeriodo(periodo);

        // Utilizar el DAO para insertar el alquiler en la base de datos
        return alquilerDAO.insertAlquiler(alquiler);
    }

    public String finalizarAlquiler(int idParcela, String idArrendatario) {
        // Utilizar el DAO para eliminar el alquiler de la base de datos
        return alquilerDAO.deleteAlquiler(idParcela, idArrendatario);
    }
}
