package codigo;

import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void mostrarMenuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            try {
                System.out.println("\n=== MENÚ PRINCIPAL ===");
                System.out.println("1. Soy cliente");
                System.out.println("2. Soy manicurista");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        menuCliente();
                        break;
                    case 2:
                        loginManicurista();
                        break;
                    case 3:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido (1, 2 o 3).");
            }
        } while (opcion != 3);
    }

    private static void menuCliente() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        cliente c = null;

        while (c == null) {
            try {
                System.out.println("\n=== LOGIN CLIENTE ===");
                System.out.print("Ingrese su ID (número de documento): ");
                int id = Integer.parseInt(sc.nextLine());
                c = clienteDAO.autenticarCliente(id);

                if (c == null) {
                    System.out.println("\n️ Cliente no registrado. Complete el formulario:");
                    c = clienteFormulario.pedirDatos();
                    clienteDAO.insertarCliente(c);
                    System.out.println("Registro exitoso. Bienvenido, " + c.getNombre());
                } else {
                    System.out.println("Bienvenido de nuevo, " + c.getNombre());
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: El ID debe ser un número.");
            }
        }

        do {
            try {
                System.out.println("\n=== MENÚ CLIENTE ===");
                System.out.println("1. Ver manicuristas disponibles");
                System.out.println("2. Solicitar cita");
                System.out.println("3. Ver mis citas");
                System.out.println("4. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        List<Disponibilidad> disponibilidades = DisponibilidadDAO.obtenerTodasDisponibilidades();

                        if (disponibilidades.isEmpty()) {
                            System.out.println("No hay disponibilidades registradas.");
                        } else {
                            System.out.println("\n=== MANICURISTAS DISPONIBLES ===");

                            for (Disponibilidad d : disponibilidades) {
                                System.out.printf("ID: %d | Fecha: %s | Hora: %s-%s | Manicurista: %s\n", d.getId(),
                                        d.getFecha(), d.getHoraInicio(), d.getHoraFin(), d.getManicuristaNombre());
                            }
                        }
                        break;
                    case 2:
                        List<Disponibilidad> disp = DisponibilidadDAO.obtenerTodasDisponibilidades();

                        if (disp.isEmpty()) {
                            System.out.println("No hay horarios disponibles.");
                        } else {
                            cita nuevaCita = CitaFormulario.pedirDatosCita(c.getId(), disp);

                            if (nuevaCita != null) {
                                CitaDAO.solicitarCita(nuevaCita);
                            }
                        }
                        break;
                    case 3:
                        List<cita> citas = CitaDAO.obtenerPorCliente(c.getId());

                        if (citas.isEmpty()) {
                            System.out.println("No tienes citas agendadas.");
                        } else {
                            System.out.println("\n=== TUS CITAS ===");

                            for (cita cita : citas) {
                                System.out.printf("ID: %d | Fecha: %s | Hora: %s | Servicio: %s | Estado: %s\n",
                                        cita.getId(), cita.getFecha(), cita.getHoraInicio(), cita.getHoraFin(),
                                        cita.getTipoServicio(), cita.getEstado());
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido (1-4).");
            }
        } while (opcion != 4);
    }

    private static void menuManicurista(int manicuristaId) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        manicurista m = manicuristaDAO.autenticarManicurista(manicuristaId);
    if (m == null) {
        System.out.println("\n️ Manicurista no registrado. Complete el formulario:");
        m = manicuristaFormulario.pedirDatos();
        manicuristaDAO.insertarManicurista(m);
        System.out.println("Registro exitoso. Bienvenido, " + m.getNombre());
    } else {
        System.out.println("Bienvenido de nuevo, " + m.getNombre());
    }        

        do {
            try {
                System.out.println("\n=== MENÚ MANICURISTA ===");
                System.out.println("1. Registrar disponibilidad");
                System.out.println("2. Ver mis disponibilidades");
                System.out.println("3. Ver citas pendientes");
                System.out.println("4. Aceptar cita");
                System.out.println("5. Rechazar cita");
                System.out.println("6. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        Disponibilidad nuevaDisp = DisponibilidadFormulario.pedirDatosDisponibilidad(m.getId());

                        if (nuevaDisp != null) {
                            DisponibilidadDAO.insertarDisponibilidad(nuevaDisp);
                        }
                        break;
                    case 2:
                        List<Disponibilidad> disponibilidades = DisponibilidadDAO
                                .obtenerDisponibilidadPorManicurista(m.getId());

                        if (disponibilidades.isEmpty()) {
                            System.out.println("No hay disponibilidades registradas.");
                        } else {
                            System.out.println("\n=== TUS DISPONIBILIDADES ===");

                            for (Disponibilidad d : disponibilidades) {
                                System.out.printf("ID: %d | Fecha: %s | Hora: %s-%s\n", d.getId(), d.getFecha(),
                                        d.getHoraInicio(), d.getHoraFin());
                            }
                        }
                        break;
                    case 3:
                        List<cita> citasPendientes = CitaDAO.obtenerPorManicuristaYEstado(m.getId(), "pendiente");

                        if (citasPendientes.isEmpty()) {
                            System.out.println("No hay citas pendientes.");
                        } else {
                            System.out.println("\n=== CITAS PENDIENTES ===");

                            for (cita cita : citasPendientes) {
                                System.out.printf("ID: %d | Fecha: %s | Hora: %s | Servicio: %s\n", cita.getId(),
                                        cita.getFecha(), cita.getHoraInicio(), cita.getHoraFin(),
                                        cita.getTipoServicio());
                            }
                        }
                        break;
                    case 4:
                        actualizarCita(manicuristaId, "aceptada");
                        break;
                    case 6:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido (1-6).");
            }
        } while (opcion != 6);
    }

    private static void actualizarCita(int manicuristaId, String nuevoEstado) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID de la cita a " + nuevoEstado + ": ");
        int citaId = scanner.nextInt();

        boolean actualizado = CitaDAO.actualizarEstado(citaId, nuevoEstado);
        if (actualizado) {
            System.out.println("Cita " + nuevoEstado + " con éxito.");
        } else {
            System.out.println("No se pudo actualizar la cita.");
        }
    }    

    private static void loginManicurista() {
        Scanner sc = new Scanner(System.in);
        int id = -1;
    
        while (id == -1) {
            try {
                System.out.println("\n=== LOGIN MANICURISTA ===");
                System.out.print("Ingrese su ID (número de documento): ");
                id = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: El ID debe ser un número.");
            }
        }
    
        menuManicurista(id);
    }
    
}
