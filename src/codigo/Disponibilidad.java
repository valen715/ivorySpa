package codigo;

import java.sql.Date;
import java.sql.Time;

public class Disponibilidad 
{
    private int id;
    private int manicuristaId;
    private String manicuristaNombre;
    private String manicuristaTelefono;
    private Date fecha;
    private Time horaInicio;
    private Time horaFin;
    private double precioSemiSencilla;
    private double precioSemiDiseño;
    private double precioPermanenteSencilla;
    private double precioPermanenteDiseño;
    
    
    public Disponibilidad(int id, int manicuristaId, Date fecha, Time horaInicio, Time horaFin, double precioSemiSencilla, double precioSemiDiseno, double precioPermanenteSencilla, double precioPermanenteDiseno) {
        this.id = id;
        this.manicuristaId = manicuristaId;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.precioSemiSencilla = precioSemiSencilla;
        this.precioSemiDiseño = precioSemiDiseno;
        this.precioPermanenteSencilla = precioPermanenteSencilla;
        this.precioPermanenteDiseño = precioPermanenteDiseno;
    }
    
    public int getId() 
    {
        return id; 
    }
    public void setId(int id) 
    {
        this.id = id; 
    }
    
    public int getManicuristaId() 
    { 
        return manicuristaId; 
    }
    public void setManicuristaId(int manicuristaId) 
    { 
        this.manicuristaId = manicuristaId; 
    }
    
    public String getManicuristaNombre() 
    { 
        return manicuristaNombre; 
    }
    public void setManicuristaNombre(String manicuristaNombre)
    {
        this.manicuristaNombre = manicuristaNombre; 
    }
    
    public Date getFecha() 
    { 
        return fecha; 
    }
    public void setFecha(Date fecha) 
    { 
        this.fecha = fecha; 
    }
    
    public Time getHoraInicio() 
    { 
        return horaInicio; 
    }
    public void setHoraInicio(Time horaInicio) 
    { 
        this.horaInicio = horaInicio; 
    }
    
    public Time getHoraFin() 
    { 
        return horaFin; 
    }
    public void setHoraFin(Time horaFin) 
    { 
        this.horaFin = horaFin; 
    }
    
    public double getPrecioSemiSencilla() 
    { 
        return precioSemiSencilla; 
    }
    public void setPrecioSemiSencilla(double precioSemiSencilla) 
    { 
        this.precioSemiSencilla = precioSemiSencilla; 
    }
    
    public double getPrecioSemiDiseño() 
    { 
        return precioSemiDiseño; 
    }
    public void setPrecioSemiDiseño(double precioSemiDiseno) 
    { 
        this.precioSemiDiseño = precioSemiDiseno; 
    }
    
    public double getPrecioPermanenteSencilla() 
    { 
        return precioPermanenteSencilla; 
    }
    public void setPrecioPermanenteSencilla(double precioPermanenteSencilla) 
    { 
        this.precioPermanenteSencilla = precioPermanenteSencilla; 
    }
    
    public double getPrecioPermanenteDiseño() 
    { 
        return precioPermanenteDiseño; 
    }
    public void setPrecioPermanenteDiseño(double precioPermanenteDiseno) 
    { 
        this.precioPermanenteDiseño = precioPermanenteDiseno; 
    }
}