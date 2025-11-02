/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tde.programacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encarga de leer las palabras desde un archivo y almacenar el
 * historial de partidas en otro archivo.
 *
 * @author Alberto López Terol
 */
public class WordleFileManager {

    /**
     * Método para leer las palabras desde el archivo y devolver una lista de
     * palabras.
     *
     * @param palabras representa la ruta del archivo desde donde se leerán las
     * palabras.
     * @return La lista de palabras.
     */
    public static List<String> loadWords(String palabras) {
        List<String> words = new ArrayList<>();//Creamos una lista vacía para almacenar las palabras leídas del archivo.
        try (FileReader fr = new FileReader(new File(palabras));//FileReader abre el archivo y lee caracteres. Creamos el objeto File que representa el archivo de palabras. 
                 BufferedReader br = new BufferedReader(fr)) {//BufferedReader lee el archivo línea por línea para no tener que hacerlo por caracteres.
            String line;
            while ((line = br.readLine()) != null) {//Lee el archivo mientras hayan líneas disponibles para leer (!= null)
                words.add(line.trim());//Agrega líneas a la lista eliminando espacios innecesarios con trim()
            }
        } catch (IOException e) {//Si no se encuentra el archivo o hay cualquier error salta un mensaje.
            System.out.println("Error al leer el archivo.");
        }
        return words;
    }

    /**
     * Método para guardar el historial de la partida en otro archivo.
     *
     * @param historial contiene la información de la partida que queremos
     * guardar en el archivo.
     */
    public static void guardarRegistro(String historial) {
        try (FileWriter fw = new FileWriter("historial.txt", true);//Abre el archivo y con true activamos el modo append para que añada texto al final, sin sobrescribir.
                 PrintWriter pw = new PrintWriter(fw)) {//Escribe líneas de texto.
            pw.println(historial);//Escribe palabras en el archivo con un salto de línea (println)
        } catch (IOException e) {//Si hay algún error salta un mensaje.
            System.out.println("Error al escribir en el archivo del historial.");
        }
    }
}
