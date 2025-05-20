package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;

public class MenuUI extends JFrame {
    public MenuUI() {
        setTitle("Sistema de Citas de Manicuristas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel label = new JLabel("Seleccione su rol:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        JButton clienteBtn = new JButton("Soy cliente");
        JButton manicuristaBtn = new JButton("Soy manicurista");
        JButton salirBtn = new JButton("Salir");

        panel.add(clienteBtn);
        panel.add(manicuristaBtn);
        panel.add(salirBtn);

        add(panel);

        // Eventos
        clienteBtn.addActionListener(e -> {
            new LoginClienteUI().setVisible(true);
        });
        // manicuristaBtn.addActionListener(e -> new LoginManicuristaUI());
        salirBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuUI());
    }
}
