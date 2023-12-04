
package GrafoDirigido;

public class ListaEnlazada {
    
    private NodoListaEnlazada primero;

    public ListaEnlazada() {
        this.primero = null;
    }

    public NodoListaEnlazada getPrimero() {
        return primero;
    }

    public void agregarAlFinal(NodoListaEnlazada nuevoNodo) {
        if (primero == null) {
            primero = nuevoNodo;
        } else {
            NodoListaEnlazada ultimo = obtenerUltimoNodo();
            ultimo.setSiguiente(nuevoNodo);
        }
    }

    private NodoListaEnlazada obtenerUltimoNodo() {
        NodoListaEnlazada actual = primero;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        return actual;
    }
    
     @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        NodoListaEnlazada actual = primero;
        while (actual != null) {
            resultado.append(actual.getNodo()).append(" ");
            actual = actual.getSiguiente();
        }
        return resultado.toString().trim();
    }
}