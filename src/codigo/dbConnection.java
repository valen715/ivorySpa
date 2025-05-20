package codigo;

import java.sql.*;

public class dbConnection 
{
    static String url = "jdbc:mysql://localhost:3306/ivoryspa";
    static String user = "root";
    static String pass = "Root1234!";
    
    public static Connection conectar()
    {
        Connection con = null;
        try
        {
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion exitosa");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }
}
