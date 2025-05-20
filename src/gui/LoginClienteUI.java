package gui;

import javax.swing.*;
import java.awt.*;
import codigo.*;

public class LoginClienteUI extends JFrame {
    
    public LoginClienteUI() {
        setTitle("Login Cliente");
        setSize(300, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel lblTitulo = new JLabel("Ingrese su ID de Cliente:");
        JTextField txtId = new JTextField();
        JButton btnLogin = new JButton("Ingresar");
        JButton btnVolver = new JButton("Volver al Menú");

        add(lblTitulo);
        add(txtId);
        add(btnLogin);
        add(btnVolver);

        // Acción de "Ingresar"
        btnLogin.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                cliente c = clienteDAO.autenticarCliente(id);

                if (c != null) {
                    JOptionPane.showMessageDialog(this, "¡Bienvenido, " + c.getNombre() + "!");
                    dispose();
                    new MenuClienteUI(c);
                } else {
                    int opcion = JOptionPane.showConfirmDialog(this, "No se encontró el cliente. ¿Desea registrarse?", "Cliente no encontrado", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                        RegistroClienteUI registro = new RegistroClienteUI(this);
                        registro.setVisible(true);
                        cliente nuevo = registro.getClienteRegistrado();
                        if (nuevo != null) {
                            clienteDAO.insertarCliente(nuevo);
                            JOptionPane.showMessageDialog(this, "¡Registro exitoso!");
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción de "Volver al Menú"
        btnVolver.addActionListener(e -> {
            dispose(); // cerrar esta ventana
            new MenuUI(); // volver al menú principal
        });

        setVisible(true);
    }
}
