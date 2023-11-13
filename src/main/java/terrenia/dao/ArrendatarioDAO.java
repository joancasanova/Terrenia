package terrenia.dao;

import terrenia.util.DatabaseConnector;
import terrenia.dto.Arrendatario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase ArrendatarioDAO para gestionar las operaciones de la base de datos relacionadas con los arrendatarios.
 */
public class ArrendatarioDAO {

    private final DatabaseConnector connector = DatabaseConnector.getInstance();

    /**
     * Inserta un nuevo arrendatario en la base de datos.
     * 
     * @param arrendatario El objeto Arrendatario a insertar.
     * @return String Mensaje de resultado de la operación.
     * @throws SQLException
     */
    public String insertArrendatario(Arrendatario arrendatario) throws SQLException {
        String sql = "INSERT INTO Arrendatarios (DNI, nombre, edad, sexo, email, info_ingreso, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, arrendatario.getDNI());
            pstmt.setString(2, arrendatario.getNombre());
            pstmt.setInt(3, arrendatario.getEdad());
            pstmt.setString(4, arrendatario.getSexo());
            pstmt.setString(5, arrendatario.getEmail());
            pstmt.setString(6, arrendatario.getInfo_ingreso());
            pstmt.setDate(7, new java.sql.Date(arrendatario.getFechaRegistro().getTime()));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Arrendatario agregado correctamente";
            }
            else {
                return "Hubo un problema en la creacion del arrendatario";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    /**
     * Busca un arrendatario en la base de datos usando su DNI.
     * 
     * @param arrendatario El arrendatario a buscar.
     * @return Arrendatario El objeto Arrendatario encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error de SQL durante la búsqueda.
     */
    public Arrendatario findArrendatarioByDNI(Arrendatario arrendatario) throws SQLException {
        String sql = "SELECT * FROM Arrendatarios WHERE DNI = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, arrendatario.getDNI());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Arrendatario(
                        rs.getString("DNI"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("sexo"),
                        rs.getString("email"),
                        rs.getString("info_ingreso"),
                        rs.getDate("fecha_registro")
                );
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return null;
    }

    /**
     * Actualiza la información de un arrendatario en la base de datos.
     * 
     * @param arrendatario El objeto Arrendatario con la información actualizada.
     * @return String Mensaje de resultado de la operación.
     * @throws SQLException
     */
    /* 
    public String updateArrendatario(Arrendatario arrendatario) throws SQLException {
        String sql = "UPDATE Arrendatarios SET nombre = ?, edad = ?, sexo = ?, email = ?, info_ingreso = ? WHERE DNI = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, arrendatario.getNombre());
            pstmt.setInt(2, arrendatario.getEdad());
            pstmt.setString(3, arrendatario.getSexo());
            pstmt.setString(4, arrendatario.getEmail());
            pstmt.setString(5, arrendatario.getInfo_ingreso());
            pstmt.setString(6, arrendatario.getDNI());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Arrendatario modificado correctamente";
            }
            else {
                return "Hubo un problema en la modificacion del arrendatario";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    } */

    /**
     * Elimina un arrendatario de la base de datos usando su DNI.
     * 
     * @param arrendatario El arrendatario a eliminar.
     * @return String Mensaje de resultado de la operación.
     * @throws SQLException
     */
    public String deleteArrendatario(Arrendatario arrendatario) throws SQLException {
        String sql = "DELETE FROM Arrendatarios WHERE DNI = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, arrendatario.getDNI());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Arrendatario borrado correctamente";
            }
            else {
                return "Hubo un problema al borrar el arrendatario";
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
