/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManagementFiles;

import EDD.Grafo;
import EDD.Lista;
import EDD.Nodo;
import EDD.Vertex;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author juanp
 */
public class SaveFile {
    
    public void guardarGrafoCompleto(Grafo grafo, Lista palabras) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar grafo y diccionario");
            
            int userSelection = fileChooser.showSaveDialog(null);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                // Asegurar extensión .txt
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".txt")) {
                    fileToSave = new File(filePath + ".txt");
                }
                
                FileWriter writer = new FileWriter(fileToSave);
                
                // Escribir sección de diccionario
                writer.write("dic\n");
                Nodo current = palabras.getpFirst();
                while (current != null) {
                    writer.write(current.getDato().toString() + "\n");
                    current = current.getPnext();
                }
                writer.write("/dic\n");
                
                // Escribir sección de tablero
                writer.write("tab\n");
                for (int i = 0; i < grafo.getMaxVerts(); i++) {
                    Vertex vertice = grafo.getVerts()[i];
                    if (vertice != null) {
                        writer.write(vertice.getLetter());
                        if (i < grafo.getMaxVerts() - 1) {
                            writer.write(",");
                        }
                    }
                }
                writer.write("\n/tab");
                
                writer.close();
                
                JOptionPane.showMessageDialog(null,
                    "Archivo guardado exitosamente en:\n" + fileToSave.getAbsolutePath(),
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                "Error al guardar el archivo: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
