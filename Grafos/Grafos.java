package Grafos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GrafoNoDirigido.FormularioGrafoNoDirigido;
import GrafoDirigido.FormularioGrafoDirigido;

public class Grafos extends JFrame {
    public Grafos() {
        // Configuración del formulario
        setTitle("Grafos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        // Crear botones
        JButton boton1 = new JButton("Grafo no dirigido");
        JButton boton2 = new JButton("Grafo dirigido");

        // Agregar ActionListener al primer botón
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] args = null;
                FormularioGrafoNoDirigido.main(args);
            }
        });
        
         boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] args = null;
                FormularioGrafoDirigido.main(args);
            }
        });
        
        
        // Agregar botones al formulario
        add(boton1);
        add(boton2);

        // Mostrar el formulario
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Grafos::new);
    }
}

