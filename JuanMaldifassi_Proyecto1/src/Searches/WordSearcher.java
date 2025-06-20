/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Searches;

import EDD.Grafo;
import EDD.Lista;
import EDD.Nodo;

/**
 *
 * @author juanp
 */
public class WordSearcher {
    
    private Grafo graph;
    private Lista encontradasBFS;
    private Lista noEncontradasBFS;
    private Lista encontradasDFS;
    private Lista noEncontradasDFS;
    private long tiempoEjecucionDFS;
    private long tiempoEjecucionBFS;

    /**
     * Constructor que inicializa el buscador con un grafo específico.
     * @param graph Grafo en el que se realizarán las búsquedas
     */
    public WordSearcher(Grafo graph) {
        this.graph = graph;
        this.encontradasBFS = new Lista();
        this.noEncontradasBFS = new Lista();
        this.encontradasDFS = new Lista();
        this.noEncontradasDFS = new Lista();
        this.tiempoEjecucionDFS = 0;
        this.tiempoEjecucionBFS = 0;
    }
    
    /**
     * Obtiene el grafo asociado al buscador.
     * @return Grafo actual
     */
    public Grafo getGraph() {
        return graph;
    }

    /**
     * Establece un nuevo grafo para el buscador.
     * @param graph Nuevo grafo a establecer
     */
    public void setGraph(Grafo graph) {
        this.graph = graph;
    }

    /**
     * Obtiene la lista de palabras encontradas con BFS.
     * @return Lista de palabras encontradas
     */
    public Lista getEncontradasBFS() {
        return encontradasBFS;
    }

    /**
     * Establece la lista de palabras encontradas con BFS.
     * @param encontradasBFS Nueva lista de palabras encontradas
     */
    public void setEncontradasBFS(Lista encontradasBFS) {
        this.encontradasBFS = encontradasBFS;
    }

    /**
     * Obtiene la lista de palabras no encontradas con BFS.
     * @return Lista de palabras no encontradas
     */
    public Lista getNoEncontradasBFS() {
        return noEncontradasBFS;
    }

    /**
     * Establece la lista de palabras no encontradas con BFS.
     * @param noEncontradasBFS Nueva lista de palabras no encontradas
     */
    public void setNoEncontradasBFS(Lista noEncontradasBFS) {
        this.noEncontradasBFS = noEncontradasBFS;
    }

    /**
     * Obtiene la lista de palabras encontradas con DFS.
     * @return Lista de palabras encontradas
     */
    public Lista getEncontradasDFS() {
        return encontradasDFS;
    }

    /**
     * Establece la lista de palabras encontradas con DFS.
     * @param encontradasDFS Nueva lista de palabras encontradas
     */
    public void setEncontradasDFS(Lista encontradasDFS) {
        this.encontradasDFS = encontradasDFS;
    }

    /**
     * Obtiene la lista de palabras no encontradas con DFS.
     * @return Lista de palabras no encontradas
     */
    public Lista getNoEncontradasDFS() {
        return noEncontradasDFS;
    }

    /**
     * Establece la lista de palabras no encontradas con DFS.
     * @param noEncontradasDFS Nueva lista de palabras no encontradas
     */
    public void setNoEncontradasDFS(Lista noEncontradasDFS) {
        this.noEncontradasDFS = noEncontradasDFS;
    }

    /**
     * Obtiene el tiempo de ejecución total del último DFS realizado.
     * @return Tiempo en milisegundos
     */
    public long getTiempoEjecucionDFS() {
        return tiempoEjecucionDFS;
    }

    /**
     * Establece manualmente el tiempo de ejecución de DFS.
     * @param tiempoEjecucionDFS Nuevo tiempo en milisegundos
     */
    public void setTiempoEjecucionDFS(long tiempoEjecucionDFS) {
        this.tiempoEjecucionDFS = tiempoEjecucionDFS;
    }

    /**
     * Obtiene el tiempo de ejecución total del último BFS realizado.
     * @return Tiempo en milisegundos
     */
    public long getTiempoEjecucionBFS() {
        return tiempoEjecucionBFS;
    }

    /**
     * Establece manualmente el tiempo de ejecución de BFS.
     * @param tiempoEjecucionBFS Nuevo tiempo en milisegundos
     */
    public void setTiempoEjecucionBFS(long tiempoEjecucionBFS) {
        this.tiempoEjecucionBFS = tiempoEjecucionBFS;
    }

