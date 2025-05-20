package gui;

import codigo.cliente;
import codigo.Disponibilidad;
import codigo.DisponibilidadDAO;
import codigo.CitaDAO;
import codigo.cita;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuClienteUI extends JFrame {
    private cliente clienteActual;

    public MenuClienteUI(cliente clienteActual) {
        this.clienteActual = clienteActual;

        setTitle("Menú del Cliente");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnVerManicuristas = new JButton("Ver manicuristas disponibles");
        JButton btnSolicitarCita = new JButton("Solicitar cita");
        JButton btnVerCitas = new JButton("Ver mis citas");
        JButton btnSalir = new JButton("Volver al menú principal");

        add(btnVerManicuristas);
        add(btnSolicitarCita);
        add(btnVerCitas);
        add(btnSalir);

        btnVerManicuristas.addActionListener(e -> mostrarDisponibilidades());
        btnSolicitarCita.addActionListener(e -> solicitarCita());
        btnVerCitas.addActionListener(e -> verCitas());
        btnSalir.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void mostrarDisponibilidades() {
        List<Disponibilidad> disponibilidades = DisponibilidadDAO.obtenerTodasDisponibilidades();

        if (disponibilidades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay disponibilidades registradas.");
        } else {
            StringBuilder sb = new StringBuilder("=== MANICURISTAS DISPONIBLES ===\n\n");

            for (Disponibilidad d : disponibilidades) {
                sb.append(String.format(
                        "ID: %d | Fecha: %s | Hora: %s-%s | Manicurista: %s\n  - Semi sencilla: %.2f\n  - Semi diseño: %.2f\n  - Permanente sencilla: %.2f\n  - Permanente diseño: %.2f\n\n",
                        d.getId(), d.getFecha(), d.getHoraInicio(), d.getHoraFin(), d.getManicuristaNombre(),
                        d.getPrecioSemiSencilla(), 0.0, // Replace with a default value or remove if unnecessary
                        d.getPrecioPermanenteSencilla(), 0.0 // Replace with a default value or remove if unnecessary
                ));
            }

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(450, 300));
            JOptionPane.showMessageDialog(this, scrollPane, "Disponibilidades", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void solicitarCita() {
        List<Disponibilidad> disp = DisponibilidadDAO.obtenerTodasDisponibilidades();

        if (disp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay horarios disponibles.");
        } else {
            new SolicitarCitaUI(clienteActual, disp);
        }
    }

    private void verCitas() {
        List<cita> citas = CitaDAO.obtenerPorCliente(clienteActual.getId());

        if (citas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tienes citas agendadas.");
        } else {
            StringBuilder sb = new StringBuilder("=== TUS CITAS ===\n\n");

            for (cita cita : citas) {
                sb.append(String.format("ID: %d | Fecha: %s | Hora: %s | Servicio: %s | Estado: %s\n",
                        cita.getId(), cita.getFecha(), cita.getHoraInicio(),
                        cita.getTipoServicio(), cita.getEstado()));
            }

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(450, 300));
            JOptionPane.showMessageDialog(this, scrollPane, "Tus Citas", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
