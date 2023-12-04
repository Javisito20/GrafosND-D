
package GrafoNoDirigido;

public class ListaEnlazada {
    protected NodoListaEnlazada primero;

    public void insertarAlFinal(NodoGrafo nodo) {
        if (primero == null) {
            primero = new NodoListaEnlazada(nodo);
        } else {
            NodoListaEnlazada actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(new NodoListaEnlazada(nodo));
        }
    }

    public NodoListaEnlazada getPrimero() {
        return primero;
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
