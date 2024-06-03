package com.example.gradleproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que crea una ventana para mostrar una tabla de usuarios desde una base de datos.
 */
public class TablaUsuarios extends JFrame {

    /**
     * Constructor que inicializa la ventana y carga los datos de la base de datos en una tabla.
     * @param url La URL de la base de datos.
     */
    public TablaUsuarios(String url) {
        // Modelo de tabla para gestionar los datos
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        // Añadir columnas al modelo de tabla
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Nivel");
        model.addColumn("Baneado");
        model.addColumn("Campeón más jugado");

        // Conexión a la base de datos y obtención de datos
        try {
            // Establecer la conexión
            Connection connection = DriverManager.getConnection(url, "root", "");
            // Preparar y ejecutar la consulta SQL
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuarios");
            ResultSet resultSet = statement.executeQuery();

            // Procesar los resultados de la consulta
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("ID"));
                row.add(resultSet.getString("Nombre"));
                row.add(resultSet.getString("nivel"));
                row.add(resultSet.getString("baneado"));
                row.add(resultSet.getString("campeon mas jugado"));
                model.addRow(row);
            }

            // Cerrar la conexión
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            // Manejo de errores de base de datos
            System.out.println("Error de BBDD: " + e.getMessage());
        }

        // Añadir la tabla a un JScrollPane para permitir el desplazamiento
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // Configurar la ventana
        setTitle("Tabla de usuarios");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
