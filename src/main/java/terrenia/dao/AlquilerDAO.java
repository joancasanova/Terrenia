package terrenia.dao;

import terrenia.util.DatabaseConnector;
import terrenia.dto.Alquiler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlquilerDAO {

    private final DatabaseConnector connector = DatabaseConnector.getInstance();

    public String insertAlquiler(Alquiler alquiler) {
        String sql = "INSERT INTO Alquileres (id_parcela, id_arrendatario, fecha_inicio, importe, periodo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, alquiler.getIdParcela());
            pstmt.setString(2, alquiler.getIdArrendatario());
            pstmt.setDate(3, new java.sql.Date(alquiler.getFechaInicio().getTime()));
            pstmt.setBigDecimal(4, alquiler.getImporte());
            pstmt.setString(5, alquiler.getPeriodo());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Alquiler creado correctamente";
            }
            else {
                return "Hubo un problema en la creacion del alquiler";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public String deleteAlquiler(int idParcela, String idArrendatario) {
        String sql = "DELETE FROM Alquileres WHERE id_parcela = ? AND id_arrendatario = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idParcela);
            pstmt.setString(2, idArrendatario);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Alquiler dado de baja correctamente";
            }
            else {
                return "Hubo un problema al dar de baja el alquiler";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }
}
