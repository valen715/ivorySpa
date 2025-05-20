package codigo;

import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

public class DisponibilidadFormulario 
{
    public static Disponibilidad pedirDatosDisponibilidad(int manicuristaId) 
    {
        Scanner sc = new Scanner(System.in);
        Date fecha = null;
        Time horaInicio = null;
        Time horaFin = null;
        double precioSemiSencilla = 0;
        double precioSemiDiseno = 0;
        double precioPermanenteSencilla = 0;
        double precioPermanenteDiseno = 0;

        System.out.println("\n=== REGISTRAR DISPONIBILIDAD ===");

        // Validación de la fecha (YYYY-MM-DD)
        while (fecha == null) 
        {
            try
            {
                System.out.print("Fecha (YYYY-MM-DD): ");
                String fechaStr = sc.nextLine();
                
                if (!fechaStr.matches("\\d{4}-\\d{2}-\\d{2}")) 
                {
                    throw new IllegalArgumentException("Formato incorrecto.");
                }
                fecha = Date.valueOf(fechaStr);
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("❌ Error: Formato de fecha inválido. Use YYYY-MM-DD (ej. 2023-12-25).");
            }
        }

        // Validación de hora de inicio (HH:MM)
        while (horaInicio == null) 
        {
            try 
            {
                System.out.print("Hora de inicio (HH:MM): ");
                String horaStr = sc.nextLine();
                
                if (!horaStr.matches("\\d{2}:\\d{2}")) 
                {
                    throw new IllegalArgumentException("Formato incorrecto.");
                }
                horaInicio = Time.valueOf(horaStr + ":00");
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("❌ Error: Formato de hora inválido. Use HH:MM (ej. 14:30).");
            }
        }
        
        while (horaFin == null) 
        {
            try 
            {
                System.out.print("Hora de fin (HH:MM): ");
                String horaStr = sc.nextLine();
                if (!horaStr.matches("\\d{2}:\\d{2}")) 
                {
                    throw new IllegalArgumentException("Formato incorrecto.");
                }
                horaFin = Time.valueOf(horaStr + ":00");

                if (horaFin.before(horaInicio) || horaFin.equals(horaInicio)) 
                {
                    System.out.println("❌ Error: La hora de fin debe ser posterior a la de inicio.");
                    horaFin = null;
                }
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println("❌ Error: Formato de hora inválido. Use HH:MM (ej. 16:30).");
            }
        }

        precioSemiSencilla = validarPrecio("Precio uñas semi sencillas: ");
        precioSemiDiseno = validarPrecio("Precio uñas semi con diseño: ");
        precioPermanenteSencilla = validarPrecio("Precio uñas permanentes sencillas: ");
        precioPermanenteDiseno = validarPrecio("Precio uñas permanentes con diseño: ");

        return new Disponibilidad
        (
            0, manicuristaId, fecha, horaInicio, horaFin,
            precioSemiSencilla, precioSemiDiseno, precioPermanenteSencilla, precioPermanenteDiseno
        );
    }

    private static double validarPrecio(String mensaje) 
    {
        Scanner sc = new Scanner(System.in);
        double precio = 0;
        while (precio <= 0) 
        {
            try 
            {
                System.out.print(mensaje);
                precio = Double.parseDouble(sc.nextLine());
                
                if (precio <= 0) 
                {
                    System.out.println("❌ Error: El precio debe ser mayor a 0.");
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("❌ Error: Ingrese un número válido (ej. 25.50).");
            }
        }
        return precio;
    }
}