/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

/**
 *
 * @author juanp
 */
public class Helpers {
    
    private boolean validarLetras(String letra){
        return letra.matches("[a-zA-Zñ]*");
    }
    
    public String validarPalabra(String palabra){
        if(validarLetras(palabra)){
            return palabra;
        }else{
            return null;
        }
    }
    
}
