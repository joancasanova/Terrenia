package terrenia.dao;

import terrenia.util.DatabaseConnector;
import terrenia.dto.Terreno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TerrenoDAO {

    private final DatabaseConnector connector = DatabaseConnector.getInstance();

    public String insertTerreno(double tamaño, String tipoDeTerreno, String ubicación) {
        String sql = "INSERT INTO Terrenos (tamaño, tipo_terreno, ubicacion) VALUES (?, ?, ?)";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDouble(1, tamaño);
            pstmt.setString(2, tipoDeTerreno);
            pstmt.setString(3, ubicación);
    
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
            return e.toString();
        }
    }

    public Terreno findTerrenoById(int id) throws SQLException {
        String sql = "SELECT * FROM Terrenos WHERE ID = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
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

    public String updateTerreno(Terreno terreno) {
        String sql = "UPDATE Terrenos SET tamaño = ?, tipo_terreno = ?, ubicacion = ? WHERE ID = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, terreno.getTamaño());
            pstmt.setString(2, terreno.getTipoDeTerreno());
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
            return e.toString();
        }
    }

    public String deleteTerreno(int id) {
        String sql = "DELETE FROM Terrenos WHERE ID = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Terreno eliminado correctamente";
            }
            else {
                return "Hubo un problema al eliminar el terreno";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }
}
