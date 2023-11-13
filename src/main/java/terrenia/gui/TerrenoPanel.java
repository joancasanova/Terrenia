package terrenia.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import terrenia.service.TerrenoService;

public class TerrenoPanel extends JPanel {

    private TerrenoService terrenoService;
    private JTextField idField, tamañoField, tipoDeTerrenoField, ubicacionField;
    private JButton addButton, deleteButton, consultButton;

    public TerrenoPanel(TerrenoService terrenoService) {
        this.terrenoService = terrenoService;
        setLayout(new BorderLayout());

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Tamaño:"));
        tamañoField = new JTextField();
        inputPanel.add(tamañoField);

        inputPanel.add(new JLabel("Tipo de Terreno:"));
        tipoDeTerrenoField = new JTextField();
        inputPanel.add(tipoDeTerrenoField);

        inputPanel.add(new JLabel("Ubicación:"));
        ubicacionField = new JTextField();
        inputPanel.add(ubicacionField);

        // Panel de botones
        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarTerreno();
            }
        });
        buttonPanel.add(addButton);

        deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarTerreno();
            }
        });
        buttonPanel.add(deleteButton);

        consultButton = new JButton("Consultar");
        consultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarTerreno();
            }
        });
        buttonPanel.add(consultButton);

        /*         
        updateButton = new JButton("Modificar");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarTerreno();
            }
        });
        buttonPanel.add(updateButton); */

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void agregarTerreno() {
        String resultado = terrenoService.agregarTerreno(
            Double.parseDouble(tamañoField.getText()),
            tipoDeTerrenoField.getText(),
            ubicacionField.getText()
        );
        mostrarMensaje(resultado);
    }

    private void eliminarTerreno() {
        String resultado = terrenoService.eliminarTerreno(
            Integer.parseInt(idField.getText())
        );
        mostrarMensaje(resultado);
    }

    private void consultarTerreno() {
        String resultado = terrenoService.consultarTerreno(
            Integer.parseInt(idField.getText())
        );
        mostrarMensaje(resultado);
    }

    /*     
    private void modificarTerreno() {
        String resultado = terrenoService.modificarTerreno(
            Integer.parseInt(idField.getText()),
            Double.parseDouble(tamañoField.getText()),
            tipoDeTerrenoField.getText(),
            ubicacionField.getText()
        );
        mostrarMensaje(resultado);
    } */

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
