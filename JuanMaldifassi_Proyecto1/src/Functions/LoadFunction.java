/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import EDD.Grafo;
import EDD.Lista;

/**
 *
 * @author juanp
 */
public class LoadFunction {
    
    private String txt;
    private Grafo grafo;
    private Lista diccionario;

    public LoadFunction(String txt, Grafo grafo, Lista diccionario) {
        this.txt = txt;
        this.grafo = grafo;
        this.diccionario = diccionario;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public Lista getDiccionario() {
        return diccionario;
    }

    public void setDiccionario(Lista diccionario) {
        this.diccionario = diccionario;
    }

    //Cargar el contenido del txt en el grafo
    public void load() {
        // Limpiar estructuras existentes
        diccionario.empty();
        grafo = new Grafo(); // Reiniciar el grafo

        // Variables de control
        boolean enDiccionario = false;
        boolean enTablero = false;

        // Dividir el texto en líneas
        String[] lineas = txt.split("\n");

        for (String linea : lineas) {
            linea = linea.trim(); // Eliminar espacios en blanco

            if (linea.equals("dic")) {
                enDiccionario = true;
                enTablero = false;
                continue;
            } else if (linea.equals("/dic")) {
                enDiccionario = false;
                continue;
            } else if (linea.equals("tab")) {
                enTablero = true;
                enDiccionario = false;
                continue;
            } else if (linea.equals("/tab")) {
                enTablero = false;
                continue;
            }

            if (enDiccionario && !linea.isEmpty()) {
                // Agregar palabra al diccionario
                String palabra = linea.toUpperCase().trim();
                diccionario.insertFinal(palabra);
            } else if (enTablero && !linea.isEmpty()) {
                // Procesar el tablero
                String[] letras = linea.split(",");
                String[][] tablero = new String[4][4];
                int index = 0;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (index < letras.length) {
                            tablero[i][j] = letras[index++].trim().toUpperCase();
                        } else {
                            tablero[i][j] = ""; // Valor por defecto si faltan letras
                        }
                    }
                }

                // Llenar el grafo
                grafo.fillBoard(tablero);
                grafo.conectarAdyacentes();
            }
        }
    }
    
}
