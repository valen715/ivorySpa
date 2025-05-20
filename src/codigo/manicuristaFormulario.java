package codigo;

import java.util.Scanner;

public class manicuristaFormulario
{
    public static manicurista pedirDatos()
    {
        Scanner sc = new Scanner(System.in);
        
        int id;
        String nombre;
        String telefono;
 
        System.out.println("===Registro de Manicurista===");
        System.out.print("Ingrese su Numero de documento: ");
        id = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Ingrese su Nombre: ");
        nombre = sc.nextLine();
        
        System.out.print("Ingrese su Numero de Telefono: ");
        telefono = sc.nextLine();
        
        return new manicurista(id, nombre, telefono);
    }
}
