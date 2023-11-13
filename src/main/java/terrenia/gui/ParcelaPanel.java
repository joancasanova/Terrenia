package terrenia.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import terrenia.service.ParcelaService;

public class ParcelaPanel extends JPanel {

    private ParcelaService parcelaService;
    private JTextField idParcelaField, idTerrenoField, tamanoField, limitesField, ubicacionField;
    private JCheckBox alquiladaCheckBox;
    private JButton addButton, getButton, updateButton, deleteButton;

    public ParcelaPanel(ParcelaService parcelaService) {
        this.parcelaService = parcelaService;
        setLayout(new BorderLayout());

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        inputPanel.add(new JLabel("ID Parcela:"));
        idParcelaField = new JTextField();
        inputPanel.add(idParcelaField);

        inputPanel.add(new JLabel("ID Terreno:"));
        idTerrenoField = new JTextField();
        inputPanel.add(idTerrenoField);

        inputPanel.add(new JLabel("Tamaño:"));
        tamanoField = new JTextField();
        inputPanel.add(tamanoField);

        inputPanel.add(new JLabel("Límites:"));
        limitesField = new JTextField();
        inputPanel.add(limitesField);

        inputPanel.add(new JLabel("Ubicación:"));
        ubicacionField = new JTextField();
        inputPanel.add(ubicacionField);

        inputPanel.add(new JLabel("¿Alquilada?"));
        alquiladaCheckBox = new JCheckBox();
        inputPanel.add(alquiladaCheckBox);

        // Panel de botones
        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarParcela();
            }
        });
        buttonPanel.add(addButton);

        getButton = new JButton("Obtener");
        getButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obtenerParcela();
            }
        });
        buttonPanel.add(getButton);
        /* 
        updateButton = new JButton("Actualizar");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarParcela();
            }
        });
        buttonPanel.add(updateButton); */

        deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarParcela();
            }
        });
        buttonPanel.add(deleteButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void agregarParcela() {
        String resultado = parcelaService.agregarParcela(
            Integer.parseInt(idTerrenoField.getText()),
            Double.parseDouble(tamanoField.getText()),
            limitesField.getText(),
            ubicacionField.getText(),
            alquiladaCheckBox.isSelected()
        );
        mostrarMensaje(resultado);
    }

    private void obtenerParcela() {
        String resultado = parcelaService.obtenerParcelaPorId(
            Integer.parseInt(idParcelaField.getText())
        );
        mostrarMensaje(resultado);
    }
    /* 
    private void actualizarParcela() {
        String resultado = parcelaService.actualizarParcela(
            Integer.parseInt(idParcelaField.getText()),
            Double.parseDouble(tamanoField.getText()),
            limitesField.getText(),
            ubicacionField.getText(),
            alquiladaCheckBox.isSelected()
        );
        mostrarMensaje(resultado);
    } */

    private void eliminarParcela() {
        String resultado = parcelaService.eliminarParcela(
            Integer.parseInt(idParcelaField.getText())
        );
        mostrarMensaje(resultado);
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
