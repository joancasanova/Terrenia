package terrenia;

import terrenia.gui.MainFrame;

import java.io.IOException;

import javax.swing.SwingUtilities;

public class Main {
    
    public static void main(String[] args) {
        // Ejecutar la GUI en el Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crea e inicia la ventana principal de la aplicación
                try {
                    new MainFrame();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
