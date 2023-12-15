package terrenia.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import terrenia.service.ParcelaService;

public class ParcelaPanel extends JPanel {

    private ParcelaService parcelaService;

    private JTextField tamañoFieldAgregar, limitesFieldAgregar, ubicacionFieldAgregar,
            idTerrenoFieldAgregar, alquiladaFieldAgregar;
    private JTextField idFieldEliminar;
    private JTextField idFieldConsultar;
    private JTextField idFieldModificar, tamañoFieldModificar, limitesFieldModificar, ubicacionFieldModificar,
            alquiladaFieldModificar;

    public ParcelaPanel(ParcelaService parcelaService) {
        this.parcelaService = parcelaService;
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
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

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
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 5, 2, 5);

        Font inputFont = new Font("Arial", Font.PLAIN, 14);

        tamañoFieldAgregar = new JTextField(20);
        tamañoFieldAgregar.setFont(inputFont);
        limitesFieldAgregar = new JTextField(20);
        limitesFieldAgregar.setFont(inputFont);
        ubicacionFieldAgregar = new JTextField(20);
        ubicacionFieldAgregar.setFont(inputFont);
        idTerrenoFieldAgregar = new JTextField(20);
        idTerrenoFieldAgregar.setFont(inputFont);
        alquiladaFieldAgregar = new JTextField(20);
        alquiladaFieldAgregar.setFont(inputFont);

        panelAgregar.add(new JLabel("Tamaño:"), gbc);
        gbc.gridy++;
        panelAgregar.add(tamañoFieldAgregar, gbc);
        gbc.gridy++;
        panelAgregar.add(new JLabel("Límites:"), gbc);
        gbc.gridy++;
        panelAgregar.add(limitesFieldAgregar, gbc);
        gbc.gridy++;
        panelAgregar.add(new JLabel("Ubicación:"), gbc);
        gbc.gridy++;
        panelAgregar.add(ubicacionFieldAgregar, gbc);
        gbc.gridy++;
        panelAgregar.add(new JLabel("ID Terreno:"), gbc);
        gbc.gridy++;
        panelAgregar.add(idTerrenoFieldAgregar, gbc);
        gbc.gridy++;
        panelAgregar.add(new JLabel("Alquilada (0 o 1):"), gbc);
        gbc.gridy++;
        panelAgregar.add(alquiladaFieldAgregar, gbc);
        gbc.gridy++;

        JButton addButton = createButton("Agregar", e -> agregarParcela());
        panelAgregar.add(addButton, gbc);

