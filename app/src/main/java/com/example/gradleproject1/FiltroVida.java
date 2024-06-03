package com.example.gradleproject1;

import java.awt.BorderLayout;
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
 * Clase que crea una ventana para mostrar una tabla de campeones ordenada por vida desde una base de datos.
 */
public class FiltroVida extends JFrame {
    private DefaultTableModel modelo;
    private JTable tabla;
    private Connection conexion;

    /**
     * Constructor que inicializa la ventana y carga los datos de la base de datos en una tabla, ordenados por vida.
     * @param url El parametro es la url de la BBDD
     */
    public FiltroVida(String url) {
        // Inicializar el modelo de la tabla y la tabla
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);

        // Añadir columnas al modelo de tabla
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Vida");
        modelo.addColumn("Daño");
        modelo.addColumn("Rol");
        modelo.addColumn("Ajustado");
        modelo.addColumn("Ajuste");
        modelo.addColumn("Item más utilizado");
        modelo.addColumn("Mejor buff");

        // Conexión a la base de datos y carga de datos
        try {
            conexion = DriverManager.getConnection(url, "root", "");
            cargarDatosTablaOrdenada();
        } catch (SQLException e) {
            System.out.println("Error de BBDD: " + e.getMessage());
        }

        // Añadir la tabla a un JScrollPane para permitir el desplazamiento
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        // Configurar la ventana
        setTitle("Tabla Ordenada por Vida");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Carga los datos de la base de datos en la tabla, ordenados por vida de forma descendente.
     */
    private void cargarDatosTablaOrdenada() {
        modelo.setRowCount(0); // Limpiar las filas existentes en el modelo
        String consulta = "SELECT * FROM campeones ORDER BY vida DESC"; // Consulta para obtener los datos ordenados por vida

        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            // Procesar los resultados de la consulta y añadirlos al modelo de la tabla
            while (resultSet.next()) {
                Vector<String> fila = new Vector<>();
                fila.add(resultSet.getString("ID"));
                fila.add(resultSet.getString("Nombre"));
                fila.add(resultSet.getString("vida"));
                fila.add(resultSet.getString("daño"));
                fila.add(resultSet.getString("Rol"));
                fila.add(resultSet.getString("ajustado"));
                fila.add(resultSet.getString("ajuste"));
                fila.add(resultSet.getString("Item mas utilizado"));
                fila.add(resultSet.getString("mejor buff"));
                modelo.addRow(fila);
            }

            // Cerrar el ResultSet y el PreparedStatement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            //gestionar errores de BBDD
            System.out.println("Error de BBDD: " + e.getMessage());
        }
    }
}
