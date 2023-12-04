
package GrafoNoDirigido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FormularioGrafoNoDirigido extends JFrame {
    private NodoGrafo primerNodo;

    private JTextField proveedorField;
    private JTextField productoField;

    public FormularioGrafoNoDirigido() {
        // Configuración del formulario
        setTitle("Grafo No Dirigido de Proveedores y Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(5, 2));
        setLocationRelativeTo(null);

        // Campos de texto para proveedor y producto
        JLabel proveedorLabel = new JLabel("Proveedor:");
        proveedorField = new JTextField();
        JLabel productoLabel = new JLabel("Producto:");
        productoField = new JTextField();

        // Botón para agregar relación en el grafo
        JButton agregarRelacionButton = new JButton("Agregar Relación");
        agregarRelacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarRelacion();
            }
        });
        
        JButton salida = new JButton("Salir");
        salida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 dispose();
            }
        });

        // Área de texto para mostrar resultados y la matriz de adyacencia
        JTextArea resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);

        // Agregar componentes al formulario
        add(proveedorLabel);
        add(proveedorField);
        add(productoLabel);
        add(productoField);
        add(agregarRelacionButton);
        add(salida);
        add(resultadoArea);

        // Mostrar el formulario
        setVisible(true);
    }

    private void agregarRelacion() {
        String proveedor = proveedorField.getText();
        String producto = productoField.getText();

        if (!proveedor.isEmpty() && !producto.isEmpty()) {
            // Buscar o crear nodos para el proveedor y el producto
            NodoGrafo nodoProveedor = buscarOCrearNodo(proveedor);
            NodoGrafo nodoProducto = buscarOCrearNodo(producto);

            // Establecer relación en el grafo no dirigido
            nodoProveedor.agregarAdyacente(nodoProducto);
            nodoProducto.agregarAdyacente(nodoProveedor);

            // Mostrar resultados
            mostrarResultados();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private NodoGrafo buscarOCrearNodo(String valor) {
        if (primerNodo == null) {
            // Si el grafo está vacío, crear el primer nodo
            primerNodo = new NodoGrafo(valor);
            return primerNodo;
        }

        // Buscar el nodo con el valor dado
        NodoGrafo nodoActual = primerNodo;
        while (nodoActual != null) {
            if (nodoActual.getValor().equals(valor)) {
                return nodoActual;
            }

            // Moverse al siguiente nodo
            nodoActual = nodoActual.getSiguiente();
        }

        // Si no se encuentra, crear un nuevo nodo
        NodoGrafo nuevoNodo = new NodoGrafo(valor);
        nuevoNodo.setSiguiente(primerNodo);
        primerNodo = nuevoNodo;

        return nuevoNodo;
    }

    private void mostrarResultados() {
        StringBuilder resultado = new StringBuilder("Nodos en el Grafo:\n");

        // Imprimir nodos y sus adyacentes
        NodoGrafo nodoActual = primerNodo;
        while (nodoActual != null) {
            resultado.append(nodoActual).append(": [").append(nodoActual.getAdyacentes()).append("]\n");

            // Moverse al siguiente nodo
            nodoActual = nodoActual.getSiguiente();
        }

        // Obtener la matriz de adyacencia
        int[][] matrizAdyacencia = obtenerMatrizAdyacencia();

        // Mostrar la matriz de adyacencia
        resultado.append("\nMatriz de Adyacencia:\n");
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = 0; j < matrizAdyacencia[i].length; j++) {
                resultado.append(matrizAdyacencia[i][j]).append(" ");
            }
            resultado.append("\n");
        }

        productoField.setText(""); // Limpiar el campo de producto después de agregar la relación

        JOptionPane.showMessageDialog(this, resultado.toString(), "Nodos y Matriz de Adyacencia", JOptionPane.INFORMATION_MESSAGE);
    }

    private int[][] obtenerMatrizAdyacencia() {
        // Obtener nodos y sus adyacentes para la matriz de adyacencia
        NodoGrafo nodoActual = primerNodo;
        List<NodoGrafo> nodos = new ArrayList<>();
        while (nodoActual != null) {
            nodos.add(nodoActual);
            nodoActual = nodoActual.getSiguiente();
        }

        // Construir la matriz de adyacencia
        int[][] matrizAdyacencia = new int[nodos.size()][nodos.size()];

        // Inicializar la matriz con ceros
        for (int i = 0; i < nodos.size(); i++) {
            for (int j = 0; j < nodos.size(); j++) {
                matrizAdyacencia[i][j] = 0;
            }
        }

        // Llenar la matriz de adyacencia con las conexiones existentes
        for (int i = 0; i < nodos.size(); i++) {
            NodoGrafo nodo = nodos.get(i);
            ListaEnlazada adyacentes = nodo.getAdyacentes();
            
            // Marcar las conexiones en la matriz de adyacencia
            NodoListaEnlazada nodoLista = adyacentes.getPrimero();
            while (nodoLista != null) {
                int indiceAdyacente = nodos.indexOf(nodoLista.getNodo());
                matrizAdyacencia[i][indiceAdyacente] = 1;
                nodoLista = nodoLista.getSiguiente();
            }
        }

        return matrizAdyacencia;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioGrafoNoDirigido();
            }
        });
    }
}