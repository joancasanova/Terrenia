package terrenia.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import terrenia.service.AlquilerService;

public class AlquilerPanel extends JPanel {

    private AlquilerService alquilerService;
    private JTextField idParcelaField, idArrendatarioField, fechaInicioField, importeField, periodoField;
    private JButton registerButton, finalizeButton;

    public AlquilerPanel(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
        setLayout(new BorderLayout());

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        inputPanel.add(new JLabel("ID Parcela:"));
        idParcelaField = new JTextField();
        inputPanel.add(idParcelaField);

        inputPanel.add(new JLabel("ID Arrendatario:"));
        idArrendatarioField = new JTextField();
        inputPanel.add(idArrendatarioField);

        inputPanel.add(new JLabel("Fecha Inicio (dd-MM-yyyy):"));
        fechaInicioField = new JTextField();
        inputPanel.add(fechaInicioField);

        inputPanel.add(new JLabel("Importe:"));
        importeField = new JTextField();
        inputPanel.add(importeField);

        inputPanel.add(new JLabel("Periodo:"));
        periodoField = new JTextField();
        inputPanel.add(periodoField);

        // Panel de botones
        JPanel buttonPanel = new JPanel();

        registerButton = new JButton("Registrar Alquiler");
        registerButton.addActionListener(e -> registrarAlquiler());
        buttonPanel.add(registerButton);

        finalizeButton = new JButton("Finalizar Alquiler");
        finalizeButton.addActionListener(e -> finalizarAlquiler());
        buttonPanel.add(finalizeButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void registrarAlquiler() {
        // Se usa un SimpleDateFormat para parsear la fecha ingresada a un objeto Date de SQL
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaInicio = null;
        try {
            java.util.Date dateStr = formatter.parse(fechaInicioField.getText());
            fechaInicio = new Date(dateStr.getTime());
        } catch (ParseException ex) {
            mostrarMensaje("Formato de fecha incorrecto. Use el formato dd-MM-yyyy.");
            return;
        }

        BigDecimal importe = null;
        try {
            importe = new BigDecimal(importeField.getText());
        } catch (NumberFormatException ex) {
            mostrarMensaje("Formato de importe incorrecto. Debe ser un número válido.");
            return;
        }

        String resultado = alquilerService.registrarAlquiler(
                Integer.parseInt(idParcelaField.getText()),
                idArrendatarioField.getText(),
                fechaInicio,
                importe,
                periodoField.getText()
        );
        mostrarMensaje(resultado);
    }

    private void finalizarAlquiler() {
        String resultado = alquilerService.finalizarAlquiler(
                Integer.parseInt(idParcelaField.getText()),
                idArrendatarioField.getText()
        );
        mostrarMensaje(resultado);
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
