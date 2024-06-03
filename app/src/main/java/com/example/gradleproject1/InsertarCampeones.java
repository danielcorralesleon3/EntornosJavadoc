package com.example.gradleproject1;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase que crea una ventana para insertar un nuevo campeón en la base de datos.
 */
class InsertarCampeones extends JFrame {
    private JTextField nombreTexto, vidaTexto, dañoTexto, rolTexto, ajusteTexto, itemMasUtilizadoTexto, mejorBuffTexto;
    private JCheckBox ajustadoCheckBox;

    /**
     * Constructor que inicializa la ventana de inserción de campeón.
     * @param url El parametro es la url de la
     */
    public InsertarCampeones(String url) {
        setTitle("Insertar Campeón");
        setLayout(new GridLayout(9, 2));

        // Inicialización de componentes gráficos
        JLabel nombre = new JLabel("Nombre:");
        nombreTexto = new JTextField();
        JLabel vida = new JLabel("Vida:");
        vidaTexto = new JTextField();
        JLabel daño = new JLabel("Daño:");
        dañoTexto = new JTextField();
        JLabel rol = new JLabel("Rol:");
        rolTexto = new JTextField();
        JLabel ajustado = new JLabel("Ajustado:");
        ajustadoCheckBox = new JCheckBox();
        JLabel ajuste = new JLabel("Ajuste:");
        ajusteTexto = new JTextField();
        JLabel itemMasUtilizado = new JLabel("Item más Utilizado:");
        itemMasUtilizadoTexto = new JTextField();
        JLabel mejorBuff = new JLabel("Mejor Buff:");
        mejorBuffTexto = new JTextField();
        JButton botonInsertar = new JButton("Insertar");

        // Añadir componentes a la ventana
        add(nombre);
        add(nombreTexto);
        add(vida);
        add(vidaTexto);
        add(daño);
        add(dañoTexto);
        add(rol);
        add(rolTexto);
        add(ajustado);
        add(ajustadoCheckBox);
        add(ajuste);
        add(ajusteTexto);
        add(itemMasUtilizado);
        add(itemMasUtilizadoTexto);
        add(mejorBuff);
        add(mejorBuffTexto);
        add(botonInsertar);

        // Acción del botón insertar
        botonInsertar.addActionListener(e -> insertarDatos(url));

        // Configuración de la ventana
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Inserta los datos del nuevo campeón en la base de datos.
     */
    private void insertarDatos(String url) {
        try {
            // Obtener los datos del formulario
            String nombre = nombreTexto.getText();
            int vida = Integer.parseInt(vidaTexto.getText());
            int daño = Integer.parseInt(dañoTexto.getText());
            String rol = rolTexto.getText();
            boolean ajustado = ajustadoCheckBox.isSelected();
            String ajuste = ajusteTexto.getText();
            int itemMasUtilizado = Integer.parseInt(itemMasUtilizadoTexto.getText());
            int mejorBuff = Integer.parseInt(mejorBuffTexto.getText());

            // Consulta a BBDD para insertar un nuevo campeón
            String insert = "INSERT INTO campeones (nombre, vida, daño, rol, ajustado, ajuste, `item mas utilizado`, `mejor buff`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            // Establecer la conexión y ejecutar la consulta
            Connection connection = DriverManager.getConnection(url, "root", "");
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, nombre);
            statement.setInt(2, vida);
            statement.setInt(3, daño);
            statement.setString(4, rol);
            statement.setBoolean(5, ajustado);
            statement.setString(6, ajuste);
            statement.setInt(7, itemMasUtilizado);
            statement.setInt(8, mejorBuff);
            statement.executeUpdate();
        } catch (SQLException e) {
            //Gestionar error de BBDD
            System.out.println("Error de BBDD: " + e.getMessage());
        } catch (NumberFormatException e) {
            //Gestionar error al insertar datos
            System.out.println("Error de formato de número: " + e.getMessage());
        }
    }
}
