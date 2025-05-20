package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import codigo.cliente;

public class RegistroClienteUI extends JDialog{
    private cliente clienteRegistrado = null;

    public RegistroClienteUI(JFrame parent) {
        super(parent, "Registro de Cliente", true);
        setSize(350, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblId = new JLabel("ID:");
        JTextField txtId = new JTextField();

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();

        JLabel lblDireccion = new JLabel("Dirección:");
        JTextField txtDireccion = new JTextField();

        JButton btnRegistrar = new JButton("Registrar");

        add(lblId); add(txtId);
        add(lblNombre); add(txtNombre);
        add(lblTelefono); add(txtTelefono);
        add(lblDireccion); add(txtDireccion);
        add(new JLabel()); add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                String nombre = txtNombre.getText().trim();
                String telefono = txtTelefono.getText().trim();
                String direccion = txtDireccion.getText().trim();

                if (nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                clienteRegistrado = new cliente(id, nombre, telefono, direccion);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public cliente getClienteRegistrado() {
        return clienteRegistrado;
    }
}