    /**
     * Realiza la búsqueda de todas las palabras usando DFS.
     * @param palabras Lista de palabras a buscar
     */
    public void buscarConDFS(Lista palabras) {
        encontradasDFS.destructor();
        noEncontradasDFS.destructor();
        long startTime = System.currentTimeMillis();

        if (palabras.isEmpty() || graph.isEmpty()) {
            tiempoEjecucionDFS = System.currentTimeMillis() - startTime;
        }

        for (int i = 0; i < palabras.getSize(); i++) {
            String palabra = (String) palabras.getValue(i);
            DFS dfs = new DFS(graph);
            boolean encontrada = dfs.buscarPalabra(palabra);
            if (encontrada) {
                encontradasDFS.insertFinal(palabra);
            } else {
                noEncontradasDFS.insertFinal(palabra);
            }
        }

        tiempoEjecucionDFS = System.currentTimeMillis() - startTime;
    }

    /**
     * Realiza la búsqueda de todas las palabras usando BFS.
     * @param palabras Lista de palabras a buscar
     */
    public void buscarConBFS(Lista palabras) {
        encontradasBFS.destructor();
        noEncontradasBFS.destructor();
        long startTime = System.currentTimeMillis();

        if (palabras.isEmpty() || graph.isEmpty()) {
            tiempoEjecucionBFS = System.currentTimeMillis() - startTime;
        }

        for (int i = 0; i < palabras.getSize(); i++) {
            String palabra = (String) palabras.getValue(i);
            BFS bfs = new BFS(graph);
            boolean encontrada = bfs.buscarPalabra(palabra);
            if (encontrada) {
                encontradasBFS.insertFinal(palabra);
            } else {
                noEncontradasBFS.insertFinal(palabra);
            }
        }

        tiempoEjecucionBFS = System.currentTimeMillis() - startTime;
    }

    /**
     * Compara ambos métodos de búsqueda y genera un reporte detallado.
     * @param palabras Lista de palabras a buscar para la comparación
     * @return String con el reporte comparativo
     */
    public String compararMetodos(Lista palabras) {
        StringBuilder reporte = new StringBuilder();

        // Ejecutar DFS
        buscarConDFS(palabras);
        // Ejecutar BFS
        buscarConBFS(palabras);

        reporte.append("\n=== COMPARACIÓN DE MÉTODOS DE BÚSQUEDA ===\n");
        reporte.append("\nDFS:\n");
        reporte.append(" - Palabras encontradas: ").append(encontradasDFS.getSize()).append("\n - Palabras no encontradas: ").append(noEncontradasDFS.getSize()).append("\n - Tiempo de ejecucion: ").append(tiempoEjecucionDFS).append(" ms");
        reporte.append("\n\nBFS:\n");
        reporte.append(" - Palabras encontradas: ").append(encontradasBFS.getSize()).append("\n - Palabras no encontradas: ").append(noEncontradasBFS.getSize()).append("\n - Tiempo de ejecucion: ").append(tiempoEjecucionBFS).append(" ms");

        reporte.append("\n\nDiferencias:\n");
        reporte.append("- DFS fue más rápido por: ").append(Math.max(0, tiempoEjecucionBFS - tiempoEjecucionDFS)).append(" ms\n");
        reporte.append("- BFS fue más rápido por: ").append(Math.max(0, tiempoEjecucionDFS - tiempoEjecucionBFS)).append(" ms\n");

        reporte.append("\nPalabras encontradas por ambos métodos: ").append(interseccionListas(encontradasDFS, encontradasBFS).getSize());
        reporte.append("\nPalabras solo encontradas por DFS: ").append(diferenciaListas(encontradasDFS, encontradasBFS).getSize());
        reporte.append("\nPalabras solo encontradas por BFS: ").append(diferenciaListas(encontradasBFS, encontradasDFS).getSize());

        return reporte.toString();
    }

    /**
     * Método auxiliar que calcula la intersección entre dos listas.
     * @param lista1 Primera lista
     * @param lista2 Segunda lista
     * @return Lista con elementos comunes a ambas listas
     */
    private Lista interseccionListas(Lista lista1, Lista lista2) {
        Lista resultado = new Lista();
        Nodo current = lista1.getpFirst();

        while (current != null) {
            if (lista2.search(current.getDato())) {
                resultado.insertFinal(current.getDato());
            }
            current = current.getPnext();
        }

        return resultado;
    }

    /**
     * Método auxiliar que calcula la diferencia entre dos listas (A - B).
     * @param listaA Lista principal
     * @param listaB Lista a comparar
     * @return Lista con elementos de A que no están en B
     */
    private Lista diferenciaListas(Lista listaA, Lista listaB) {
        Lista resultado = new Lista();
        Nodo current = listaA.getpFirst();

        while (current != null) {
            if (!listaB.search(current.getDato())) {
                resultado.insertFinal(current.getDato());
            }
            current = current.getPnext();
        }

        return resultado;
    } 
    
}
