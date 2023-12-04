
package GrafoNoDirigido;

public class NodoListaEnlazada {
     protected NodoGrafo nodo;
     protected NodoListaEnlazada siguiente;

    public NodoListaEnlazada(NodoGrafo nodo) {
        this.nodo = nodo;
        this.siguiente = null;
    }

    public NodoGrafo getNodo() {
        return nodo;
    }

    public NodoListaEnlazada getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaEnlazada siguiente) {
        this.siguiente = siguiente;
    }
}
