package codigo;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class CitaFormulario 
{
    public static cita pedirDatosCita(int clienteId, List<Disponibilidad> disponibilidades) 
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n=== Solicitar Cita ===");
    
        System.out.println("\nHorarios disponibles:");
        for (int i = 0; i < disponibilidades.size(); i++) 
        {
            Disponibilidad d = disponibilidades.get(i);
            System.out.printf("%d. Fecha: %s, Hora: %s a %s\n", i+1, d.getFecha(), d.getHoraInicio(), d.getHoraFin());
            System.out.printf("   Precios:\n");
            System.out.printf("   - Semi sencilla: %.2f\n", d.getPrecioSemiSencilla());
            System.out.printf("   - Semi diseño: %.2f\n", d.getPrecioSemiDiseño());
            System.out.printf("   - Permanente sencilla: %.2f\n", d.getPrecioPermanenteSencilla());
            System.out.printf("   - Permanente diseño: %.2f\n\n", d.getPrecioPermanenteDiseño());
        }
        
        System.out.print("Seleccione un horario (número): ");
        int seleccion = sc.nextInt() - 1;
        sc.nextLine();
        
        if (seleccion < 0 || seleccion >= disponibilidades.size()) {
            System.out.println("Selección inválida");
            return null;
        }
        
        Disponibilidad dispSeleccionada = disponibilidades.get(seleccion);
        
        System.out.println("\nTipos de servicio:");
        System.out.println("1. Uñas semipermanentes sencillas");
        System.out.println("2. Uñas semipermanentes con diseño");
        System.out.println("3. Uñas permanentes sencillas");
        System.out.println("4. Uñas permanentes con diseño");
        System.out.print("Seleccione el tipo de servicio: ");
        int tipoServicio = sc.nextInt();
        sc.nextLine();
        
        String servicio;
        switch(tipoServicio) 
        {
            case 1: servicio = "semi_sencilla"; break;
            case 2: servicio = "semi_diseno"; break;
            case 3: servicio = "permanente_sencilla"; break;
            case 4: servicio = "permanente_diseno"; break;
            default: 
                System.out.println("Opción inválida");
                return null;
        }
        return new cita(0, clienteId, dispSeleccionada.getManicuristaId(), dispSeleccionada.getId(), servicio, dispSeleccionada.getFecha(), dispSeleccionada.getHoraInicio(), dispSeleccionada.getHoraFin(), "pendiente");
    }
}
