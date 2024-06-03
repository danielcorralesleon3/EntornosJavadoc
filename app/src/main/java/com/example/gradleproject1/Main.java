package com.example.gradleproject1;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Clase principal que crea una interfaz gráfica con botones para visualizar e insertar datos sobre una base de datos.
 */
public class Main {

    /**
     * Método principal que inicia la aplicación.
     */
    public static void main(String[] args) {
        // URL de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/trabajo_final";

        // Crear la ventana principal
        JFrame frame = new JFrame("Gestión de Campeones");

        // Crear un panel para poner los botones
        JPanel panel = new JPanel();

        // Crear botón para visualizar los campeones
        JButton viewCampeones = new JButton("Visualizar todos los campeones");
        viewCampeones.addActionListener(e -> {
            // Al hacer clic, se abre una nueva ventana con la tabla de campeones
            TablaChamp a = new TablaChamp(url);
        });

        // Crear botón para visualizar campeones filtrados por vida
        JButton filtro = new JButton("Visualizar los campeones filtrados por vida");
        filtro.addActionListener(e -> {
            // Al hacer clic, se abre una nueva ventana con la tabla de campeones filtrados por vida
            FiltroVida a = new FiltroVida(url);
        });

        // Crear botón para visualizar todas las entidades del mapa
        JButton viewEntidades = new JButton("Visualizar todas las entidades del mapa");
        viewEntidades.addActionListener(e -> {
            // Al hacer clic, se abre una nueva ventana con la tabla de entidades del mapa
            TablaEntidades a = new TablaEntidades(url);
        });

        // Crear botón para visualizar todos los items
        JButton viewItems = new JButton("Visualizar todos los ítems");
        viewItems.addActionListener(e -> {
            // Al hacer clic, se abre una nueva ventana con la tabla de ítems
            TablaItems a = new TablaItems(url);
        });

        // Crear botón para visualizar todos los usuarios
        JButton viewUsuarios = new JButton("Visualizar todos los usuarios");
        viewUsuarios.addActionListener(e -> {
            // Al hacer clic, se abre una nueva ventana con la tabla de usuarios
            TablaUsuarios a = new TablaUsuarios(url);
        });

        // Crear botón para insertar un nuevo campeón
        JButton insertCampeones = new JButton("Insertar un campeón");
        insertCampeones.addActionListener(e -> {
            // Al hacer clic, se abre una nueva ventana para insertar un campeón
            InsertarCampeones a = new InsertarCampeones(url);
        });

        // Agregar el panel a la ventana
        frame.add(panel);

        // Agregar todos los botones al panel
        panel.add(viewCampeones);
        panel.add(filtro);
        panel.add(viewEntidades);
        panel.add(viewItems);
        panel.add(viewUsuarios);
        panel.add(insertCampeones);

        // Ajustar el tamaño de la ventana para que se ajuste a los componentes añadidos
        frame.pack();

        // Hacer visible la ventana
        frame.setVisible(true);

        // Cierra la aplicación al cerrar la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
