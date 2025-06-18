/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import EDD.Grafo;
import EDD.Lista;
import EDD.Vertex;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

/**
 *
 * @author juanp
 */

public class VisualizadorArbol extends JFrame {

    private Grafo grafo;
    private Lista visited;
    private Viewer visor;
    private ViewPanel panelVista;

    public VisualizadorArbol(Grafo grafo, Lista visited) {
        this.grafo = grafo;
        this.visited = visited;
        configurarInterfaz();
        inicializarVisor();
        agregarBotonRegresar();
    }

    private void configurarInterfaz() {
        setTitle("Mapa de Estaciones");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void inicializarVisor() {
        Graph grafoVisual = new SingleGraph("Estaciones");
        construirGrafo(grafoVisual);

        // Mostrar el grafo sin crear una nueva ventana
        visor = grafoVisual.display(false);
        visor.enableAutoLayout();  // Permitir que el layout se ajuste automáticamente

        // Creamos el panel de visualización si no existe ya
        if (panelVista == null) {
            panelVista = (ViewPanel) visor.getDefaultView();  // Sin ventana adicional
            add(panelVista, BorderLayout.CENTER);  // Añadir el panel al JFrame
        }
    }

    private void construirGrafo(Graph grafoVisual) {
        for (int i = 0; i < visited.getSize(); i++) {
            int num = (int) visited.getValue(i);
            Vertex vertice = grafo.getVerts()[num];
            Node nodo = grafoVisual.addNode(vertice.nameUnique());
            nodo.setAttribute("ui.label", vertice.getLetter());
        }

        agregarRutas(grafoVisual);

        // Ajustar los estilos generales del grafo
        grafoVisual.setAttribute("ui.stylesheet",
                "node { text-size: 14px; size: 25px; text-alignment: under; }"
                + "edge { size: 2px; }"
        );
    }

    private void agregarRutas(Graph grafoVisual) {
        for (int i = 0; i < visited.getSize(); i++) {
            int num = (int) visited.getValue(i);
            Vertex vertice = grafo.getVerts()[num];
            Lista adyacentes = grafo.getAdyacentesPorCoordenada(grafo.numVertice(vertice.getLetter()));

            // Conexiones entre estaciones adyacentes
            for (int j = 0; j < adyacentes.getSize(); j++) {
                Vertex vertAdyacente = (Vertex) adyacentes.getValue(j);
                boolean encontrado = false;

                for (int k = 0; k < visited.getSize(); k++) {
                    int num2 = (int) visited.getValue(k);
                    Vertex vertice2 = grafo.getVerts()[num2];
                    if (vertice2.nameUnique().equals(vertAdyacente.nameUnique())) {
                        encontrado = true;
                        break;
                    }
                }

                if (encontrado) {
                    String idConexion = vertice.nameUnique() + "-" + vertAdyacente.nameUnique();

                    if (grafoVisual.getEdge(idConexion) == null && grafoVisual.getEdge(vertAdyacente.nameUnique() + "-" + vertice.nameUnique()) == null) {
                        grafoVisual.addEdge(idConexion, vertice.nameUnique(), vertAdyacente.nameUnique());
                    }
                }
            }
        }
    }

    private void agregarBotonRegresar() {
        JButton botonRegresar = new JButton("Regresar");
        botonRegresar.addActionListener(e -> {
            cerrarVisor();
            this.dispose();

            Board menuPrincipal = new Board();
            menuPrincipal.setVisible(true);
        });
        add(botonRegresar, BorderLayout.SOUTH);
    }

    private void cerrarVisor() {
        if (visor != null) {
            visor.disableAutoLayout();
            visor.close();
        }
        if (panelVista != null) {
            remove(panelVista);
            panelVista = null;
        }
    }
}
