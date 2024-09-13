public class Main {
    public static void main(String[] args) {
        // Usamos el método invokeLater para asegurarnos de que la GUI se ejecute en el hilo de eventos de Swing
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Inicializamos la GUI de la calculadora y la hacemos visible
                new CalculadoraGUI().setVisible(true);
            }
        });
    }
}
