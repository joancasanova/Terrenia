package terrenia.dao;

import terrenia.dto.Parcela;
import terrenia.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que gestiona las operaciones de base de datos para las parcelas.
 * Incluye métodos para insertar, buscar, actualizar y eliminar parcelas.
 */
public class ParcelaDAO {

    private final DatabaseConnector connector = DatabaseConnector.getInstance();

    /**
     * Inserta una nueva parcela en la base de datos.
     *
     * @param parcela El objeto Parcela con la información a insertar.
     * @return Un mensaje indicando el resultado de la operación.
     * @throws SQLException
     */
    public String insertParcela(Parcela parcela) throws SQLException {
        String sql = "INSERT INTO Parcelas (tamaño, limites, ubicacion, id_terreno, alquilada) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDouble(1, parcela.getTamaño());
            pstmt.setString(2, parcela.getLimites());
            pstmt.setString(3, parcela.getUbicacion());
            pstmt.setInt(4, parcela.getId_terreno());
            pstmt.setBoolean(5, parcela.getAlquilada());
    
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
            throw new SQLException(e);
        }
    }

    /**
     * Obtiene una parcela por su ID de la base de datos.
     *
     * @param parcela Objeto Parcela con el ID de la parcela a buscar.
     * @return Objeto Parcela con los datos de la parcela encontrada, o null si no se encuentra.
     * @throws SQLException
     */
    public Parcela findParcelaById(Parcela parcela) throws SQLException {
        String sql = "SELECT * FROM Parcelas WHERE id_parcela = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, parcela.getId_parcela());
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

    /**
     * Actualiza los detalles de una parcela existente en la base de datos.
     *
     * @param parcela Objeto Parcela con los datos actualizados.
     * @return Un mensaje indicando el resultado de la operación.
     * @throws SQLException
     */
    public String updateParcela(Parcela parcela) throws SQLException {
        String sql = "UPDATE Parcelas SET tamaño = ?, limites = ?, ubicacion = ?, alquilada = ? WHERE id_parcela = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, parcela.getTamaño());
            pstmt.setString(2, parcela.getLimites());
            pstmt.setString(3, parcela.getUbicacion());
            pstmt.setBoolean(4, parcela.getAlquilada());
            pstmt.setInt(5, parcela.getId_parcela());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Parcela modificada correctamente";
            }
            else {
                return "Hubo un problema en la modificacion de la parcela";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    /**
     * Elimina una parcela de la base de datos.
     *
     * @param parcela Objeto Parcela que representa la parcela a eliminar.
     * @return Un mensaje indicando el resultado de la operación.
     * @throws SQLException
     */
    public String deleteParcela(Parcela parcela) throws SQLException {
        String sql = "DELETE FROM Parcelas WHERE id_parcela = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, parcela.getId_parcela());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Parcela eliminada correctamente";
            }
            else {
                return "Hubo un problema al eliminar la parcela";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
