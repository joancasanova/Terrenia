package terrenia.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import terrenia.service.AlquilerService;
import terrenia.service.ReciboService;

public class AlquilerPanel extends JPanel {

    private AlquilerService alquilerService;
    private ReciboService reciboService;

    private JTextField idParcelaFieldAgregar, idArrendatarioFieldAgregar, fechaInicioFieldAgregar, importeFieldAgregar,
            periodoFieldAgregar;
    private JTextField idParcelaFieldDesalquilar, idArrendatarioFieldDesalquilar;

    public AlquilerPanel(AlquilerService alquilerService, ReciboService reciboService) {
        this.alquilerService = alquilerService;
        this.reciboService = reciboService;
        setLayout(new BorderLayout());
        initTabbedPane();
    }

    private void initTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        tabbedPane.addTab("Alquilar", crearPanelAlquilar());
        tabbedPane.setTabComponentAt(0, createTabLabel("Alquilar"));

        tabbedPane.addTab("Desalquilar", crearPanelDesalquilar());
        tabbedPane.setTabComponentAt(1, createTabLabel("Desalquilar"));

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JLabel createTabLabel(String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return label;
    }

    private JPanel crearPanelAlquilar() {
        JPanel panelAgregar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 5, 2, 5);

        Font inputFont = new Font("Arial", Font.PLAIN, 14);

        idParcelaFieldAgregar = new JTextField(20);
        idParcelaFieldAgregar.setFont(inputFont);
        idArrendatarioFieldAgregar = new JTextField(20);
        idArrendatarioFieldAgregar.setFont(inputFont);
        fechaInicioFieldAgregar = new JTextField(20);
        fechaInicioFieldAgregar.setFont(inputFont);
        importeFieldAgregar = new JTextField(20);
        importeFieldAgregar.setFont(inputFont);
        periodoFieldAgregar = new JTextField(20);
        periodoFieldAgregar.setFont(inputFont);

        panelAgregar.add(new JLabel("ID Parcela:"), gbc);
        panelAgregar.add(idParcelaFieldAgregar, gbc);
        panelAgregar.add(new JLabel("ID Arrendatario:"), gbc);
        panelAgregar.add(idArrendatarioFieldAgregar, gbc);
        panelAgregar.add(new JLabel("Fecha Inicio:"), gbc);
        panelAgregar.add(fechaInicioFieldAgregar, gbc);
        panelAgregar.add(new JLabel("Importe:"), gbc);
        panelAgregar.add(importeFieldAgregar, gbc);
        panelAgregar.add(new JLabel("Periodo:"), gbc);
        panelAgregar.add(periodoFieldAgregar, gbc);

        gbc.gridy += 15;
        gbc.insets = new Insets(10, 5, 2, 5);
        JButton addButton = createButton("Alquilar", e -> registrarAlquiler());
        panelAgregar.add(addButton, gbc);

        return panelAgregar;
    }

    private JPanel crearPanelDesalquilar() {
        JPanel panelDesalquilar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 4, 2, 4);

        Font inputFont = new Font("Arial", Font.PLAIN, 14);

        idParcelaFieldDesalquilar = new JTextField(20);
        idParcelaFieldDesalquilar.setFont(inputFont);
        idArrendatarioFieldDesalquilar = new JTextField(20);
        idArrendatarioFieldDesalquilar.setFont(inputFont);

        panelDesalquilar.add(new JLabel("ID Parcela:"), gbc);
        panelDesalquilar.add(idParcelaFieldDesalquilar, gbc);
        panelDesalquilar.add(new JLabel("ID Arrendatario:"), gbc);
        panelDesalquilar.add(idArrendatarioFieldDesalquilar, gbc);

        gbc.gridy += 15;
        gbc.insets = new Insets(10, 5, 2, 5);
        JButton desalquilarButton = createButton("Desalquilar", e -> desalquilarParcela());
        panelDesalquilar.add(desalquilarButton, gbc);

        return panelDesalquilar;
    }

    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    private void registrarAlquiler() {
        if (validarCamposAgregarAlquiler()) {
            // Convertir y validar datos según sea necesario
            Integer idParcela = Integer.parseInt(idParcelaFieldAgregar.getText());
            String idArrendatario = idArrendatarioFieldAgregar.getText();
            Date fechaInicio = convertirFecha(fechaInicioFieldAgregar.getText());
            BigDecimal importe = new BigDecimal(importeFieldAgregar.getText());
            int periodo = Integer.parseInt(periodoFieldAgregar.getText());

            String resultado = alquilerService.registrarAlquiler(idParcela, idArrendatario, fechaInicio, importe,
                    periodo);
            mostrarMensaje(resultado);

            if (!resultado.contains("Error")) {
                mostrarMensaje(reciboService.generarRecibo(idParcela, idArrendatario, fechaInicio, importe));
            }
        }
    }

    private void desalquilarParcela() {
        if (validarCampoId(idParcelaFieldDesalquilar)) {
            int idParcela = Integer.parseInt(idParcelaFieldDesalquilar.getText());
            String idArrendatario = idArrendatarioFieldDesalquilar.getText();
            String resultado = alquilerService.finalizarAlquiler(idParcela, idArrendatario);
            mostrarMensaje(resultado);
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private boolean validarCamposAgregarAlquiler() {
        // Validar idParcela
        if (!validarCampoNumerico(idParcelaFieldAgregar, "ID Parcela")) {
            return false;
        }

        // Validar idArrendatario
        if (idArrendatarioFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo ID Arrendatario no puede estar vacío.");
            return false;
        }

        // Validar fechaInicio
        if (fechaInicioFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Fecha Inicio no puede estar vacío.");
            return false;
        }
        if (convertirFecha(fechaInicioFieldAgregar.getText()) == null) {
            return false;
        }

        // Validar importe
        if (!validarCampoNumerico(importeFieldAgregar, "Importe")) {
            return false;
        }

        // Validar periodo
        if (!validarCampoNumerico(periodoFieldAgregar, "Periodo")) {
            return false;
        }

        return true;
    }

    private boolean validarCampoId(JTextField idField) {
        if (idField.getText().trim().isEmpty()) {
            mostrarMensaje("El campo ID no puede estar vacío.");
            return false;
        }
        try {
            Integer.parseInt(idField.getText());
            return true;
        } catch (NumberFormatException e) {
            mostrarMensaje("El ID debe ser un número entero.");
            return false;
        }
    }

    private Date convertirFecha(String fechaTexto) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaInicio = null;
        try {
            java.util.Date dateStr = formato.parse(fechaTexto);
            fechaInicio = new Date(dateStr.getTime());
        } catch (ParseException e) {
            mostrarMensaje("Formato de fecha no válido. Use dd/MM/yyyy.");
        }
        return fechaInicio;
    }

    private boolean validarCampoNumerico(JTextField textField, String nombreCampo) {
        if (textField.getText().trim().isEmpty()) {
            mostrarMensaje("El campo " + nombreCampo + " no puede estar vacío.");
            return false;
        }
        try {
            Double.parseDouble(textField.getText());
            return true;
        } catch (NumberFormatException e) {
            mostrarMensaje("El campo " + nombreCampo + " debe ser numérico.");
            return false;
        }
    }
}
