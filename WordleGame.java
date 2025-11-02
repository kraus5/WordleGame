/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tde.programacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Esta clase contiene la lógica principal del juego.
 * @author Alberto López Terol
 */
public class WordleGame {
    
    private static final int MAX_TRIES = 6;
    private static final int WORD_LENGTH = 5;
    private final String[] fileWords;//Lo declaro como final porque una vez iniciado el constructor, no cambia su referencia.
    private final String secretWord;//Final porque la palabra secreta se define al iniciar el juego y ya no cambia.
    private int remainingAttempts;
    private final List<String> triesHistory;//Final porque la lista se inicia en el constructor y no cambia su referencia.
    
    /**
     * Constructor que usa el método loadWords de la clase WordleFileManager que contiene la lista de palabras.
     * @param fileWords Array que contiene la lista de palabras cargadas desde el archivo.
     */
    public WordleGame(String[] fileWords) {
        this.fileWords = fileWords;//Recibe un array de palabras y lo almacena en fileWords.
        this.secretWord = selectRandomWord(fileWords);//Llama al método selectRandomWord y almacena una palabra aleatoria en secretWord.
        this.remainingAttempts = MAX_TRIES;//Inicia remainingAttemps con 6 intentos.
        this.triesHistory = new ArrayList<>();//Registra las palabras intentadas.
    }
    
    /**
     * Método que devuelve una palabra aleatoria.
     * @param words Selecciona una palabra aleatoria.
     * @return La palabra aleatoria en mayúsculas.
     */
    private String selectRandomWord(String[] words) {
        Random random = new Random();//Usamos la clase Random para elegir una palabra aleatoria.
        return words[random.nextInt(words.length)].toUpperCase();//Devuelve la palabra dentro del array y la transforma a mayúscula.
    }
    
    /**
     * Método start para iniciar el juego.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);//Clase scanner para leer las palabras del usuario.
        
        System.out.println("Bienvenido a Wordle!");
        
        while (remainingAttempts > 0) {//Creamos un bucle que se ejecuta mientras queden intentos.
            showTriesHistory();//Muestra el historial de intentos con el método showTriesHistory.
            String intento = getUserInput(scanner);//Se obtiene la palabra ingresada por el usuario.
            WordleFileManager.guardarRegistro("Intento: " + intento);//Guarda los intentos en el archivo creado en la clase WordleFileManager.
            
            if (intento.equals(secretWord)) {//Si el usuario acierta la palabra se muestra un mensaje y finaliza el juego.
                System.out.println("¡Felicidades! Has adivinado la palabra correcta: " + secretWord);
                WordleFileManager.guardarRegistro("¡Felicidades! Has adivinado la palabra correcta: " + secretWord);
                return;
            } else {//Si la palabra ingresada es incorrecta, resta un intento y muestra un feedback con los colores.
                remainingAttempts--;
                String feedback = WordleFeedBack.feedBackString(secretWord, intento);
                System.out.println("Resultado: " + feedback);
                WordleFileManager.guardarRegistro("Resultado: " + feedback);//Se guarda el resultado en el archivo.
            }
        }
        
        System.out.println("Se acabaron los intentos. La palabra era: " + secretWord);//Si se acaban los intentos muestra un mensaje y se guarda el resultado en el archivo.
        WordleFileManager.guardarRegistro("Se acabaron los intentos. La palabra era: " + secretWord);
    }
    
    /**
     * Método que imprime las palabras ingresadas por el usuario y los intentos restantes.
     */
    private void showTriesHistory() {
        System.out.println("\nTienes " + remainingAttempts + " intentos restantes.");
        for (String intento : triesHistory) {//Bucle for-each que recorre las palabras almacenadas en triesHistory.
            System.out.println(intento);//Imprime la palabra intento en la consola.
        }
    }
    
    /**
     * Método que solicita al usuario que ingrese una palabra de 5 letras y la transforma a mayúsuclas.
     * @param scanner para inresar la palabra por teclado.
     * @return La palabra ingresada.
     */
    private String getUserInput(Scanner scanner) {
        String intento;
        do {
            System.out.print("Introduce una palabra de " + WORD_LENGTH + " letras: ");
            intento = scanner.nextLine().toUpperCase();//Pide una palabra al usuario de 5 letras y la transforma a mayúsculas.
            
            if (intento.length() != WORD_LENGTH) {//Si la palabra no es de 5 letras muestra un mensaje y vuelve a pedir la palabra.
                System.out.println("Error: La palabra debe tener exactamente " + WORD_LENGTH + " letras.");
            }
        } while (intento.length() != WORD_LENGTH);
        
        triesHistory.add(intento);//Añade la palabra a triesHistory y la devuelve.
        return intento;
    }
}
