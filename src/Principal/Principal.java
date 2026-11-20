package principal;

import modelo.Paciente;
import modelo.CitaMedica;
import modelo.ExpedienteClinico;
import modelo.CitaFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Patrón Singleton para Bitácora de Auditoría Centralizada (Ley N° 29733)
class AlmacenSeguroSingleton {
    private static AlmacenSeguroSingleton instancia;
    private List<String> bitacora;

    private AlmacenSeguroSingleton() {
        bitacora = new ArrayList<>();
    }

    public static AlmacenSeguroSingleton getInstancia() {
        if (instancia == null) {
            instancia = new AlmacenSeguroSingleton();
        }
        return instancia;
    }

    public void registrarEvento(String evento) {
        bitacora.add(evento);
        System.out.println("[AUDITORIA SINGLETON]: " + evento);
    }

    public void mostrarBitacora() {
        System.out.println("\n--- BITACORA DE AUDITORIA Y ACCESOS  ---");
        if (bitacora.isEmpty()) {
            System.out.println("No hay eventos registrados.");
        } else {
            bitacora.forEach(e -> System.out.println("-> " + e));
        }
    }
}

public class Principal {
    private static List<Paciente> listaPacientes = new ArrayList<>();
    private static List<CitaMedica> listaCitas = new ArrayList<>();
    private static List<ExpedienteClinico> listaExpedientes = new ArrayList<>();

    public static void main(String[] args) {
        AlmacenSeguroSingleton auditoria = AlmacenSeguroSingleton.getInstancia();
        auditoria.registrarEvento("Inicio de sesion en el sistema Centro de Salud San Sebastian");

        // Carga inicial de datos mediante CitaFactory
        try {
            listaPacientes.add(new Paciente("72839401", "Carlos Mendoza", "HC-1001"));
            listaPacientes.add(new Paciente("45892013", "Maria Fernandez", "HC-1002"));
            listaPacientes.add(new Paciente("10293847", "Juan Perez", "HC-1003"));

            // Uso del Patrón Factory para instanciar citas
            listaCitas.add(CitaFactory.crearCita("C-01", "72839401", "2026-11-20 09:00", "Programada"));
            listaCitas.add(CitaFactory.crearCita("C-02", "45892013", "2026-11-20 10:30", "Atendida"));
            listaCitas.add(CitaFactory.crearCita("C-03", "72839401", "2026-11-21 11:00", "Programada"));

            listaExpedientes.add(new ExpedienteClinico("EXP-01", "72839401", "Hipertensión Arterial - Tratamiento A"));
        } catch (Exception e) {
            System.out.println("Error al cargar datos iniciales: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            try {
                System.out.println("\n=======================================================");
                System.out.println("  CENTRO DE SALUD SAN SEBASTIAN");
                System.out.println("=======================================================");
                System.out.println("1. Registrar nuevo paciente");
                System.out.println("2. Registrar nueva cita ");
                System.out.println("3. Listar pacientes ");
                System.out.println("4. Buscar citas por DNI ");
                System.out.println("5. Reporte de Citas Programadas ");
                System.out.println("6. Ver Bitacora de Auditoria ");
                System.out.println("7. Salir");
                System.out.print("Seleccione una opcion: ");

                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese DNI (8 digitos): ");
                        String dni = scanner.nextLine();
                        System.out.print("Ingrese Nombre Completo: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese Codigo HC: ");
                        String hc = scanner.nextLine();

                        listaPacientes.add(new Paciente(dni, nombre, hc));
                        auditoria.registrarEvento("Nuevo paciente registrado: " + hc);
                        break;

                    case 2:
                        System.out.print("Ingrese ID de Cita (ej. C-04): ");
                        String idC = scanner.nextLine();
                        System.out.print("Ingrese DNI del paciente: ");
                        String dniC = scanner.nextLine();
                        System.out.print("Ingrese Fecha y Hora (AAAA-MM-DD HH:MM): ");
                        String fecha = scanner.nextLine();

                        // Uso explícito del patrón Factory con manejo de excepciones
                        CitaMedica nuevaCita = CitaFactory.crearCita(idC, dniC, fecha, "Programada");
                        listaCitas.add(nuevaCita);
                        auditoria.registrarEvento("Cita creada exitosamente via CitaFactory: " + idC);
                        break;

                    case 3:
                        System.out.println("\n--- LISTA DE PACIENTES REGISTRADOS ---");
                        listaPacientes.forEach(p -> System.out.println(p));
                        auditoria.registrarEvento("Consulta general de lista de pacientes");
                        break;

                    case 4:
                        // FUNCIÓN DE ORDEN SUPERIOR 1: Stream filter por DNI
                        System.out.print("Ingrese DNI a buscar: ");
                        String dniB = scanner.nextLine();

                        List<CitaMedica> citasFiltradas = listaCitas.stream()
                                .filter(c -> c.getDniPaciente().equals(dniB))
                                .collect(Collectors.toList());

                        System.out.println("\n--- CITAS ENCONTRADAS PARA DNI " + dniB + " ---");
                        if (citasFiltradas.isEmpty()) {
                            System.out.println("No existen citas registradas para dicho DNI.");
                        } else {
                            citasFiltradas.forEach(System.out::println);
                        }
                        auditoria.registrarEvento("Busqueda de citas con filtro Stream para DNI: " + dniB);
                        break;

                    case 5:
                        // FUNCIÓN DE ORDEN SUPERIOR 2: Stream filter por estado "Programada"
                        List<CitaMedica> citasProgramadas = listaCitas.stream()
                                .filter(c -> c.getEstado().equalsIgnoreCase("Programada"))
                                .collect(Collectors.toList());

                        System.out.println("\n--- REPORTE DE CITAS CON ESTADO 'PROGRAMADA' ---");
                        citasProgramadas.forEach(System.out::println);
                        auditoria.registrarEvento("Generacion de reporte de citas programadas via Stream");
                        break;

                    case 6:
                        auditoria.mostrarBitacora();
                        break;

                    case 7:
                        salir = true;
                        System.out.println("Saliendo del sistema...");
                        auditoria.registrarEvento("Cierre de sesion del sistema");
                        break;

                    default:
                        System.out.println("Opcion inválida. Ingrese un numero entre 1 y 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[EXCEPCIÓN MANEJADA]: Entrada invalida. Debe digitar un numero entero.");
            } catch (IllegalArgumentException e) {
                System.out.println("\n[EXCEPCIÓN MANEJADA]: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\n[EXCEPCIÓN GENERAL]: Ocurrio un error inesperado: " + e.getMessage());
            }
        }
        scanner.close();
    }
}