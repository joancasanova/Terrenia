package terrenia.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import terrenia.service.ArrendatarioService;

public class ArrendatarioPanel extends JPanel {

    private ArrendatarioService arrendatarioService;
    private JTextField dniField, nombreField, edadField, sexoField, emailField, infoIngresoField;
    private JButton addButton, deleteButton, consultButton, updateButton;

    public ArrendatarioPanel(ArrendatarioService arrendatarioService) {
        this.arrendatarioService = arrendatarioService;
        setLayout(new BorderLayout());

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        inputPanel.add(new JLabel("DNI:"));
        dniField = new JTextField();
        inputPanel.add(dniField);

        inputPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        inputPanel.add(nombreField);

        inputPanel.add(new JLabel("Edad:"));
        edadField = new JTextField();
        inputPanel.add(edadField);

        inputPanel.add(new JLabel("Sexo:"));
        sexoField = new JTextField();
        inputPanel.add(sexoField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Información de Ingreso:"));
        infoIngresoField = new JTextField();
        inputPanel.add(infoIngresoField);

        // Panel de botones
        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarArrendatario();
            }
        });
        buttonPanel.add(addButton);

        deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarArrendatario();
            }
        });
        buttonPanel.add(deleteButton);

        consultButton = new JButton("Consultar");
        consultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarArrendatario();
            }
        });
        buttonPanel.add(consultButton);

        updateButton = new JButton("Modificar");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarArrendatario();
            }
        });
        buttonPanel.add(updateButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void agregarArrendatario() {
        java.util.Date utilDate = new java.util.Date();

        String resultado = arrendatarioService.agregarArrendatario(
            dniField.getText(),
            nombreField.getText(),
            Integer.parseInt(edadField.getText()),
            sexoField.getText(),
            emailField.getText(),
            infoIngresoField.getText(),
            new java.sql.Date(utilDate.getTime())
        );
        mostrarMensaje(resultado);
    }

    private void eliminarArrendatario() {
        String resultado = arrendatarioService.eliminarArrendatario(dniField.getText());
        mostrarMensaje(resultado);
    }

    private void consultarArrendatario() {
        String resultado = arrendatarioService.consultarArrendatario(dniField.getText());
        mostrarMensaje(resultado);
    }

    private void modificarArrendatario() {
        String resultado = arrendatarioService.modificarArrendatario(
            dniField.getText(),
            nombreField.getText(),
            Integer.parseInt(edadField.getText()),
            sexoField.getText(),
            emailField.getText(),
            infoIngresoField.getText()
        );
        mostrarMensaje(resultado);
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
