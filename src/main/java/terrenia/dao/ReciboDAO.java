package terrenia.dao;

import terrenia.util.DatabaseConnector;
import terrenia.dto.Recibo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase DAO para manejar las operaciones de base de datos para los Recibos.
 * Permite insertar registros de recibos en la base de datos.
 */
public class ReciboDAO {

    private final DatabaseConnector connector = DatabaseConnector.getInstance();
    
    /**
     * Inserta un nuevo recibo en la base de datos.
     *
     * @param recibo El objeto Recibo con la información a insertar.
     * @return Un mensaje indicando el resultado de la operación.
     * @throws SQLException
     */
    public String insertRecibo(Recibo recibo) throws SQLException {
        String insertSql = "INSERT INTO Recibos (id_area, tipo_area, fecha_emision, importe, IVA, IRPF, email_arrendatario, cobrado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, recibo.getIdParcela());
            pstmt.setString(2, recibo.getTipoTerreno());
            pstmt.setDate(3, new java.sql.Date(recibo.getFechaEmision().getTime()));
            pstmt.setBigDecimal(4, recibo.getImporte());
            pstmt.setInt(5, recibo.getIVA());
            pstmt.setInt(6, recibo.getIRPF());
            pstmt.setString(7, recibo.getEmail());
            pstmt.setBoolean(8, recibo.getCobrado());
            int affectedRows = pstmt.executeUpdate();
    
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long idNuevo = generatedKeys.getLong(1);
                        return "Recibo creado correctamente con ID: " + idNuevo;
                    } else {
                        return "Recibo creado, pero no se pudo obtener el ID";
                    }
                }
            } else {
                return "Hubo un problema en la creacion del recibo";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }    
}
