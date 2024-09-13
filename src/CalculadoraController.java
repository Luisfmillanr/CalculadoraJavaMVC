/**
 * La clase CalculadoraController actúa como un puente entre la interfaz gráfica
 * y la lógica de negocio de la calculadora. Se encarga de recibir entradas, validar,
 * llamar a los métodos de la clase Calculadora y devolver los resultados a la vista,
 * siguiendo el modelo MVC visto en clase.
 */
public class CalculadoraController {
    // Instancia de la clase Calculadora para realizar las operaciones matemáticas
    private Calculadora calculadora;

    // Atributos para almacenar los números y el operador
    private double numero1;
    private double numero2;
    private String operador;

    /**
     * Constructor para inicializar el controlador con una instancia de Calculadora.
     */
    public CalculadoraController(){
        this.calculadora = new Calculadora(); // Inicializamos la calculadora
    }

    /**
     * Método privado para validar y convertir una entrada de texto en un número.
     * Convierte una cadena de texto en un número de tipo double. 
     * Si la entrada no es un número válido, lanza una IllegalArgumentException.
     *
     * @param input El número ingresado como una cadena de texto desde la interfaz.
     * @return El número convertido de tipo double.
     * @throws IllegalArgumentException Si la entrada no es un número válido.
     */
    private double convertirNumero(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El valor ingresado no es válido. Debe ser un número.");
        }
    }

    /**
     * Establece el primer número de la operación matemática.
     * 
     * @param input El número ingresado como una cadena de texto desde la interfaz.
     * @throws IllegalArgumentException Si el número ingresado no es válido.
     */
    public void setNumero1(String input) {
        this.numero1 = convertirNumero(input);
    }

    /**
     * Establece el segundo número de la operación matemática.
     * 
     * @param input El número ingresado como una cadena de texto desde la interfaz.
     * @throws IllegalArgumentException Si el número ingresado no es válido.
     */
    public void setNumero2(String input) {
        this.numero2 = convertirNumero(input);
    }

    /**
     * Establece el operador que define la operación matemática a realizar.
     * Valida que el operador sea uno de los siguientes: "+", "-", "*", "/".
     *
     * @param operador Un símbolo que representa la operación (por ejemplo, "+", "-", "*", "/").
     * @throws IllegalArgumentException Si el operador ingresado no es válido.
     */
    public void setOperador(String operador) {
        if (!operador.equals("+") && !operador.equals("-") && !operador.equals("*") && !operador.equals("/")) {
            throw new IllegalArgumentException("Operador no válido. Debe ser uno de: +, -, *, /");
        }
        this.operador = operador;
    }

    /**
     * Realiza la operación matemática basada en los números y el operador seleccionados.
     * Dependiendo del operador, se llama al método correspondiente de la clase Calculadora.
     *
     * @return El resultado de la operación como un número de tipo double.
     * @throws ArithmeticException Si ocurre un error en la operación, como la división por cero.
     * @throws IllegalArgumentException Si el operador es inválido (aunque este caso nunca debería ocurrir
     *         si se validó correctamente en setOperador).
     */
    public double calcular() {
        double resultado = 0;

        // Dependiendo del operador, llamamos a los métodos de la clase Calculadora
        switch (this.operador) {
            case "+":
                resultado = calculadora.sumar(numero1, numero2);
                break;
            case "-":
                resultado = calculadora.restar(numero1, numero2);
                break;
            case "*":
                resultado = calculadora.multiplicar(numero1, numero2);
                break;
            case "/":
                try {
                    resultado = calculadora.dividir(numero1, numero2);
                } catch (ArithmeticException e) {
                    // Aquí manejamos la excepción de división por cero
                    throw new ArithmeticException("Error: No se puede dividir por cero.");
                }
                break;
            default:
                // Este caso nunca debería ocurrir porque ya validamos el operador en setOperador
                throw new IllegalArgumentException("Operador no válido.");
        }

        // Devolvemos el resultado de la operación
        return resultado;
    }
}

