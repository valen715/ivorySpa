package codigo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisponibilidadDAO 
{
    
    public static void insertarDisponibilidad(Disponibilidad d) {
        String sql = "INSERT INTO disponibilidades (manicurista_id, fecha, hora_inicio, hora_fin, " + "precio_semi_sencilla, precio_semi_diseño, precio_permanente_sencilla, precio_permanente_diseño) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = dbConnection.conectar(); PreparedStatement ps = con.prepareStatement(sql)) 
        { 
            ps.setInt(1, d.getManicuristaId());
            ps.setDate(2, d.getFecha());
            ps.setTime(3, d.getHoraInicio());
            ps.setTime(4, d.getHoraFin());
            ps.setDouble(5, d.getPrecioSemiSencilla());
            ps.setDouble(6, d.getPrecioSemiDiseño());
            ps.setDouble(7, d.getPrecioPermanenteSencilla());
            ps.setDouble(8, d.getPrecioPermanenteDiseño());
            
            int filas = ps.executeUpdate();
            if (filas > 0) 
            {
                System.out.println("Disponibilidad registrada con éxito");
            }
        } catch (SQLException e) 
        {
            System.err.println("Error al insertar disponibilidad: " + e.getMessage()); 
            e.printStackTrace();
        }
    }
    
    public static List<Disponibilidad> obtenerDisponibilidadPorManicurista(int manicuristaId) 
    {
        List<Disponibilidad> disponibilidades = new ArrayList<>();
        String sql = "SELECT * FROM disponibilidades WHERE manicurista_id = ? ORDER BY fecha, hora_inicio";
        
        try (Connection con = dbConnection.conectar(); PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, manicuristaId);
            
            try (ResultSet rs = ps.executeQuery()) 
            {
                while (rs.next()) 
                {
                    Disponibilidad d = new Disponibilidad
                    (
                        rs.getInt("id"),
                        rs.getInt("manicurista_id"),
                        rs.getDate("fecha"),
                        rs.getTime("hora_inicio"),
                        rs.getTime("hora_fin"),
                        rs.getDouble("precio_semi_sencilla"),
                        rs.getDouble("precio_semi_diseño"),
                        rs.getDouble("precio_permanente_sencilla"),
                        rs.getDouble("precio_permanente_diseño")
                    );
                    disponibilidades.add(d);
                }
            }
        } catch (SQLException e) 
        {
            System.err.println("Error al obtener disponibilidad por manicurista: " + e.getMessage());
            e.printStackTrace();
        }
        return disponibilidades;
    }
    
    public static List<Disponibilidad> obtenerTodasDisponibilidades() 
    {
        List<Disponibilidad> disponibilidades = new ArrayList<>();
        String sql = "SELECT d.*, m.nombre AS manicurista_nombre FROM disponibilidades d " + "JOIN manicurista m ON d.manicurista_id = m.ID_MANICURISTA " + "WHERE d.fecha >= CURDATE() " + "AND NOT EXISTS ( " + "    SELECT 1 FROM citas c " + "    WHERE c.disponibilidad_id = d.id AND c.estado = 'aceptada' " + ") " + "ORDER BY d.fecha, d.hora_inicio";
        
        try (Connection con = dbConnection.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) 
            {
                Disponibilidad d = new Disponibilidad
                (
                    rs.getInt("id"),
                    rs.getInt("manicurista_id"),
                    rs.getDate("fecha"),
                    rs.getTime("hora_inicio"),
                    rs.getTime("hora_fin"),
                    rs.getDouble("precio_semi_sencilla"),
                    rs.getDouble("precio_semi_diseño"),
                    rs.getDouble("precio_permanente_sencilla"),
                    rs.getDouble("precio_permanente_diseño")
                );
               
                d.setManicuristaNombre(rs.getString("manicurista_nombre"));
                
                disponibilidades.add(d);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las disponibilidades: " + e.getMessage());
            e.printStackTrace();
        }
        return disponibilidades;
    }
    
    public static Disponibilidad obtenerDisponibilidadPorId(int id) 
    {
        String sql = "SELECT * FROM disponibilidades WHERE id = ?";
        
        try (Connection con = dbConnection.conectar(); PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) 
            {
                if (rs.next()) 
                {
                    return new Disponibilidad
                    ( 
                        rs.getInt("id"),
                        rs.getInt("manicurista_id"),
                        rs.getDate("fecha"),
                        rs.getTime("hora_inicio"),
                        rs.getTime("hora_fin"),
                        rs.getDouble("precio_semi_sencilla"),
                        rs.getDouble("precio_semi_diseño"),
                        rs.getDouble("precio_permanente_sencilla"),
                        rs.getDouble("precio_permanente_diseño")
                    );
                }
            }
        } catch (SQLException e) 
        {
            System.err.println("Error al obtener disponibilidad por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public static boolean existeDisponibilidad(int disponibilidadId) 
    {
    String sql = "SELECT COUNT(*) FROM disponibilidades WHERE id = ?";
    
    try (Connection con = dbConnection.conectar(); PreparedStatement ps = con.prepareStatement(sql)) 
    {
        ps.setInt(1, disponibilidadId);
        ResultSet rs = ps.executeQuery();
        
        return rs.next() && rs.getInt(1) > 0;
    } 
    catch (SQLException e) 
    {
        System.out.println("Error al verificar disponibilidad: " + e.getMessage());
        return false;
    }
}
    
    public static boolean eliminarDisponibilidad(int id) 
    {
        String sql = "DELETE FROM disponibilidades WHERE id = ?";
        
        try (Connection con = dbConnection.conectar(); PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
            
        } 
        catch (SQLException e) 
        {
            System.err.println("Error al eliminar disponibilidad: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}