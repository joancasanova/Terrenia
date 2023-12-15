package terrenia.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import terrenia.service.*;

public class MainFrame extends JFrame {

    private TerrenoService terrenoService;
    private ParcelaService parcelaService;
    private ArrendatarioService arrendatarioService;
    private AlquilerService alquilerService;
    private ReciboService reciboService;

    private TerrenoPanel terrenoPanel;
    private ParcelaPanel parcelaPanel;
    private ArrendatarioPanel arrendatarioPanel;
    private AlquilerPanel alquilerPanel;

    private JTabbedPane tabbedPane;

    public MainFrame() throws IOException {
        setTitle("Gestión de Terrenia");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        inicializarServiciosYPaneles();
        configurarLayoutPrincipal();

        setVisible(true);
    }

    private void inicializarServiciosYPaneles() {
        terrenoService = new TerrenoService();
        parcelaService = new ParcelaService();
        arrendatarioService = new ArrendatarioService();
        alquilerService = new AlquilerService();
        reciboService = new ReciboService();

        terrenoPanel = new TerrenoPanel(terrenoService);
        parcelaPanel = new ParcelaPanel(parcelaService);
        arrendatarioPanel = new ArrendatarioPanel(arrendatarioService);
        alquilerPanel = new AlquilerPanel(alquilerService, reciboService);
    }

    private void configurarLayoutPrincipal() throws IOException {
        setLayout(new BorderLayout());

        inicializarTabbedPane();
        inicializarPanelLogo();
    }

    private void inicializarPanelLogo() throws IOException {
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        logoPanel.setBackground(new Color(240, 240, 240));

        try {
            BufferedImage logoImage = ImageIO.read(new File("src/main/java/terrenia/images/logo.png"));
            Image scaledImage = logoImage.getScaledInstance(700, 130, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoPanel.add(logoLabel);
        } catch (IOException e) {
            System.err.println("Error al cargar el logo: " + e.getMessage());
        }

        add(logoPanel, BorderLayout.NORTH);
    }

    private void inicializarTabbedPane() {
        tabbedPane = new JTabbedPane();

        // Agregar pestañas
        tabbedPane.addTab("Terrenos", terrenoPanel);
        tabbedPane.addTab("Parcelas", parcelaPanel);
        tabbedPane.addTab("Arrendatarios", arrendatarioPanel);
        tabbedPane.addTab("Alquileres", alquilerPanel);

        // Configuración de la fuente y tamaño de las pestañas
        Font tabFont = new Font("Arial", Font.BOLD, 17); // Aumenta el tamaño y usa negrita
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            JLabel lbl = new JLabel(tabbedPane.getTitleAt(i));
            lbl.setFont(tabFont);
            lbl.setBorder(new EmptyBorder(10, 0, 10, 0)); // Ajuste de márgenes para más espacio
            tabbedPane.setTabComponentAt(i, lbl);
        }
        // Colores de las pestañas
        tabbedPane.setBackground(new Color(225, 225, 225));
        tabbedPane.setForeground(Color.DARK_GRAY);

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MainFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
