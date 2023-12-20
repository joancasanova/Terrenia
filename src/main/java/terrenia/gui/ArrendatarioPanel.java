package terrenia.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import terrenia.service.ArrendatarioService;

public class ArrendatarioPanel extends JPanel {

    private ArrendatarioService arrendatarioService;
    private JTabbedPane tabbedPane;

    private JTextField nombreFieldAgregar, dniFieldAgregar, edadFieldAgregar, sexoFieldAgregar, emailFieldAgregar,
            infoIngresoFieldAgregar;
    private JTextField dniFieldEliminar;
    private JTextField dniFieldConsultar;
    private JTextField nombreFieldModificar, edadFieldModificar, sexoFieldModificar, emailFieldModificar,
            infoIngresoFieldModificar, dniFieldModificar;

    public ArrendatarioPanel(ArrendatarioService arrendatarioService) {
        this.arrendatarioService = arrendatarioService;
        setLayout(new BorderLayout());
        initTabbedPane();
    }

    private JLabel createTabLabel(String title) {
        JLabel label = new JLabel(title);
        Font tabFont = new Font("Arial", Font.BOLD, 16);
        label.setFont(tabFont);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return label;
    }

    private void initTabbedPane() {
        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        tabbedPane.addTab("Agregar", crearPanelAgregar());
        tabbedPane.setTabComponentAt(0, createTabLabel("Agregar"));

        tabbedPane.addTab("Eliminar", crearPanelEliminar());
        tabbedPane.setTabComponentAt(1, createTabLabel("Eliminar"));

        tabbedPane.addTab("Consultar", crearPanelConsultar());
        tabbedPane.setTabComponentAt(2, createTabLabel("Consultar"));

        tabbedPane.addTab("Modificar", crearPanelModificar());
        tabbedPane.setTabComponentAt(3, createTabLabel("Modificar"));

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel crearPanelAgregar() {
        JPanel panelAgregar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 5, 2, 5);

        Font inputFont = new Font("Arial", Font.PLAIN, 14);

        nombreFieldAgregar = new JTextField(20);
        nombreFieldAgregar.setFont(inputFont);
        dniFieldAgregar = new JTextField(20);
        dniFieldAgregar.setFont(inputFont);
        edadFieldAgregar = new JTextField(20);
        edadFieldAgregar.setFont(inputFont);
        sexoFieldAgregar = new JTextField(20);
        sexoFieldAgregar.setFont(inputFont);
        emailFieldAgregar = new JTextField(20);
        emailFieldAgregar.setFont(inputFont);
        infoIngresoFieldAgregar = new JTextField(20);
        infoIngresoFieldAgregar.setFont(inputFont);

        panelAgregar.add(new JLabel("Nombre:"), gbc);
        panelAgregar.add(nombreFieldAgregar, gbc);
        panelAgregar.add(new JLabel("DNI:"), gbc);
        panelAgregar.add(dniFieldAgregar, gbc);
        panelAgregar.add(new JLabel("Edad:"), gbc);
        panelAgregar.add(edadFieldAgregar, gbc);
        panelAgregar.add(new JLabel("Sexo:"), gbc);
        panelAgregar.add(sexoFieldAgregar, gbc);
        panelAgregar.add(new JLabel("Email:"), gbc);
        panelAgregar.add(emailFieldAgregar, gbc);
        panelAgregar.add(new JLabel("Info Ingreso:"), gbc);
        panelAgregar.add(infoIngresoFieldAgregar, gbc);

        gbc.gridy += 15;
        gbc.insets = new Insets(10, 5, 2, 5);
        JButton addButton = createButton("Agregar", e -> agregarArrendatario());
        panelAgregar.add(addButton, gbc);

        return panelAgregar;
    }

    private JPanel crearPanelEliminar() {
        JPanel panelEliminar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 4, 2, 4);

        Font inputFont = new Font("Arial", Font.PLAIN, 14);

        dniFieldEliminar = new JTextField(20);
        dniFieldEliminar.setFont(inputFont);

        panelEliminar.add(new JLabel("DNI:"), gbc);
        panelEliminar.add(dniFieldEliminar, gbc);

        gbc.gridy += 10;
        gbc.insets = new Insets(10, 5, 2, 5);
        JButton deleteButton = createButton("Eliminar", e -> eliminarArrendatario());
        panelEliminar.add(deleteButton, gbc);

