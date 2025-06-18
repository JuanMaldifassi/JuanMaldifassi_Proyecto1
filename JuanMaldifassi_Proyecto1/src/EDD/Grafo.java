/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author juanp
 */
public class Grafo {
    private int numVerts;
    private Vertex[] verts;
    private int[][] matAd;
    private int maxVerts;

    // Constructor 
    public Grafo() {
        this.maxVerts = 16; // 4x4 = 16 vértices fijos
        this.verts = new Vertex[this.maxVerts];
        this.matAd = new int[this.maxVerts][this.maxVerts];
        this.numVerts = 0;
        this.initMatrix();
    }

    //Funciones privadas para usar dentro del constructor
    private void initMatrix() {
        for (int i = 0; i < maxVerts; i++) {
            for (int j = 0; j < maxVerts; j++) {
                matAd[i][j] = 0;
            }
        }
    }

    //Getter and Setter
    public int getNumVerts() {
        return numVerts;
    }

    public void setNumVerts(int numVerts) {
        this.numVerts = numVerts;
    }

    public Vertex[] getVerts() {
        return verts;
    }

    public void setVerts(Vertex[] verts) {
        this.verts = verts;
    }

    public int[][] getMatAd() {
        return matAd;
    }

    public void setMatAd(int[][] matAd) {
        this.matAd = matAd;
    }

    public int getMaxVerts() {
        return maxVerts;
    }

    public void setMaxVerts(int maxVerts) {
        this.maxVerts = maxVerts;
    }

    public void fillBoard(String[][] tablero) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // Asegurarse de crear todos los vértices
                String nombre = String.valueOf(tablero[i][j]);
                Vertex v = new Vertex(nombre, i, j);
                verts[i * 4 + j] = v; // Asignación directa por posición
                numVerts++;
            }
        }
    }

    public void conectarAdyacentes() {
        int[] movFila = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] movCol = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < maxVerts; i++) { // Usar maxVerts en lugar de numVerts
            Vertex actual = verts[i];
            if (actual == null) {
                continue; // Saltar si el vértice no existe
            }
            int filaActual = actual.getRow();
            int colActual = actual.getCol();

            for (int k = 0; k < 8; k++) {
                int nuevaFila = filaActual + movFila[k];
                int nuevaCol = colActual + movCol[k];

                if (nuevaFila >= 0 && nuevaFila < 4 && nuevaCol >= 0 && nuevaCol < 4) {
                    int indiceVecino = nuevaFila * 4 + nuevaCol;
                    if (verts[indiceVecino] != null) { // Verificar que el vecino existe
                        matAd[i][indiceVecino] = 1;
                        matAd[indiceVecino][i] = 1;
                    }
                }
            }
        }
    }

    //Primitivas de la clase
    public void addVertex(String nombre, int fila, int columna) {
        if (numVertice(nombre) < 0) {
            Vertex v = new Vertex(nombre, fila, columna); // Usar constructor con coordenadas
            verts[numVerts++] = v;
        }
    }

    public int numVertice(String vs) {
        for (int i = 0; i < numVerts; i++) {
            if (verts[i].getLetter().equals(vs)) {
                return i;
            }
        }
        return -1;
    }

    public void addEdge(int va, int vb) throws Exception {
        if (va  < 0 || vb < 0 || va  >= maxVerts || vb >= maxVerts) {
            throw new Exception("Vértice no existe");
        }
        matAd[va][vb] = 1;
        matAd[vb][va] = 1; // Grafo no dirigido
    }

    /**
     * Muestra el grafo en consola de dos formas: 1. El tablero de letras (4x4)
     * 2. La matriz de adyacencia (conexiones entre vértices)
     */
    public void mostrarGrafo() {
        System.out.println("\n=== TABLERO DE LETRAS ===");
        mostrarTablero();

        System.out.println("\n=== MATRIZ DE ADYACENCIA ===");
        mostrarMatrizAdyacencia();
    }

    /**
     * Muestra el tablero 4x4 con las letras y sus posiciones
     */
    private void mostrarTablero() {
        // Crear matriz temporal para organizar las letras
        String[][] tablero = new String[4][4];

        // Llenar la matriz con las letras
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int index = i * 4 + j;
                if (index < verts.length && verts[index] != null) {
                    tablero[i][j] = verts[index].getLetter();
                } else {
                    tablero[i][j] = " ";
                }
            }
        }

        // Mostrar letras solas (vista de sopa de letras)
        System.out.println("\nVista de sopa de letras:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Muestra la matriz de adyacencia (solo conexiones)
     */
    private void mostrarMatrizAdyacencia() {

        System.out.println("\nConexiones importantes:");
        for (int i = 0; i < 16; i++) {
            if (verts[i] != null) {
                System.out.print("Vértice " + i + " (" + verts[i].getLetter() + ") conecta con: ");
                for (int j = 0; j < 16; j++) {
                    if (matAd[i][j] == 1 && verts[j] != null) {
                        System.out.print(j + " (" + verts[j].getLetter() + "), ");
                    }
                }
                System.out.println();
            }
        }
    }

    /**
     * Obtiene los vértices adyacentes a una coordenada específica
     *
     * @param index Indice del vértice (0 a 15)
     * @return Lista de vértices adyacentes al vértice en la posición (fila,
     * col)
     */
    public Lista getAdyacentesPorCoordenada(int index) {
        // Validar que las coordenadas estén dentro del rango
        if (index < 16 && index>=0) {
            Lista adyacentes = new Lista();

            // Verificar que el vértice actual existe
            if (verts[index] == null) {
                return adyacentes; // Retorna lista vacía si no hay vértice en esa posición
            }

            // Recorrer la fila correspondiente en la matriz de adyacencia
            for (int j = 0; j < maxVerts; j++) {
                // Si hay conexión (valor 1 en la matriz) y el vértice existe
                if (matAd[index][j] == 1 && verts[j] != null) {
                    adyacentes.insertFinal(verts[j]); // Agregar a la lista de adyacentes
                }
            }

            return adyacentes;
        }

        return null;
    }

    public void destroid() {
        this.maxVerts = 16; // 4x4 = 16 vértices fijos
        this.verts = new Vertex[this.maxVerts];
        this.matAd = new int[this.maxVerts][this.maxVerts];
        this.numVerts = 0;
        this.initMatrix();
    }

    public boolean isEmpty() {
        return this.numVerts == 0;
    }
}
