package codigo;

import java.util.Scanner;

public class clienteFormulario 
{
    public static cliente pedirDatos()
    {
        Scanner sc = new Scanner(System.in);
        int id = 0;
        String nombre = "";
        String telefono = "";
        String direccion = "";

        System.out.println("\n=== REGISTRO DE CLIENTE ===");

        while (true)
        {
            try 
            {
                System.out.print("Ingrese su número de documento (ID): ");
                id = Integer.parseInt(sc.nextLine());
                break;
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }

        while (nombre.trim().isEmpty()) 
        {
            System.out.print("Ingrese su nombre: ");
            nombre = sc.nextLine();
            
            if (nombre.trim().isEmpty()) 
            {
                System.out.println("❌ Error: El nombre no puede estar vacío.");
            }
        }

        
        while (telefono.trim().isEmpty()) 
        {
            System.out.print("Ingrese su teléfono: ");
            telefono = sc.nextLine();
            
            if (telefono.trim().isEmpty()) 
            {
                System.out.println("Error: El teléfono no puede estar vacío.");
            }
        }

        while (direccion.trim().isEmpty()) 
        {
            System.out.print("Ingrese su dirección: ");
            direccion = sc.nextLine();
            
            if (direccion.trim().isEmpty()) 
            {
                System.out.println("Error: La dirección no puede estar vacía.");
            }
        }
        return new cliente(id, nombre, telefono, direccion);
    }
}