        return panelEliminar;
    }

    private JPanel crearPanelConsultar() {
        JPanel panelConsultar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 4, 2, 4);

        Font inputFont = new Font("Arial", Font.PLAIN, 14);

        dniFieldConsultar = new JTextField(20);
        dniFieldConsultar.setFont(inputFont);

        panelConsultar.add(new JLabel("DNI:"), gbc);
        panelConsultar.add(dniFieldConsultar, gbc);

        gbc.gridy += 10;
        gbc.insets = new Insets(10, 5, 2, 5);
        JButton consultButton = createButton("Consultar", e -> consultarArrendatario());
        panelConsultar.add(consultButton, gbc);

        return panelConsultar;
    }

    private JPanel crearPanelModificar() {
        JPanel panelModificar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 4, 2, 4);

        Font inputFont = new Font("Arial", Font.PLAIN, 14);

        dniFieldModificar = new JTextField(20);
        dniFieldModificar.setFont(inputFont);
        nombreFieldModificar = new JTextField(20);
        nombreFieldModificar.setFont(inputFont);
        edadFieldModificar = new JTextField(20);
        edadFieldModificar.setFont(inputFont);
        sexoFieldModificar = new JTextField(20);
        sexoFieldModificar.setFont(inputFont);
        emailFieldModificar = new JTextField(20);
        emailFieldModificar.setFont(inputFont);
        infoIngresoFieldModificar = new JTextField(20);
        infoIngresoFieldModificar.setFont(inputFont);

        panelModificar.add(new JLabel("DNI:"), gbc);
        panelModificar.add(dniFieldModificar, gbc);
        panelModificar.add(new JLabel("Nombre:"), gbc);
        panelModificar.add(nombreFieldModificar, gbc);
        panelModificar.add(new JLabel("Edad:"), gbc);
        panelModificar.add(edadFieldModificar, gbc);
        panelModificar.add(new JLabel("Sexo:"), gbc);
        panelModificar.add(sexoFieldModificar, gbc);
        panelModificar.add(new JLabel("Email:"), gbc);
        panelModificar.add(emailFieldModificar, gbc);
        panelModificar.add(new JLabel("Info Ingreso:"), gbc);
        panelModificar.add(infoIngresoFieldModificar, gbc);

        gbc.gridy += 15;
        gbc.insets = new Insets(10, 5, 2, 5);
        JButton updateButton = createButton("Modificar", e -> modificarArrendatario());
        panelModificar.add(updateButton, gbc);

        return panelModificar;
    }

    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    private void agregarArrendatario() {
        if (validarCamposAgregar()) {
            java.util.Date utilDate = new java.util.Date();
            String resultado = arrendatarioService.agregarArrendatario(
                    dniFieldAgregar.getText(),
                    nombreFieldAgregar.getText(),
                    Integer.parseInt(edadFieldAgregar.getText()),
                    sexoFieldAgregar.getText(),
                    emailFieldAgregar.getText(),
                    infoIngresoFieldAgregar.getText(),
                    new java.sql.Date(utilDate.getTime()));
            mostrarMensaje(resultado);
        }
    }

    private void eliminarArrendatario() {
        if (validarCampoDNI(dniFieldEliminar)) {
            String resultado = arrendatarioService.eliminarArrendatario(dniFieldEliminar.getText());
            mostrarMensaje(resultado);
        }
    }

    private void consultarArrendatario() {
        if (validarCampoDNI(dniFieldConsultar)) {
            String resultado = arrendatarioService.consultarArrendatario(dniFieldConsultar.getText());
            mostrarMensaje(resultado);
        }
    }

    private void modificarArrendatario() {
        if (validarCampoDNI(dniFieldModificar) && validarCamposModificar()) {
            String resultado = arrendatarioService.modificarArrendatario(
                    dniFieldModificar.getText(),
                    nombreFieldModificar.getText(),
                    Integer.parseInt(edadFieldModificar.getText()),
                    sexoFieldModificar.getText(),
                    emailFieldModificar.getText(),
                    infoIngresoFieldModificar.getText());
            mostrarMensaje(resultado);
        }
    }

    private boolean validarCampoDNI(JTextField dniField) {
        if (dniField.getText().trim().isEmpty()) {
            mostrarMensaje("El campo DNI no puede estar vacío.");
            return false;
        }
        return true;
    }

    private boolean validarCamposAgregar() {
        if (nombreFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Nombre no puede estar vacío.");
            return false;
        }

        if (!validarCampoDNI(dniFieldAgregar)) {
            return false;
        }

        if (edadFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Edad no puede estar vacío.");
            return false;
        }

        try {
            int edad = Integer.parseInt(edadFieldAgregar.getText());
            if (edad <= 0) {
                mostrarMensaje("La edad debe ser un número positivo.");
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("La Edad debe ser un número.");
            return false;
        }

        if (sexoFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Sexo no puede estar vacío.");
            return false;
        }

        if (emailFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Email no puede estar vacío.");
            return false;
        }

        if (infoIngresoFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Info Ingreso no puede estar vacío.");
            return false;
        }

        return true;
    }

    private boolean validarCamposModificar() {
        if (!validarCampoDNI(dniFieldModificar)) {
            return false;
        }

        if (nombreFieldModificar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Nombre no puede estar vacío.");
            return false;
        }

        if (edadFieldModificar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Edad no puede estar vacío.");
            return false;
        }

        try {
            int edad = Integer.parseInt(edadFieldModificar.getText());
            if (edad <= 0) {
                mostrarMensaje("La edad debe ser un número positivo.");
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("La Edad debe ser un número.");
            return false;
        }

        if (sexoFieldModificar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Sexo no puede estar vacío.");
            return false;
        }

        if (emailFieldModificar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Email no puede estar vacío.");
            return false;
        }

        if (infoIngresoFieldModificar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Info Ingreso no puede estar vacío.");
            return false;
        }

        return true;
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
