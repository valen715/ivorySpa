package codigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class manicuristaDAO
{
    public static void insertarManicurista(manicurista m)
    {
         try (Connection con = dbConnection.conectar())
        {
            String sql = "INSERT INTO manicurista (ID_MANICURISTA, nombre, telefono) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, m.getId());
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getTelefono());           
            ps.executeUpdate();
            System.out.println("Manicurista registrado");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static manicurista autenticarManicurista(int id)
    {
        String sql = "SELECT * FROM manicurista WHERE ID_MANICURISTA = ?";
        
        try (Connection con = dbConnection.conectar(); PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) 
            {
                if (rs.next()) 
                {
                    return new manicurista
                    (
                        rs.getInt("ID_MANICURISTA"),
                        rs.getString("nombre"),
                        rs.getString("telefono")
                    );
                }
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
}