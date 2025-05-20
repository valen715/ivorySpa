package codigo;

public class cliente
{
    private int id;
    private String nombre;
    private String telefono;
    private String direccion;
    
    public cliente(int id, String nombre, String telefono, String direccion)
    {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    
    /* devuelve el valor actual del id y cambia el valor del id*/
    public int getId()
    {
        return id;
    }
    public void setId(int id) 
    { 
        this.id = id; 
    } 
    
    /* devuelve el valor actual del nombre y cambia el valor del mombre*/
    public String getNombre()
    {
        return nombre;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    /* devuelve el valor actual del telefono y cambia el valor del telefono*/
    public String getTelefono()
    {
        return telefono;
    }
    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }
    
    /* devuelve el valor actual del direccion y cambia el valor de la direccion*/
    public String getDireccion()
    {
        return direccion;
    }
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }
}