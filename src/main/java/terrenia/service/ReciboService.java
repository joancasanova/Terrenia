package terrenia.service;

import java.math.BigDecimal;
import java.sql.SQLException;

import terrenia.dao.ArrendatarioDAO;
import terrenia.dao.ParcelaDAO;
import terrenia.dao.ReciboDAO;
import terrenia.dao.TerrenoDAO;
import terrenia.dto.Arrendatario;
import terrenia.dto.Parcela;
import terrenia.dto.Recibo;
import terrenia.dto.Terreno;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import java.sql.Date;

public class ReciboService {

    private ReciboDAO reciboDAO;
    private ArrendatarioDAO arrendatarioDAO;
    private ParcelaDAO parcelaDAO;
    private TerrenoDAO terrenoDAO;

    public ReciboService() {
        this.reciboDAO = new ReciboDAO();
        this.arrendatarioDAO = new ArrendatarioDAO();
        this.parcelaDAO = new ParcelaDAO();
        this.terrenoDAO = new TerrenoDAO();
    }

    public String generarRecibo(int idParcela, String idArrendatario, Date fechaInicio, BigDecimal importe) {
        Recibo recibo = new Recibo();
        recibo.setIdParcela(idParcela);

        String respuesta;
        String respuestaEmail;
        try {
            Parcela parcela = parcelaDAO.findParcelaById(idParcela);
            Arrendatario arrendatario = arrendatarioDAO.findArrendatarioByDNI(idArrendatario);
            Terreno terreno = terrenoDAO.findTerrenoById(parcela.getIdTerreno());

            recibo.setTipoTerreno(terreno.getTipoTerreno());
            recibo.setFechaEmision(fechaInicio);
            recibo.setImporte(importe);
            recibo.setIVA(15);
            recibo.setIRPF(15);
            recibo.setEmail(arrendatario.getEmail());
            recibo.setCobrado(true);

            respuesta = reciboDAO.insertRecibo(recibo);
            respuestaEmail = enviarRecibo(recibo);
        } catch (IllegalArgumentException e) {
            respuesta = "Error: " + e.getMessage();
            respuestaEmail = "";
        } catch (SQLException e) {
            respuesta = "Error en la base de datos: " + e.getMessage();
            respuestaEmail = "";
        }

        return respuesta + "\n" + respuestaEmail;

    }

    private String enviarRecibo(Recibo recibo) {
        final String from = "juancasanovaferrer@gmail.com";
        final String password = "mdreufermpfsnzkz ";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(from, password);
            }
        });

        try {
            String subject = "Terrenia - Recibo alquiler " + recibo.getTipoTerreno();
            String body = "Alquilada parcela con identificador " + recibo.getIdParcela() + ". Importe de "
                    + recibo.getImporte()
                    + " euros.\nMuchas gracias por confiar en nosotros!\nEl equipo de JEJIS le desea una FELIZ NAVIDAD!!";

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recibo.getEmail()));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            return "Correo enviado exitosamente!";
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return "Error al enviar correo: " + mex.getMessage();
        }
    }
}
