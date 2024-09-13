import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que implementa la interfaz gráfica para una calculadora, permitiendo al usuario
 * ingresar números, seleccionar operaciones y ver los resultados.
 * Interactúa con {@link CalculadoraController} para realizar las operaciones.
 */
public class CalculadoraGUI extends JFrame {

    private JTextField txtNumero1, txtNumero2;  // Campos de texto para los números de entrada
    private JLabel lblResultado;  // Etiqueta para mostrar el resultado
    private String operadorSeleccionado = "";  // Almacena el operador seleccionado
    private CalculadoraController controlador;  // Controlador para el procesamiento de las operaciones

    /**
     * Constructor que inicializa la interfaz gráfica y sus componentes.
     */
    public CalculadoraGUI() {
        controlador = new CalculadoraController();  // Instancia del controlador
        initializeUI();  // Método para configurar la interfaz de usuario
    }

    /**
     * Configura los componentes de la interfaz gráfica.
     */
    private void initializeUI() {
        setTitle("Calculadora Programación Avanzada");  // Título de la ventana
        setSize(550, 400);  // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // Operación al cerrar
        setLocationRelativeTo(null);  // Centrado en pantalla

        JPanel panel = new JPanel(new GridBagLayout());  // Panel con GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Márgenes para los componentes

        // Configuración para el primer número
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Número 1:"), gbc);
        txtNumero1 = new JTextField(15);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtNumero1, gbc);

        // Configuración para el segundo número
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Número 2:"), gbc);
        txtNumero2 = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtNumero2, gbc);

        // Panel para botones de operación
        JPanel panelOperaciones = new JPanel(new GridLayout(1, 5, 10, 10));
        String[] operadores = {"+", "-", "*", "/", "Clear"};
        for (String op : operadores) {
            JButton boton = new JButton(op);
            boton.addActionListener(this::accionBoton);
            panelOperaciones.add(boton);
        }

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(panelOperaciones, gbc);

        JButton btnCalcular = new JButton("Calcular");
        gbc.gridy = 3;
        panel.add(btnCalcular, gbc);
        btnCalcular.addActionListener(this::realizarCalculo);

        lblResultado = new JLabel("Resultado: ");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 4;
        panel.add(lblResultado, gbc);

        add(panel);  // Añadir el panel a la ventana
    }

    /**
     * Manejador de eventos para los botones de operación y clear.
     * Almacena el operador seleccionado para su uso en el cálculo.
     *
     * @param e Evento de acción que contiene la fuente del evento.
     */
    private void accionBoton(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Clear".equals(command)) {
            txtNumero1.setText("");
            txtNumero2.setText("");
            lblResultado.setText("Resultado: ");
            operadorSeleccionado = "";  // Restablecer el operador seleccionado
        } else {
            operadorSeleccionado = command;
        }
    }

    /**
     * Realiza el cálculo al presionar el botón "Calcular".
     * Utiliza el controlador para procesar los números y el operador seleccionado.
     */
    private void realizarCalculo(ActionEvent e) {
        try {
            String input1 = txtNumero1.getText().trim();
            String input2 = txtNumero2.getText().trim();
            validarEntradas(input1, input2);

            controlador.setNumero1(input1);
            controlador.setNumero2(input2);
            controlador.setOperador(operadorSeleccionado);
            double resultado = controlador.calcular();

            lblResultado.setText("Resultado: " + resultado);
        } catch (IllegalArgumentException | ArithmeticException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Valida que ambos campos de entrada contengan datos antes de realizar el cálculo.
     *
     * @param input1 Primer número ingresado.
     * @param input2 Segundo número ingresado.
     * @throws IllegalArgumentException Si alguno de los campos está vacío.
     */
    private void validarEntradas(String input1, String input2) {
        if (input1.isEmpty() || input2.isEmpty()) {
            throw new IllegalArgumentException("Ambos números deben ser ingresados.");
        }
    }

    /**
     * Método principal para ejecutar la aplicación.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGUI().setVisible(true));
    }
}
