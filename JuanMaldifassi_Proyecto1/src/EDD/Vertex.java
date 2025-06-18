/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author juanp
 */
public class Vertex {
    
    private String letter;  // Letra en la celda (ej. 'C', 'H', 'A')
    private int row;      // Fila en el tablero (0 a 3)
    private int col;      // Columna en el tablero (0 a 3)

    public Vertex(String letter, int row, int col) {
        this.letter = letter;
        this.row = row;
        this.col = col;
    }
    public Vertex(String letter) {
        this.letter = letter;
        this.row = -1;
        this.col = -1;
    }

    // Getters
    public String getLetter() {
        return letter;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    public void setLetter(String letter) {
        this.letter = letter;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
    
    public String nameUnique(){
        return letter + row + col;
    }
    @Override
    public String toString() {
        return "Celda [" + row + "][" + col + "]: " + letter;
    }
    
}
