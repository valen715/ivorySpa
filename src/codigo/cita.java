package codigo;

import java.sql.Date;
import java.sql.Time;

public class cita 
{
    private int id;
    private int clienteId;
    private int manicuristaId;
    private int disponibilidadId;
    private String tipoServicio; 
    private Date fecha;
    private Time hora_inicio;
    private Time hora_fin;
    private String estado;
    
    public cita(int id, int clienteId, int manicuristaId, int disponibilidadId, String tipoServicio, Date fecha, Time hora_inicio, Time hora_fin, String estado) 
    {
        this.id = id;
        this.clienteId = clienteId;
        this.manicuristaId = manicuristaId;
        this.disponibilidadId = disponibilidadId;
        this.tipoServicio = tipoServicio;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.estado = estado;
    }
    
    public int getId() 
    { 
        return id; 
    }
    public void setId(int id) 
    { 
        this.id = id; 
    }
    
    public int getClienteId() 
    { 
        return clienteId; 
    }
    public void setClienteId(int clienteId) 
    { 
        this.clienteId = clienteId; 
    }
    
    public int getManicuristaId() 
    { 
        return manicuristaId; 
    }
    public void setManicuristaId(int manicuristaId) 
    { 
        this.manicuristaId = manicuristaId; 
    }
    
    public int getDisponibilidadId() 
    { 
        return disponibilidadId; 
    }
    public void setDisponibilidadId(int disponibilidadId) 
    { 
        this.disponibilidadId = disponibilidadId; 
    }
    
    public String getTipoServicio() 
    { 
        return tipoServicio; 
    }
    public void setTipoServicio(String tipoServicio) 
    { 
        this.tipoServicio = tipoServicio; 
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
        return hora_inicio;
    }
    public void setHoraInicio(Time hora_inicio) 
    { 
        this.hora_inicio = hora_inicio; 
    }
    
    public Time getHoraFin() 
    { 
        return hora_fin;
    }
    public void setHoraFin(Time hora_fin) 
    { 
        this.hora_fin = hora_fin; 
    }
    
    public String getEstado() 
    { 
        return estado; 
    }
    public void setEstado(String estado) 
    { 
        this.estado = estado; 
    }
}