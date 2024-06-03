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
 * Clase que crea una ventana para visualizar todos los campeones almacenados en la base de datos MySQL.
 */
class TablaChamp extends JFrame {

    /**
     * Constructor que inicializa la ventana de visualización de campeones.
     * @param url El parametro es la URL de conexión a la base de datos MySQL
     */
    public TablaChamp(String url) {
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        // Añadir columnas a la tabla
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Vida");
        model.addColumn("Daño");
        model.addColumn("Rol");
        model.addColumn("Ajustado");
        model.addColumn("Ajuste");
        model.addColumn("Item más utilizado");
        model.addColumn("Mejor buff");

        // Conectar a la base de datos y cargar los datos en la tabla
        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM campeones");
            ResultSet resultSet = statement.executeQuery();

            // Cargar cada fila de resultados en la tabla
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("ID"));
                row.add(resultSet.getString("Nombre"));
                row.add(resultSet.getString("vida"));
                row.add(resultSet.getString("daño"));
                row.add(resultSet.getString("Rol"));
                row.add(resultSet.getString("ajustado"));
                row.add(resultSet.getString("ajuste"));
                row.add(resultSet.getString("Item mas utilizado"));
                row.add(resultSet.getString("mejor buff"));
                model.addRow(row);
            }

        } catch (SQLException e) {
            //Gestionar error de BBDD
            System.out.println("Error de BBDD: " + e.getMessage());
        }

        // Configurar el JScrollPane y la ventana
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        // Configurar la ventana
        setTitle("Tabla de Campeones");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
