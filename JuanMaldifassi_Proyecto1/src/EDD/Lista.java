/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import javax.swing.JOptionPane;

/**
 *
 * @author juanp
 */
public class Lista {
    
    private Nodo pFirst; //nodo apuntador al primero
    private int size; //tamaño de la lista
    
    //Constructor de la clase Lista
    public Lista() {
        this.pFirst = null;
        this.size = 0; 
    }
    
    //Metodos get y set para los atrubutos

    public Nodo getpFirst() {
        return pFirst;
    }

    public void setpFirst(Nodo pFirst) {
        this.pFirst = pFirst;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    //Primitivas
    
    //Funcion para verificar si la lista es vacia
    public boolean isEmpty(){
        return this.pFirst == null;
    }
    
    //Metodo que vacia la lista
    public void empty(){
        this.pFirst = null;
        this.size = 0;   
    }
    
    //Metodo para insertar al inicio
    public Nodo insertStart(Object dato){
        
        Nodo pNew = new Nodo();
        pNew.setDato(dato);
       
        if (isEmpty()) {   
            pFirst = pNew;
        } else{
            pNew.setPnext(pFirst);
            pFirst = pNew;
        }
        size++;
        return pNew;
    }
    


    //Metodo para insertar al final
    public void insertFinal(Object dato){
        Nodo pNew = new Nodo(dato);
        if(isEmpty()){
            pFirst = pNew;
        }else{
            Nodo aux = pFirst;
            while (aux.getPnext() != null){
                aux = aux.getPnext();
            }
            aux.setPnext(pNew);
        }
        size++;
    }
    
    public void insertForPosition(int posicion, Object valor){
        if(posicion>=0 && posicion<size){
            Nodo nuevo = new Nodo(valor);
            if(posicion == 0){
                nuevo.setPnext(pFirst);
                pFirst = nuevo;
            }
            else{
                if(posicion == size-1){
                    Nodo aux = pFirst;
                    while(aux.getPnext() != null){
                        aux = aux.getPnext();
                    }
                    aux.setPnext(nuevo);              
                }
                else{
                    Nodo aux = pFirst;
                    for (int i = 0; i < (posicion-1); i++) {
                        aux = aux.getPnext();
                    }
                    Nodo siguiente = aux.getPnext();
                    aux.setPnext(nuevo);
                    nuevo.setPnext(siguiente);
                }
            }
            size++;
        }
    }
    
    //Metodo para Insertar por referencia
    public void insertForReference(Object ref,Object valor){
        
        Nodo nuevo = new Nodo();
        nuevo.setDato(valor);
        
        if (!isEmpty()) {
            if (search(ref)) {
                Nodo aux = pFirst;
                // Recorre la lista hasta llegar al nodo de referencia.
                while (aux.getDato() != ref) {
                    aux = aux.getPnext();
                }
                // Crea un respaldo de la continuación de la lista.
                Nodo siguiente = aux.getPnext();
                // Enlaza el nuevo nodo despues del nodo de referencia.
                aux.setPnext(nuevo);
                // Une la continuacion de la lista al nuevo nodo.
                nuevo.setPnext(siguiente);
            
                size++;
            }
        }
    }
    
    //Metodo para transformar una lista en cadena de caracteres
    public String convertString(){
        if(!isEmpty()){
            
            Nodo aux = pFirst;
            String expresion = "";
            
            for(int i = 0; i <size;i++){
            expresion += aux.getDato().toString() + "\n";
            aux = aux.getPnext();
            }
            return expresion;
        }
        return "Lista vacia";
    }
    
    public void print(){
        if (!isEmpty()){
            Nodo aux = pFirst;
            String expresion = "";
            while(aux != null){
               expresion = expresion + aux.getDato().toString() + "\n";
               aux = aux.getPnext();
            }
            JOptionPane.showMessageDialog(null,expresion);
            
        }else{
            JOptionPane.showMessageDialog(null, "La lista esta vacia");
        }
    }
    
    //Funcion para eliminar al inicio
    public boolean deleteStart(){
        if(!isEmpty()){
            pFirst = pFirst.getPnext();
            size--;
            return true;
        }else{
            return false;
        }
    }
    
    public void deleteFinal(){
        if(!isEmpty()){
            if (getSize()==1) {
                empty();
            }else{
                Nodo pointer = getpFirst();
                while(pointer.getPnext() != null && pointer.getPnext().getPnext()!= null){
                    pointer = pointer.getPnext();
                }
                pointer.setPnext(null);
            }
            size --;
        }  
    }
    
    public void deleteForReference(Object referencia){

        if (search(referencia)) {
            if (pFirst.getDato() == referencia) {
                pFirst = pFirst.getPnext();
            } else{
                Nodo aux = pFirst;
                while(aux.getPnext().getDato() != referencia){
                    aux = aux.getPnext();
                }
                Nodo siguiente = aux.getPnext().getPnext();
                aux.setPnext(siguiente);  
            }
            size--;
        }
    }
    
    public void deleteForPosition(int posicion){

        if(posicion>=0 && posicion<size){
            if(posicion == 0){
                pFirst = pFirst.getPnext();
            }
            else{
                Nodo aux = pFirst;
                for (int i = 0; i < posicion-1; i++) {
                    aux = aux.getPnext();
                }
                Nodo siguiente = aux.getPnext();
                aux.setPnext(siguiente.getPnext());
            }
            size--;
        }
    }
    
    public void editForReference(Object referencia, Object dato){
        if (search(referencia)) {
            Nodo aux = pFirst;
            while(aux.getDato() != referencia){
                aux = aux.getPnext();
            }
            aux.setDato(dato);
        }
    }
    
    //Metodo para editar el valor de un nodo en una posicion
    public void editForPosition(int posicion , Object dato){

        if(posicion>=0 && posicion<size){
            if(posicion == 0){
                pFirst.setDato(dato);
            }
            else{
                Nodo aux = pFirst;

                for (int i = 0; i < posicion; i++) {
                    aux = aux.getPnext();
                }
                aux.setDato(dato);
            }
        }
    }
    
    //Metodo para obtener el valor de un nodo en una determinada posición
    public Object getValue(int posicion){

        if(posicion>=0 && posicion<size){
            
            if (posicion == 0) {
                return pFirst.getDato();
            }else{
                Nodo aux = pFirst;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getPnext();
                }
                return aux.getDato();
            }
        }
        return null;
    }
    
