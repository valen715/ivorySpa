package gui;

import codigo.Disponibilidad;
import codigo.CitaDAO;
import codigo.cita;
import codigo.cliente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SolicitarCitaUI extends JFrame {
    private JComboBox<String> comboHorarios;
    private JRadioButton semiSencilla, semiDiseno, permanenteSencilla, permanenteDiseno;
    private ButtonGroup grupoServicios;
    private JButton btnConfirmar;

    private cliente clienteActual;
    private List<Disponibilidad> disponibilidades;

    public SolicitarCitaUI(cliente clienteActual, List<Disponibilidad> disponibilidades) {
        this.clienteActual = clienteActual;
        this.disponibilidades = disponibilidades;

        setTitle("Solicitar Cita");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior: ComboBox con horarios
        JPanel panelHorarios = new JPanel(new BorderLayout());
        panelHorarios.setBorder(BorderFactory.createTitledBorder("Seleccione un horario disponible"));

        comboHorarios = new JComboBox<>();
        for (int i = 0; i < disponibilidades.size(); i++) {
            Disponibilidad d = disponibilidades.get(i);
            String texto = String.format("ID %d | Fecha: %s, Hora: %s-%s | Manicurista: %s",
                    d.getId(), d.getFecha(), d.getHoraInicio(), d.getHoraFin(), d.getManicuristaNombre());
            comboHorarios.addItem(texto);
        }

        panelHorarios.add(comboHorarios, BorderLayout.CENTER);

        // Panel medio: tipos de servicio
        JPanel panelServicios = new JPanel(new GridLayout(4, 1));
        panelServicios.setBorder(BorderFactory.createTitledBorder("Tipo de servicio"));

        semiSencilla = new JRadioButton("Semi permanente sencilla");
        semiDiseno = new JRadioButton("Semi permanente con diseño");
        permanenteSencilla = new JRadioButton("Permanente sencilla");
        permanenteDiseno = new JRadioButton("Permanente con diseño");

        grupoServicios = new ButtonGroup();
        grupoServicios.add(semiSencilla);
        grupoServicios.add(semiDiseno);
        grupoServicios.add(permanenteSencilla);
        grupoServicios.add(permanenteDiseno);

        panelServicios.add(semiSencilla);
        panelServicios.add(semiDiseno);
        panelServicios.add(permanenteSencilla);
        panelServicios.add(permanenteDiseno);

        // Panel inferior: botón confirmar
        JPanel panelBoton = new JPanel();
        btnConfirmar = new JButton("Confirmar cita");
        panelBoton.add(btnConfirmar);

        btnConfirmar.addActionListener(e -> registrarCita());

        add(panelHorarios, BorderLayout.NORTH);
        add(panelServicios, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void registrarCita() {
        int indexSeleccionado = comboHorarios.getSelectedIndex();
        if (indexSeleccionado < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un horario.");
            return;
        }

        Disponibilidad seleccionada = disponibilidades.get(indexSeleccionado);

        String tipoServicio = null;
        if (semiSencilla.isSelected()) tipoServicio = "Semi permanente sencilla";
        else if (semiDiseno.isSelected()) tipoServicio = "Semi permanente con diseño";
        else if (permanenteSencilla.isSelected()) tipoServicio = "Permanente sencilla";
        else if (permanenteDiseno.isSelected()) tipoServicio = "Permanente con diseño";

        if (tipoServicio == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de servicio.");
            return;
        }

        cita nueva = new cita(
                0,
                clienteActual.getId(),
                seleccionada.getManicuristaId(),
                seleccionada.getId(),
                tipoServicio,
                seleccionada.getFecha(),
                seleccionada.getHoraInicio(),
                seleccionada.getHoraFin(),
                "pendiente"
        );

        CitaDAO.solicitarCita(nueva);
        JOptionPane.showMessageDialog(this, "✅ Cita registrada con éxito.");
        dispose();
    }
}
