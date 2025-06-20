/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Searches;

import EDD.Cola;
import EDD.Grafo;
import EDD.Lista;
import EDD.Nodo;
import EDD.Vertex;

/**
 *
 * @author juanp
 */
public class BFS {
    private Grafo graph;
    private Lista visited; // Para llevar registro de nodos visitados en el camino actual
    private boolean found; // Indica si se encontró la palabra

    /**
     * Constructor que inicializa el BFS con un grafo específico.
     * @param graph Grafo en el que se realizarán las búsquedas
     */
    public BFS(Grafo graph) {
        this.graph = graph;
        this.visited = new Lista();
        this.found = false;
    }

    /**
     * Obtiene el grafo asociado al BFS.
     * @return Grafo actual
     */
    public Grafo getGraph() {
        return graph;
    }

    /**
     * Establece un nuevo grafo para el BFS.
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
     * Busca una palabra completa en el grafo usando BFS.
     * @param palabra La palabra a buscar en el grafo
     * @return true si la palabra se encuentra, false en caso contrario
     */
    public boolean buscarPalabra(String palabra) {
        // Validaciones iniciales
        if (graph.isEmpty() || palabra == null || palabra.isEmpty()) {
            return false;
        }

        palabra = palabra.toUpperCase(); // Convertir a mayúsculas para coincidir con el tablero
        Vertex[] vertices = graph.getVerts();
        found = false;

        // Buscar la primera letra en todos los vértices
        for (int i = 0; i < vertices.length && !found; i++) {
            if (vertices[i] != null && vertices[i].getLetter().equals(String.valueOf(palabra.charAt(0)))) {
                visited.destructor();// Limpiar lista de visitados
                bfs(i, palabra);
            }
        }

        return found;
    }

    /**
     * Implementación privada del algoritmo BFS para buscar la palabra.
     * @param startIndex Índice del vértice inicial para la búsqueda
     * @param palabra Palabra completa a buscar
     */
    private void bfs(int startIndex, String palabra) {
        Cola queue = new Cola();
        // Usamos una lista para guardar el camino actual
        Lista currentPath = new Lista();
        currentPath.insertStart(startIndex);
        queue.insert(currentPath);

        while (!queue.isEmpty() && !found) {
            Lista path = (Lista) queue.delete();
            int lastIndex = (int) path.getpFirst().getDato();
            
            // Si el camino actual coincide con la longitud de la palabra
            if (path.getSize() == palabra.length()) {
                found = true;
                visited = path; // Guardamos el camino encontrado
                return;
            }

            Vertex current = graph.getVerts()[lastIndex];
            int nextPos = path.getSize();
            String nextLetter = String.valueOf(palabra.charAt(nextPos));
            int[][] matAd = graph.getMatAd();

            // Explorar todos los vértices adyacentes
            for (int i = 0; i < matAd[lastIndex].length; i++) {
                // Si es adyacente y no está en el camino actual
                if (matAd[lastIndex][i] == 1 && !path.search(i)) {
                    Vertex neighbor = graph.getVerts()[i];
                    if (neighbor != null && neighbor.getLetter().equals(nextLetter)) {
                        // Crear un nuevo camino con este vecino
                        Lista newPath = new Lista();
                        // Copiar el camino actual
                        Nodo currentInPath = path.getpFirst();
                        while (currentInPath != null) {
                            newPath.insertStart(currentInPath.getDato());
                            currentInPath = currentInPath.getPnext();
                        }
                        newPath.insertStart(i); // Agregar el nuevo vértice
                        queue.insert(newPath);
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
