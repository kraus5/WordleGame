/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tde.programacion;

/**
 * Clase encargada de aplicar el sistema de colores.
 *
 * @author Alberto López Terol
 */
public class WordleFeedBack {

    private static final int WORD_LENGTH = 5;//Palabra de 5 letras.
    public static final String ANSI_RESET = "\u001B[0m";//Para resetear el color.
    public static final String ANSI_GREEN = "\u001B[32m";//Verde cuando la letra es correcta y está en su posición.
    public static final String ANSI_YELLOW = "\u001B[33m";//Amarillo cuando la letra es correcta pero no está en su posición.
    public static final String ANSI_RED = "\u001B[31m";//Rojo cuando la letra no existe.

    /**
     * Método para aplicar el color correspondiente a la letra.
     *
     * @param letter Recibe una letra.
     * @param color Recibe un código de color ANSI.
     * @return La letra con un color aplicado.
     */
    private static String applyColor(String letter, String color) {
        return color + letter + ANSI_RESET;//ANSI_RESET para resetear los colores en la consola después de la letra.
    }

    /**
     * Método que compara la palabra del usuario con la palabra secreta y genera
     * un feedback visual con los colores aplicados.
     *
     * @param secretWord Palabra secreta que el usuario debe adivinar.
     * @param intento Palabra ingresada por el usuario (intento).
     * @return La palabra coloreada.
     */
    public static String feedBackString(String secretWord, String intento) {//Comparamos la palabra del usuario con la palabra secreta.
        StringBuilder feedback = new StringBuilder();//Creamos el StringBuilder que almacena el resultado coloreado de la comparación.

        for (int i = 0; i < WORD_LENGTH; i++) {//Recorre cada letra de la palabra ingresada por el usuario.
            char letra = intento.charAt(i);//Se obtiene la letra en la posición "i" usando intento.charAt(i).
            if (letra == secretWord.charAt(i)) {//Verifica si la letra ingresada está en la misma posición que la palabra secreta.
                feedback.append(applyColor(String.valueOf(letra), ANSI_GREEN));//Si está en la posición correcta, la colorea en verde.
            } else if (secretWord.contains(String.valueOf(letra))) {
                feedback.append(applyColor(String.valueOf(letra), ANSI_YELLOW));//Si la letra existe pero no está en la posición correcta, la colorea en amarillo.
            } else {
                feedback.append(applyColor(String.valueOf(letra), ANSI_RED));//Si la letra no existe la colorea en rojo.
            }
        }

        return feedback.toString();//Convertimos el feedback del StringBuilder a un String para mostar la palabra coloreada por pantalla.
    }
}
