/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tde.programacion;

import java.util.List;

/**
 * Clase que enlaza todas las partes del juego.
 * @author Alberto López Terol
 */
public class MainClass {

    /**
     * Método main que carga las palabras del archivo, crea una instancia de WordleGame y llama a start() para iniciar el juego.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> wordList = WordleFileManager.loadWords("words.txt");//Se llama al método loadWords de la clase WordleFileManager para leer las palabras desde el archivo.  
        
        WordleGame game = new WordleGame(wordList.toArray(new String[0]));//Creamos el objeto de la clase WordleGame pasando la lista de palabras como un array.
        game.start();//Iniciamos el juego.
    }
    
}
