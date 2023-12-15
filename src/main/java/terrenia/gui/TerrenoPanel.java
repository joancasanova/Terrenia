package terrenia.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import terrenia.service.TerrenoService;

public class TerrenoPanel extends JPanel {

    private TerrenoService terrenoService;
    private JTabbedPane tabbedPane;

    private JTextField tamañoFieldAgregar, tipoDeTerrenoFieldAgregar, ubicacionFieldAgregar;
    private JTextField idFieldEliminar;
    private JTextField idFieldConsultar;
    private JTextField idFieldModificar, tamañoFieldModificar, tipoDeTerrenoFieldModificar, ubicacionFieldModificar;

    public TerrenoPanel(TerrenoService terrenoService) {
        this.terrenoService = terrenoService;
        setLayout(new BorderLayout());
        initTabbedPane();
    }

    // Método para crear una etiqueta de pestaña con padding
    private JLabel createTabLabel(String title) {
        JLabel label = new JLabel(title);
        Font tabFont = new Font("Arial", Font.BOLD, 16); // Aumenta el tamaño de la fuente
        label.setFont(tabFont);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Agrega padding arriba y abajo
        return label;
    }

    private void initTabbedPane() {
        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        // Envuelve el panelAgregar con un margen
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
        gbc.insets = new Insets(2, 5, 2, 5); // Reducir el espacio entre componentes

        Font inputFont = new Font("Arial", Font.PLAIN, 14); // Fuente más grande para los inputs

        tamañoFieldAgregar = new JTextField(20); // Aumentar el número de columnas
        tamañoFieldAgregar.setFont(inputFont); // Aplicar la nueva fuente
        tipoDeTerrenoFieldAgregar = new JTextField(20);
        tipoDeTerrenoFieldAgregar.setFont(inputFont);
        ubicacionFieldAgregar = new JTextField(20);
        ubicacionFieldAgregar.setFont(inputFont);

        panelAgregar.add(new JLabel("Tamaño:"), gbc);
        panelAgregar.add(tamañoFieldAgregar, gbc);
        panelAgregar.add(new JLabel("Tipo de Terreno:"), gbc);
        panelAgregar.add(tipoDeTerrenoFieldAgregar, gbc);
        panelAgregar.add(new JLabel("Ubicación:"), gbc);
        panelAgregar.add(ubicacionFieldAgregar, gbc);

        // Configuración para el botón
        gbc.gridy += 10; // Incrementar gbc.gridy para bajar el botón
        gbc.insets = new Insets(10, 5, 2, 5); // Aumentar el espacio superior del botón
        JButton addButton = createButton("Agregar", e -> agregarTerreno());
        panelAgregar.add(addButton, gbc);

        return panelAgregar;
    }

    private JPanel crearPanelModificar() {
        JPanel panelModificar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 4, 2, 4); // Reducir el espacio entre componentes

        Font inputFont = new Font("Arial", Font.PLAIN, 14); // Fuente más grande para los inputs

        idFieldModificar = new JTextField(20); // Aumentar el número de columnas
        idFieldModificar.setFont(inputFont); // Aplicar la nueva fuente
        tamañoFieldModificar = new JTextField(20); // Aumentar el número de columnas
        tamañoFieldModificar.setFont(inputFont); // Aplicar la nueva fuente
        tipoDeTerrenoFieldModificar = new JTextField(20);
        tipoDeTerrenoFieldModificar.setFont(inputFont);
        ubicacionFieldModificar = new JTextField(20);
        ubicacionFieldModificar.setFont(inputFont);

        panelModificar.add(new JLabel("ID:"), gbc);
        panelModificar.add(idFieldModificar, gbc);
        panelModificar.add(new JLabel("Tamaño:"), gbc);
        panelModificar.add(tamañoFieldModificar, gbc);
        panelModificar.add(new JLabel("Tipo de Terreno:"), gbc);
        panelModificar.add(tipoDeTerrenoFieldModificar, gbc);
        panelModificar.add(new JLabel("Ubicación:"), gbc);
        panelModificar.add(ubicacionFieldModificar, gbc);