        //Metodo para obtener un nodo en una determinada posición
    public Nodo getNode(int posicion){

        if(posicion>=0 && posicion<size){
            
            if (posicion == 0) {
                return pFirst;
            }else{
                Nodo aux = pFirst;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getPnext();
                }
                return aux;
            }
        }
        return null;
    }
    
    //Metodo para retornar la posicion de un nodo
    public int getIndex (Nodo nodito){
        if (!isEmpty()){
            Nodo aux = pFirst;
            int count = 0;
            while(aux != null){
                
                if (nodito == aux){
                    return count;     //posicion en memoria del nodo
                }
                count ++;
                aux = aux.getPnext();
            }
            return -1;
        }
    return -1;
    }
    
    //Consulta la posición de un elemento en la lista
    public int getPosition(Object referencia){

        if (search(referencia)) {
            
            Nodo aux = pFirst;
            int cont = 0;
            while(referencia != aux.getDato()){
                cont ++;
                aux = aux.getPnext();
            }
            return cont;
        } else {
            return -1;
        }
    }
    
    // Funcion para buscar un elemento en la lista
    public boolean search(Object referencia){
        Nodo aux = pFirst;
        boolean encontrado = false;
        while(aux != null && encontrado != true){
            if (referencia == aux.getDato()){ 
                encontrado = true;
            }
            else{
                aux = aux.getPnext();
            }
        }
        return encontrado;
    }
    
    //Destructor
    public void destructor(){
        pFirst = null;
        size = 0;
    }
    
}