        return panelAgregar;
    }
    private JPanel crearPanelEliminar() {
        JPanel panelEliminar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 5, 2, 5);
    
        Font inputFont = new Font("Arial", Font.PLAIN, 14);
    
        idFieldEliminar = new JTextField(20);
        idFieldEliminar.setFont(inputFont);
    
        panelEliminar.add(new JLabel("ID Parcela:"), gbc);
        gbc.gridy++;
        panelEliminar.add(idFieldEliminar, gbc);
        gbc.gridy++;
    
        JButton deleteButton = createButton("Eliminar", e -> eliminarParcela());
        panelEliminar.add(deleteButton, gbc);
    
        return panelEliminar;
    }
    
    private JPanel crearPanelConsultar() {
        JPanel panelConsultar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 5, 2, 5);
    
        Font inputFont = new Font("Arial", Font.PLAIN, 14);
    
        idFieldConsultar = new JTextField(20);
        idFieldConsultar.setFont(inputFont);
    
        panelConsultar.add(new JLabel("ID Parcela:"), gbc);
        gbc.gridy++;
        panelConsultar.add(idFieldConsultar, gbc);
        gbc.gridy++;
    
        JButton consultButton = createButton("Consultar", e -> consultarParcela());
        panelConsultar.add(consultButton, gbc);
    
        return panelConsultar;
    }
    
    private JPanel crearPanelModificar() {
        JPanel panelModificar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 5, 2, 5);
    
        Font inputFont = new Font("Arial", Font.PLAIN, 14);
    
        idFieldModificar = new JTextField(20);
        idFieldModificar.setFont(inputFont);
        tamañoFieldModificar = new JTextField(20);
        tamañoFieldModificar.setFont(inputFont);
        limitesFieldModificar = new JTextField(20);
        limitesFieldModificar.setFont(inputFont);
        ubicacionFieldModificar = new JTextField(20);
        ubicacionFieldModificar.setFont(inputFont);
        alquiladaFieldModificar = new JTextField(20);
        alquiladaFieldModificar.setFont(inputFont);
    
        panelModificar.add(new JLabel("ID Parcela:"), gbc);
        gbc.gridy++;
        panelModificar.add(idFieldModificar, gbc);
        gbc.gridy++;
        panelModificar.add(new JLabel("Tamaño:"), gbc);
        gbc.gridy++;
        panelModificar.add(tamañoFieldModificar, gbc);
        gbc.gridy++;
        panelModificar.add(new JLabel("Límites:"), gbc);
        gbc.gridy++;
        panelModificar.add(limitesFieldModificar, gbc);
        gbc.gridy++;
        panelModificar.add(new JLabel("Ubicación:"), gbc);
        gbc.gridy++;
        panelModificar.add(ubicacionFieldModificar, gbc);
        gbc.gridy++;
        panelModificar.add(new JLabel("Alquilada (0 o 1):"), gbc);
        gbc.gridy++;
        panelModificar.add(alquiladaFieldModificar, gbc);
        gbc.gridy++;
    
        JButton updateButton = createButton("Modificar", e -> modificarParcela());
        panelModificar.add(updateButton, gbc);
    
        return panelModificar;
    }
    

    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    private void agregarParcela() {
        if (validarCamposAgregar()) {
            String resultado = parcelaService.agregarParcela(
                    Integer.parseInt(idTerrenoFieldAgregar.getText()),
                    Double.parseDouble(tamañoFieldAgregar.getText()),
                    limitesFieldAgregar.getText(),
                    ubicacionFieldAgregar.getText(),
                    Boolean.parseBoolean(alquiladaFieldAgregar.getText()));
            mostrarMensaje(resultado);
        }
    }

    // Método para eliminar una parcela
    private void eliminarParcela() {
        if (validarCampoId(idFieldEliminar)) {
            String resultado = parcelaService.eliminarParcela(
                    Integer.parseInt(idFieldEliminar.getText()));
            mostrarMensaje(resultado);
        }
    }

    // Método para consultar una parcela
    private void consultarParcela() {
        if (validarCampoId(idFieldConsultar)) {
            String resultado = parcelaService.obtenerParcelaPorId(
                    Integer.parseInt(idFieldConsultar.getText()));
            mostrarMensaje(resultado);
        }
    }

    // Método para modificar una parcela
    private void modificarParcela() {
        if (validarCamposModificar()) {
            String resultado = parcelaService.actualizarParcela(
                    Integer.parseInt(idFieldModificar.getText()),
                    Double.parseDouble(tamañoFieldModificar.getText()),
                    limitesFieldModificar.getText(),
                    ubicacionFieldModificar.getText(),
                    Boolean.parseBoolean(alquiladaFieldModificar.getText()));
            mostrarMensaje(resultado);
        }
    }

    // Método para validar que el campo ID no esté vacío y sea un número válido
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

    // Método para validar los campos al modificar una parcela
    private boolean validarCamposModificar() {
        if (!validarCampoId(idFieldModificar)) {
            return false;
        }
        if (tamañoFieldModificar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Tamaño no puede estar vacío.");
            return false;
        }
        try {
            double tamaño = Double.parseDouble(tamañoFieldModificar.getText());
            if (tamaño <= 0) {
                mostrarMensaje("El tamaño debe ser un número positivo.");
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("El Tamaño debe ser un número.");
            return false;
        }
        if (limitesFieldModificar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Límites no puede estar vacío.");
            return false;
        }
        if (ubicacionFieldModificar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Ubicación no puede estar vacío.");
            return false;
        }
        if (alquiladaFieldModificar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Alquilada no puede estar vacío.");
            return false;
        }
        if (!alquiladaFieldModificar.getText().equals("0") && !alquiladaFieldModificar.getText().equals("1")) {
            mostrarMensaje("El campo Alquilada debe ser 0 (no alquilada) o 1 (alquilada).");
            return false;
        }
        return true;
    }

    // Método para validar los campos al agregar una parcela
    private boolean validarCamposAgregar() {
        if (tamañoFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Tamaño no puede estar vacío.");
            return false;
        }
        try {
            double tamaño = Double.parseDouble(tamañoFieldAgregar.getText());
            if (tamaño <= 0) {
                mostrarMensaje("El tamaño debe ser un número positivo.");
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("El Tamaño debe ser un número.");
            return false;
        }
        if (limitesFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Límites no puede estar vacío.");
            return false;
        }
        if (ubicacionFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Ubicación no puede estar vacío.");
            return false;
        }
        if (idTerrenoFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo ID Terreno no puede estar vacío.");
            return false;
        }
        try {
            Integer.parseInt(idTerrenoFieldAgregar.getText());
        } catch (NumberFormatException e) {
            mostrarMensaje("El ID Terreno debe ser un número entero.");
            return false;
        }
        if (alquiladaFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Alquilada no puede estar vacío.");
            return false;
        }
        if (!alquiladaFieldAgregar.getText().equals("0") && !alquiladaFieldAgregar.getText().equals("1")) {
            mostrarMensaje("El campo Alquilada debe ser 0 (no alquilada) o 1 (alquilada).");
            return false;
        }
        return true;
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
