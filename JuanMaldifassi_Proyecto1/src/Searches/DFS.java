/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Searches;

import EDD.Grafo;
import EDD.Lista;
import EDD.Nodo;
import EDD.Vertex;


/**
 *
 * @author juanp
 */
public class DFS {
    private Grafo graph;
    private Lista visited; // Para llevar registro de nodos visitados
    private boolean found; // Indica si se encontró la palabra

    /**
     * Constructor que inicializa el DFS con un grafo específico.
     * @param graph Grafo en el que se realizarán las búsquedas
     */
    public DFS(Grafo graph) {
        this.graph = graph;
        this.visited = new Lista();
        this.found = false;
    }
    
    /**
     * Obtiene el grafo asociado al DFS.
     * @return Grafo actual
     */
    public Grafo getGraph() {
        return graph;
    }

    /**
     * Establece un nuevo grafo para el DFS.
     * @param graph Nuevo grafo a establecer
     */
    public void setGraph(Grafo graph) {
        this.graph = graph;
    }

    /**
     * Obtiene la lista de vértices visitados.
     * @return Lista con los índices de los vértices visitados
     */
    public Lista getVisited() {
        return visited;
    }

    /**
     * Establece una nueva lista de visitados.
     * @param visited Nueva lista de visitados
     */
    public void setVisited(Lista visited) {
        this.visited = visited;
    }

    /**
     * Verifica si se encontró la palabra en la última búsqueda.
     * @return true si se encontró la palabra, false en caso contrario
     */
    public boolean isFound() {
        return found;
    }

    /**
     * Establece manualmente el estado de encontrado.
     * @param found Nuevo valor para el estado de búsqueda
     */
    public void setFound(boolean found) {
        this.found = found;
    }
    
    /**
     * Busca una palabra completa en el grafo usando DFS.
     * @param palabra La palabra a buscar en el grafo
     * @return true si la palabra se encuentra, false en caso contrario
     */
    public boolean buscarPalabra(String palabra) {
        // Validaciones iniciales
        if (graph.isEmpty() || palabra == null) {
            return false;
        }

        palabra = palabra.toUpperCase(); // Convertir a mayúsculas para coincidir con el tablero
        Vertex[] vertices = graph.getVerts();
        found = false;

        // Buscar la primera letra en todos los vértices
        for (int i = 0; i < vertices.length && !found; i++) {
            if (vertices[i] != null && vertices[i].getLetter().equals(String.valueOf(palabra.charAt(0)))) {
                visited.destructor();// Limpiar lista de visitados
                visited.insertStart(i); // Marcar como visitado
                buscarDFS(i, palabra, 1); // Buscar recursivamente
            }
        }

        return found;
    }

    /**
     * Implementación privada del algoritmo DFS para buscar la palabra.
     * @param currentIndex Índice del vértice actual
     * @param palabra Palabra completa a buscar
     * @param posicion Posición actual en la palabra que se está buscando
     */
    private void buscarDFS(int currentIndex, String palabra, int posicion) {
        if (posicion >= palabra.length()) {
            found = true;
        } else {
            Vertex current = graph.getVerts()[currentIndex];
            String nextLetter = String.valueOf(palabra.charAt(posicion));
            int[][] matAd = graph.getMatAd();

            // Explorar todos los vértices adyacentes
            for (int i = 0; i < matAd[currentIndex].length && !found; i++) {
                // Si es adyacente y no ha sido visitado en esta búsqueda
                if (matAd[currentIndex][i] == 1 && !visited.search(i)) {
                    Vertex neighbor = graph.getVerts()[i];
                    
                    if (neighbor != null && neighbor.getLetter().equals(nextLetter)) {
                        visited.insertStart(i); // Marcar como visitado
                        buscarDFS(i, palabra, posicion + 1); // Llamada recursiva
                        
                        if (!found) {
                            //desmarcar si no lleva al resultado
                            visited.deleteForReference(i);
                        }
                    }
                }
            }
        }
    }

    /**
     * Obtiene las coordenadas de la palabra encontrada en el tablero.
     * @return Lista de strings con formato "(fila,columna)" para cada letra
     */
    public Lista obtenerCoordenadasPalabra() {
        Lista coordenadas = new Lista();
        if (found) {
            Nodo current = visited.getpFirst();
            while (current != null) {
                int index = (int) current.getDato();
                Vertex v = graph.getVerts()[index];
                coordenadas.insertStart("(" + v.getRow() + "," + v.getCol() + ")");
                current = current.getPnext();
            }
        }
        return coordenadas;
    }
}
