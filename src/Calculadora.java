/**
 * La clase Calculadora contiene la lógica para realizar operaciones matemáticas básicas.
 * Implementa operaciones como suma, resta, multiplicación y división, y verifica
 * que los números sean válidos (no NaN ni infinitos) para evitar errores.
 */
public class Calculadora {

    /**
     * Método privado para validar si los números de entrada o el resultado de la operación
     * son válidos. Se lanza una excepción si alguno de los números es NaN o si el resultado es infinito.
     *
     * @param a El primer número.
     * @param b El segundo número.
     * @param resultado El resultado de la operación matemática.
     * @throws IllegalArgumentException Si alguno de los números es NaN o si el resultado es demasiado grande o pequeño.
     */
    private void validarNumeros(double a, double b, double resultado) {
        if (Double.isNaN(a) || Double.isNaN(b)) {
            throw new IllegalArgumentException("Uno o ambos números son NaN.");
        }

        if (Double.isInfinite(resultado)) {
            throw new IllegalArgumentException("El resultado es demasiado grande o pequeño para ser representado.");
        }
    }

    /**
     * Método para sumar dos números.
     *
     * @param a El primer número.
     * @param b El segundo número.
     * @return La suma de los dos números.
     * @throws IllegalArgumentException Si alguno de los números es NaN o si el resultado es demasiado grande o pequeño.
     */
    public double sumar(double a, double b) {
        double suma = a + b;  // Realizamos la suma
        validarNumeros(a, b, suma);  // Validamos los números y el resultado
        return suma;
    }

    /**
     * Método para restar dos números.
     *
     * @param a El primer número.
     * @param b El segundo número.
     * @return La resta de los dos números.
     * @throws IllegalArgumentException Si alguno de los números es NaN o si el resultado es demasiado grande o pequeño.
     */
    public double restar(double a, double b) {
        double resta = a - b;  // Realizamos la resta
        validarNumeros(a, b, resta);  // Validamos los números y el resultado
        return resta;
    }

    /**
     * Método para multiplicar dos números.
     *
     * @param a El primer número.
     * @param b El segundo número.
     * @return La multiplicación de los dos números.
     * @throws IllegalArgumentException Si alguno de los números es NaN o si el resultado es demasiado grande o pequeño.
     */
    public double multiplicar(double a, double b) {
        double multiplicacion = a * b;  // Realizamos la multiplicación
        validarNumeros(a, b, multiplicacion);  // Validamos los números y el resultado
        return multiplicacion;
    }

    /**
     * Método para dividir dos números.
     * Lanza una excepción si el segundo número (b) es 0 para evitar la división por cero.
     *
     * @param a El primer número.
     * @param b El segundo número (debe ser diferente de 0).
     * @return La división de los dos números.
     * @throws ArithmeticException Si b es igual a 0 (división por cero).
     * @throws IllegalArgumentException Si alguno de los números es NaN o si el resultado es demasiado grande o pequeño.
     */
    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir por cero.");  // Manejo de la división por cero
        }
        double division = a / b;  // Realizamos la división
        validarNumeros(a, b, division);  // Validamos los números y el resultado
        return division;
    }
}
