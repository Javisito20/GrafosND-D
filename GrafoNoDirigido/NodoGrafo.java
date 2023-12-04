
package GrafoNoDirigido;

public class NodoGrafo {
    protected String valor;
    protected NodoGrafo siguiente;
    protected ListaEnlazada adyacentes;

    public NodoGrafo(String valor) {
        this.valor = valor;
        this.siguiente = null;
        this.adyacentes = new ListaEnlazada();
    }

    public String getValor() {
        return valor;
    }

    public NodoGrafo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoGrafo siguiente) {
        this.siguiente = siguiente;
    }

    public ListaEnlazada getAdyacentes() {
        return adyacentes;
    }

    public void agregarAdyacente(NodoGrafo nodo) {
        adyacentes.insertarAlFinal(nodo);
    }

    @Override
    public String toString() {
        return valor;
    }
}
