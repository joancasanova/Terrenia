package terrenia.gui;

import javax.swing.*;
import java.awt.*;

import terrenia.service.*;

public class MainFrame extends JFrame {

    // Definir los servicios que se utilizarán en la GUI
    private TerrenoService terrenoService;
    private ParcelaService parcelaService;
    private ArrendatarioService arrendatarioService;
    private AlquilerService alquilerService;

    // Definir los paneles para cada entidad
    private TerrenoPanel terrenoPanel;
    private ParcelaPanel parcelaPanel;
    private ArrendatarioPanel arrendatarioPanel;
    private AlquilerPanel alquilerPanel;

    public MainFrame() {
        // Inicializar los servicios
        terrenoService = new TerrenoService();
        parcelaService = new ParcelaService();
        arrendatarioService = new ArrendatarioService();
        alquilerService = new AlquilerService();

        // Configurar la ventana principal
        setTitle("Gestión de Terrenia");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear y agregar la barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        JMenuItem terrenoItem = new JMenuItem("Terrenos");
        JMenuItem parcelaItem = new JMenuItem("Parcelas");
        JMenuItem arrendatarioItem = new JMenuItem("Arrendatarios");
        JMenuItem alquilerItem = new JMenuItem("Alquileres");
        
        // Agregar acciones a los items del menú
        terrenoItem.addActionListener(e -> mostrarPanel(terrenoPanel));
        parcelaItem.addActionListener(e -> mostrarPanel(parcelaPanel));
        arrendatarioItem.addActionListener(e -> mostrarPanel(arrendatarioPanel));
        alquilerItem.addActionListener(e -> mostrarPanel(alquilerPanel));

        // Agregar items al menú
        menu.add(terrenoItem);
        menu.add(parcelaItem);
        menu.add(arrendatarioItem);
        menu.add(alquilerItem);
        
        // Agregar menú a la barra de menú
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Inicializar los paneles
        terrenoPanel = new TerrenoPanel(terrenoService);
        parcelaPanel = new ParcelaPanel(parcelaService);
        arrendatarioPanel = new ArrendatarioPanel(arrendatarioService);
        alquilerPanel = new AlquilerPanel(alquilerService);

        // Añadir el panel inicial al contenedor principal
        add(terrenoPanel, BorderLayout.CENTER);

        // Hacer visible la ventana principal
        setVisible(true);
    }

    // Método para mostrar el panel seleccionado
    private void mostrarPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().validate();
        getContentPane().repaint();
    }

    public static void main(String[] args) {
        // Ejecutar la GUI en el Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
