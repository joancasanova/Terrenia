package terrenia.dao;

import terrenia.util.DatabaseConnector;
import terrenia.dto.Arrendatario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArrendatarioDAO {

    private final DatabaseConnector connector = DatabaseConnector.getInstance();

    public String insertArrendatario(Arrendatario arrendatario) {
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
            return e.toString();
        }
    }

    public Arrendatario findArrendatarioByDNI(String DNI) throws SQLException {
        String sql = "SELECT * FROM Arrendatarios WHERE DNI = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, DNI);
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

    public String updateArrendatario(Arrendatario arrendatario) {
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
            return e.toString();
        }
    }

    public String deleteArrendatario(String DNI) {
        String sql = "DELETE FROM Arrendatarios WHERE DNI = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, DNI);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return "Arrendatario borrado correctamente";
            }
            else {
                return "Hubo un problema al borrar el arrendatario";
            }
        } catch (SQLException e) {
            return e.toString();
        }
    }
}
