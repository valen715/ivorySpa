package codigo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO 
{

    public static boolean solicitarCita(cita nuevaCita) 
    {
        Connection con = null;
        
        try 
        {
            con = dbConnection.conectar();
            
            if (!DisponibilidadDAO.existeDisponibilidad(nuevaCita.getDisponibilidadId())) 
            {
                System.out.println("Error: La disponibilidad seleccionada no existe");
                return false;
            }

            if (existeTraslape(con, nuevaCita)) 
            {
                System.out.println("Error: El manicurista ya tiene una cita en ese horario");
                return false;
            }

            String sql = "INSERT INTO citas (cliente_id, manicurista_id, disponibilidad_id, " + "tipo_servicio, fecha, hora_inicio, hora_fin, estado) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement ps = con.prepareStatement(sql)) 
            {
                ps.setInt(1, nuevaCita.getClienteId());
                ps.setInt(2, nuevaCita.getManicuristaId());
                ps.setInt(3, nuevaCita.getDisponibilidadId());
                ps.setString(4, nuevaCita.getTipoServicio());
                ps.setDate(5, nuevaCita.getFecha());
                ps.setTime(6, nuevaCita.getHoraInicio());
                ps.setTime(7, nuevaCita.getHoraFin());
                ps.setString(8, "pendiente");
                
                int filas = ps.executeUpdate();
                
                if (filas > 0) 
                {
                    System.out.println("Cita agendada exitosamente");
                    return true;
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error al agendar cita: " + e.getMessage());
        } 
        finally 
        {
            if (con != null) 
            {
                try 
                {
                    con.close();
                } 
                catch (SQLException e) 
                {
                    System.out.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
        return false;
    }

    private static boolean existeTraslape(Connection con, cita cita) throws SQLException 
    {
        String sql = "SELECT COUNT(*) FROM citas " + "WHERE manicurista_id = ? " + "AND fecha = ? " +"AND estado != 'rechazada' " + "AND ((hora_inicio < ? AND hora_fin > ?) OR " + "(hora_inicio BETWEEN ? AND ?) OR " + "(hora_fin BETWEEN ? AND ?))";                
        
        try (PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, cita.getManicuristaId());
            ps.setDate(2, cita.getFecha());
            ps.setTime(3, cita.getHoraFin());
            ps.setTime(4, cita.getHoraInicio());
            ps.setTime(5, cita.getHoraInicio());
            ps.setTime(6, cita.getHoraFin());
            ps.setTime(7, cita.getHoraInicio());
            ps.setTime(8, cita.getHoraFin());
            
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    public static boolean actualizarEstado(int citaId, String estado) 
    {
        String sql = "UPDATE citas SET estado = ? WHERE id = ?";
        
        try (Connection con = dbConnection.conectar(); PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, estado);
            ps.setInt(2, citaId);
            
            return ps.executeUpdate() > 0;
        } 
        catch (SQLException e) 
        {
            System.out.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }

    public static List<cita> obtenerPorCliente(int clienteId) 
    {
        List<cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM citas WHERE cliente_id = ? ORDER BY fecha, hora_inicio";
        
        try (Connection con = dbConnection.conectar(); PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, clienteId);
            
            try (ResultSet rs = ps.executeQuery()) 
            {
                while (rs.next()) 
                {
                    citas.add(mapearCita(rs));
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error al obtener citas: " + e.getMessage());
        }
        return citas;
    }

    public static List<cita> obtenerPorManicuristaYEstado(int manicuristaId, String estado) 
    {
        List<cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM citas WHERE manicurista_id = ? AND estado = ? ORDER BY fecha, hora_inicio";
        
        try (Connection con = dbConnection.conectar(); PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, manicuristaId);
            ps.setString(2, estado);
            
            try (ResultSet rs = ps.executeQuery()) 
            {
                while (rs.next()) 
                {
                    citas.add(mapearCita(rs));
                }
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("❌ Error al obtener citas: " + e.getMessage());
        }
        return citas;
    }

    private static cita mapearCita(ResultSet rs) throws SQLException 
    {
        return new cita(
            rs.getInt("id"),
            rs.getInt("cliente_id"),
            rs.getInt("manicurista_id"),
            rs.getInt("disponibilidad_id"),
            rs.getString("tipo_servicio"),
            rs.getDate("fecha"),
            rs.getTime("hora_inicio"),
            rs.getTime("hora_fin"),
            rs.getString("estado")
        );
    }
}