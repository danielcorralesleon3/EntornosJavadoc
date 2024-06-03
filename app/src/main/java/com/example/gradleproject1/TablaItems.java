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
 * Clase que crea una ventana para visualizar todos los items almacenados en la base de datos.
 */
class TablaItems extends JFrame {

    /**
     * Constructor que inicializa la ventana de visualización de items.
     *
     * @param url El parametro es la url de la BBDD
     */
    public TablaItems(String url) {
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        // Añadir columnas a la tabla
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Vida");
        model.addColumn("Daño");
        model.addColumn("Tipo de daño");
        model.addColumn("Ajustado");
        model.addColumn("Ajuste");

        // Conectar a la base de datos y cargar los datos en la tabla
        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM items");
            ResultSet resultSet = statement.executeQuery();

            // Cargar cada fila de resultados en la tabla
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("ID"));
                row.add(resultSet.getString("Nombre"));
                row.add(resultSet.getString("vida"));
                row.add(resultSet.getString("daño"));
                row.add(resultSet.getString("tipo de daño"));
                row.add(resultSet.getString("ajustado"));
                row.add(resultSet.getString("ajuste"));
                model.addRow(row);
            }

        } catch (SQLException e) {
            //Gestionar errores de BBDD
            System.out.println("Error de BBDD: " + e.getMessage());
        }

        // Configurar el JScrollPane y la ventana
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        setTitle("Tabla de items");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
