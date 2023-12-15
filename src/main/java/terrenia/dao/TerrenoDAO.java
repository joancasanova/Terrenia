package terrenia.dao;

import terrenia.util.DatabaseConnector;
import terrenia.dto.Terreno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase TerrenoDAO para la gestión de datos de terrenos en la base de datos.
 * Proporciona métodos para insertar, encontrar, actualizar y eliminar terrenos.
 */
public class TerrenoDAO {

    private final DatabaseConnector connector = DatabaseConnector.getInstance();

    /**
     * Inserta un nuevo terreno en la base de datos.
     *
     * @param terreno El objeto Terreno a insertar.
     * @return String Mensaje indicando el resultado de la operación.
     * @throws SQLException
     */
    public String insertTerreno(Terreno terreno) throws SQLException {
        String sql = "INSERT INTO Terrenos (tamaño, tipo_terreno, ubicacion) VALUES (?, ?, ?)";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDouble(1, terreno.getTamaño());
            pstmt.setString(2, terreno.getTipoTerreno());
            pstmt.setString(3, terreno.getUbicación());
    
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long idNuevo = generatedKeys.getLong(1); // Obtener el ID generado
                        return "Terreno agregado correctamente con ID: " + idNuevo;
                    } else {
                        return "Terreno agregado, pero no se pudo obtener el ID";
                    }
                }
            } else {
                return "Hubo un problema al agregar el terreno";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    /**
     * Busca un terreno por su ID en la base de datos.
     *
     * @param idTerreno El ID a buscar.
     * @return Terreno El terreno encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error de SQL durante la operación.
     */
    public Terreno findTerrenoById(int idTerreno) throws SQLException {
        String sql = "SELECT * FROM Terrenos WHERE ID = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idTerreno);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Terreno(
                        rs.getInt("ID"),
                        rs.getDouble("tamaño"),
                        rs.getString("tipo_terreno"),
                        rs.getString("ubicacion")
                );
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return null;
    }
    
    /**
     * Actualiza los detalles de un terreno existente en la base de datos.
     *
     * @param terreno El objeto Terreno con los datos actualizados.
     * @return String Mensaje indicando el resultado de la operación.
     * @throws SQLException
     */
    
    public String updateTerreno(Terreno terreno) throws SQLException {
        String sql = "UPDATE Terrenos SET tamaño = ?, tipo_terreno = ?, ubicacion = ? WHERE ID = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, terreno.getTamaño());
            pstmt.setString(2, terreno.getTipoTerreno());
            pstmt.setString(3, terreno.getUbicación());
            pstmt.setInt(4, terreno.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Terreno modificado correctamente";
            }
            else {
                return "Hubo un problema al modificar el terreno";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    /**
     * Elimina un terreno de la base de datos.
     *
     * @param terreno El objeto Terreno a eliminar.
     * @return String Mensaje indicando el resultado de la operación.
     * @throws SQLException
     */
    public String deleteTerreno(Terreno terreno) throws SQLException {
        String sql = "DELETE FROM Terrenos WHERE ID = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, terreno.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Terreno eliminado correctamente";
            }
            else {
                return "Hubo un problema al eliminar el terreno";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