        // Configuración para el botón
        gbc.gridy += 10; // Incrementar gbc.gridy para bajar el botón
        gbc.insets = new Insets(10, 5, 2, 5); // Aumentar el espacio superior del botón
        JButton updateButton = createButton("Modificar", e -> modificarTerreno());
        panelModificar.add(updateButton, gbc);

        return panelModificar;
    }

    private JPanel crearPanelEliminar() {
        JPanel panelEliminar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 4, 2, 4); // Reducir el espacio entre componentes

        Font inputFont = new Font("Arial", Font.PLAIN, 14); // Fuente más grande para los inputs

        idFieldEliminar = new JTextField(20); // Aumentar el número de columnas
        idFieldEliminar.setFont(inputFont); // Aplicar la nueva fuente

        panelEliminar.add(new JLabel("ID:"), gbc);
        panelEliminar.add(idFieldEliminar, gbc);

        // Configuración para el botón
        gbc.gridy += 10; // Incrementar gbc.gridy para bajar el botón
        gbc.insets = new Insets(10, 5, 2, 5); // Aumentar el espacio superior del botón
        JButton deleteButton = createButton("Eliminar", e -> eliminarTerreno());
        panelEliminar.add(deleteButton, gbc);

        return panelEliminar;
    }

    private JPanel crearPanelConsultar() {
        JPanel panelConsultar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 4, 2, 4); // Reducir el espacio entre componentes

        Font inputFont = new Font("Arial", Font.PLAIN, 14); // Fuente más grande para los inputs

        idFieldConsultar = new JTextField(20); // Aumentar el número de columnas
        idFieldConsultar.setFont(inputFont); // Aplicar la nueva fuente

        panelConsultar.add(new JLabel("ID:"), gbc);
        panelConsultar.add(idFieldConsultar, gbc);

        // Configuración para el botón
        gbc.gridy += 10; // Incrementar gbc.gridy para bajar el botón
        gbc.insets = new Insets(10, 5, 2, 5); // Aumentar el espacio superior del botón
        JButton consultButton = createButton("Consultar", e -> consultarTerreno());
        panelConsultar.add(consultButton, gbc);

        return panelConsultar;
    }

    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    private void agregarTerreno() {
        if (validarCamposAgregar()) {
            String resultado = terrenoService.agregarTerreno(
                    Double.parseDouble(tamañoFieldAgregar.getText()),
                    tipoDeTerrenoFieldAgregar.getText(),
                    ubicacionFieldAgregar.getText());
            mostrarMensaje(resultado);
        }
    }

    private void eliminarTerreno() {
        if (validarCampoId(idFieldEliminar)) {
            String resultado = terrenoService.eliminarTerreno(
                    Integer.parseInt(idFieldEliminar.getText()));
            mostrarMensaje(resultado);
        }
    }

    private void consultarTerreno() {
        if (validarCampoId(idFieldConsultar)) {
            String resultado = terrenoService.consultarTerreno(
                    Integer.parseInt(idFieldConsultar.getText()));
            mostrarMensaje(resultado);
        }
    }

    private void modificarTerreno() {
        if (validarCampoId(idFieldModificar) && validarCamposModificar()) {
            String resultado = terrenoService.modificarTerreno(
                    Integer.parseInt(idFieldModificar.getText()),
                    Double.parseDouble(tamañoFieldModificar.getText()),
                    tipoDeTerrenoFieldModificar.getText(),
                    ubicacionFieldModificar.getText());
            mostrarMensaje(resultado);
        }
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

    private boolean validarCamposAgregar() {

        // Validación del campo Tamaño
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

        // Validación del campo Tipo de Terreno
        if (tipoDeTerrenoFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Tipo de Terreno no puede estar vacío.");
            return false;
        }

        // Validación del campo Ubicación
        if (ubicacionFieldAgregar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Ubicación no puede estar vacío.");
            return false;
        }

        return true;
    }

    private boolean validarCamposModificar() {
        // Las mismas validaciones que en agregar, pero para los campos de modificar
        if (idFieldModificar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo ID no puede estar vacío.");
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

        if (tipoDeTerrenoFieldModificar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Tipo de Terreno no puede estar vacío.");
            return false;
        }

        if (ubicacionFieldModificar.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Ubicación no puede estar vacío.");
            return false;
        }

        return true;
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
