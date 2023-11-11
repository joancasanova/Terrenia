package terrenia.dao;

import terrenia.util.DatabaseConnector;
import terrenia.dto.Alquiler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Calendar;

/**
 * Clase DAO para manejar las operaciones de base de datos para los Alquileres.
 * Permite insertar y eliminar registros de alquileres en la base de datos.
 */
public class AlquilerDAO {

    private final DatabaseConnector connector = DatabaseConnector.getInstance();
    
    /**
     * Inserta un nuevo alquiler en la base de datos y actualiza el estado de la parcela.
     * 
     * @param alquiler El objeto Alquiler que contiene los detalles del alquiler a insertar.
     * @return Un mensaje indicando el resultado de la operación.
     * @throws SQLException
     */
    public String insertAlquiler(Alquiler alquiler) throws SQLException {
        String insertSql = "INSERT INTO Alquileres (id_parcela, id_arrendatario, fecha_inicio, importe, periodo) VALUES (?, ?, ?, ?, ?)";
        String updateSql = "UPDATE Parcelas SET alquilada = 1 WHERE id_parcela = ?";
        
        try (Connection conn = connector.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            
            // Preparar y ejecutar la inserción en Alquileres
            insertStmt.setInt(1, alquiler.getIdParcela());
            insertStmt.setString(2, alquiler.getIdArrendatario());
            insertStmt.setDate(3, new java.sql.Date(alquiler.getFechaInicio().getTime()));
            insertStmt.setBigDecimal(4, alquiler.getImporte());
            insertStmt.setInt(5, alquiler.getPeriodo());
            int affectedRows = insertStmt.executeUpdate();
    
            // Si la inserción es exitosa, actualizar la tabla Parcelas
            if (affectedRows > 0) {
                updateStmt.setInt(1, alquiler.getIdParcela());
                updateStmt.executeUpdate();
                return "Alquiler creado correctamente y parcela actualizada";
            } else {
                return "Hubo un problema en la creacion del alquiler";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }    
    
    /**
     * Elimina un alquiler existente de la base de datos y actualiza el estado de la parcela.
     * 
     * @param alquiler El objeto Alquiler que contiene los detalles del alquiler a eliminar.
     * @return Un mensaje indicando el resultado de la operación.
     * @throws SQLException
     */
    public String deleteAlquiler(Alquiler alquiler) throws SQLException {
        String deleteSql = "DELETE FROM Alquileres WHERE id_parcela = ? AND id_arrendatario = ?";
        String updateSql = "UPDATE Parcelas SET alquilada = 0 WHERE id_parcela = ?";

        try (Connection conn = connector.getConnection();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            
            // Preparar y ejecutar el borrado en Alquileres
            deleteStmt.setInt(1, alquiler.getIdParcela());
            deleteStmt.setString(2, alquiler.getIdArrendatario());
            int affectedRows = deleteStmt.executeUpdate();
    
            // Si el borrado es exitos, actualizar la tabla Parcelas
            if (affectedRows > 0) {
                updateStmt.setInt(1, alquiler.getIdParcela());
                updateStmt.executeUpdate();
                return "Alquiler dado de baja correctamente";
            } else {
                return "Hubo un problema al dar de baja el alquiler";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }    

    /**
     * Comprueba si la parcela está disponible para ser alquilada en el periodo especificado.
     * 
     * @param alquiler El objeto Alquiler que contiene los detalles del alquiler a eliminar.
     * @return true si la parcela está disponible, false en caso contrario.
     * @throws SQLException
     */
    public boolean isParcelaDisponible(Alquiler alquiler) throws SQLException {
        Date fechaInicio = alquiler.getFechaInicio();
        Integer periodo = alquiler.getPeriodo();        
    
        // Calculo de la fecha de fin del alquiler
        Date fechaFin = calcularFechaFin(fechaInicio, periodo);
    
        // Modificación de la consulta SQL para usar DATEADD con días
        String sql = "SELECT CASE WHEN EXISTS (" +
                        "SELECT 1 FROM Alquileres " +
                        "WHERE id_parcela = ?" +
                        "AND (" +
                            "(fecha_inicio >= ? AND fecha_inicio <= ?) " +
                            "OR " +
                            "(DATEADD(day, periodo, fecha_inicio) >= ? AND DATEADD(day, periodo, fecha_inicio) <= ?)" +
                        ")" +
                    ") THEN 1 ELSE 0 END";
    
        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, alquiler.getIdParcela());
            stmt.setDate(2, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(3, new java.sql.Date(fechaFin.getTime()));
            stmt.setDate(4, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(5, new java.sql.Date(fechaFin.getTime()));
    
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0; // Si no hay alquileres que se solapen, la parcela está disponible
            } else {
                return false; // En caso de error o falta de respuesta, se considera no disponible
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    

    /**
     * Calcula la fecha de fin del alquiler a partir de la fecha de inicio y el periodo en días.
     * 
     * @param fechaInicio La fecha de inicio del alquiler.
     * @param periodo El periodo de alquiler en días.
     * @return La fecha de fin del alquiler.
     */
    private Date calcularFechaFin(Date fechaInicio, Integer periodo) {

        // Adapter la variable de la fecha al formato adecuado para Calendar
        java.util.Date utilDatefechaInicio = new java.util.Date(fechaInicio.getTime());

        // Crear un calendario y sumarle los dias
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(utilDatefechaInicio);
        calendario.add(Calendar.DAY_OF_MONTH, periodo);

        return new java.sql.Date(calendario.getTime().getTime()); // Devolver la fecha de fin calculada
    }
}
