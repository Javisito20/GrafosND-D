
package GrafoDirigido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FormularioGrafoDirigido extends JFrame {
    private NodoGrafo primerNodo;

    private JTextField clienteField;
    private JTextField pedidoField;

    public FormularioGrafoDirigido() {
        // Configuración del formulario
        setTitle("Grafo Dirigido de Gestion de Pedidos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(5, 2));
        setLocationRelativeTo(null);

        // Campos de texto para cliente y pedido
        JLabel clienteLabel = new JLabel("Cliente:");
        clienteField = new JTextField();
        JLabel pedidoLabel = new JLabel("Pedido:");
        pedidoField = new JTextField();

        // Botón para agregar relación en el grafo
        JButton agregarRelacionButton = new JButton("Agregar Pedido");
        agregarRelacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPedido();
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
        add(clienteLabel);
        add(clienteField);
        add(pedidoLabel);
        add(pedidoField);
        add(agregarRelacionButton);
        add(salida);
        add(resultadoArea);

        // Mostrar el formulario
        setVisible(true);
    }

    private void agregarPedido() {
        String cliente = clienteField.getText();
        String pedido = pedidoField.getText();

        if (!cliente.isEmpty() && !pedido.isEmpty()) {
            // Buscar o crear nodos para el cliente y el pedido
            NodoGrafo nodoCliente = buscarOCrearNodo(cliente);
            NodoGrafo nodoPedido = buscarOCrearNodo(pedido);

            // Establecer relación en el grafo dirigido (de cliente a pedido)
            nodoCliente.agregarAdyacente(nodoPedido);

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
        StringBuilder resultado = new StringBuilder("Clientes y sus Pedidos:\n");

        // Imprimir clientes y sus pedidos
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

        pedidoField.setText(""); // Limpiar el campo de pedido después de agregar la relación

        JOptionPane.showMessageDialog(this, resultado.toString(), "Clientes y Pedidos", JOptionPane.INFORMATION_MESSAGE);
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
                new FormularioGrafoDirigido();
            }
        });
    }
}


