package codigo;

import java.sql.*;

public class clienteDAO 
{
    public static void insertarCliente(cliente c) 
    {
        Connection con = null;
        try 
        {
            con = dbConnection.conectar();
            String sql = "INSERT INTO cliente (ID_CLIENTE, nombre, telefono, direccion) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getId());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getDireccion());

            int filas = ps.executeUpdate();
            if (filas > 0) 
            {
                System.out.println("Cliente registrado con éxito.");
            }
        }
        catch (SQLException e) 
        {
            if (e.getErrorCode() == 1062) 
            { 
                System.out.println("Error: El ID ya está registrado.");
            } 
            else 
            {
                System.out.println("Error de base de datos: " + e.getMessage());
            }
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
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    public static cliente autenticarCliente(int id) 
    {
        Connection con = null;
        try 
        {
            con = dbConnection.conectar();
            String sql = "SELECT * FROM cliente WHERE ID_CLIENTE = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) 
            {
                return new cliente
                (
                    rs.getInt("ID_CLIENTE"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
            }
        } 
        catch (SQLException e) 
        {
            System.out.println("Error de base de datos: " + e.getMessage());
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
                    System.out.println("❌ Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return null;
    }
}