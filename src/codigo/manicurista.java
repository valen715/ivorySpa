package codigo;

public class manicurista
{
    private int id;
    private String nombre;
    private String telefono;
    
    public manicurista(int id, String nombre, String telefono)
    {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    public int getId()
    {
        return id;
    }
    public void setID(int id)
    {
        this.id = id;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public String getTelefono()
    {
        return telefono;
    }
    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }
}

