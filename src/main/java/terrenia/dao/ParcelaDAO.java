package terrenia.dao;

import terrenia.dto.Parcela;
import terrenia.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParcelaDAO {

    private final DatabaseConnector connector = DatabaseConnector.getInstance();

    // Método para insertar una nueva parcela
    public String insertParcela(int id_terreno, double tamaño, String limites, String ubicacion, Boolean alquilada) {
        String sql = "INSERT INTO Parcelas (tamaño, limites, ubicacion, id_terreno, alquilada) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDouble(1, tamaño);
            pstmt.setString(2, limites);
            pstmt.setString(3, ubicacion);
            pstmt.setInt(4, id_terreno);
            pstmt.setBoolean(5, alquilada);
    
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long idNuevo = generatedKeys.getLong(1); // Obtener el ID generado
                        return "Parcela creada correctamente con ID: " + idNuevo;
                    } else {
                        return "Parcela creada, pero no se pudo obtener el ID";
                    }
                }
            } else {
                return "Hubo un problema al agregar el terreno";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }

    // Método para obtener una parcela por ID
    public Parcela findParcelaById(int idParcela) throws SQLException {
        String sql = "SELECT * FROM Parcelas WHERE id_parcela = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idParcela);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Parcela(
                        rs.getInt("id_parcela"),
                        rs.getInt("id_terreno"),
                        rs.getDouble("tamaño"),
                        rs.getString("limites"),
                        rs.getString("ubicacion"),
                        rs.getBoolean("alquilada")
                );
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return null;
    }

    // Método para actualizar una parcela
    public String updateParcela(Parcela parcela) {
        String sql = "UPDATE Parcelas SET tamaño = ?, limites = ?, ubicacion = ?, id_terreno = ?, alquilada = ? WHERE id_parcela = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, parcela.getTamaño());
            pstmt.setString(2, parcela.getLimites());
            pstmt.setString(3, parcela.getUbicacion());
            pstmt.setInt(4, parcela.getId_terreno());
            pstmt.setBoolean(5, parcela.getAlquilada());
            pstmt.setInt(6, parcela.getId_parcela());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Parcela modificada correctamente";
            }
            else {
                return "Hubo un problema en la modificacion de la parcela";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }

    // Método para eliminar una parcela
    public String deleteParcela(int idParcela) {
        String sql = "DELETE FROM Parcelas WHERE id_parcela = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idParcela);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Parcela eliminada correctamente";
            }
            else {
                return "Hubo un problema al eliminar la parcela";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }
}
