// Clase CitaFactory - Implementacion del Patron de Diseno Factory
package modelo;

public class CitaFactory {
    public static CitaMedica crearCita(String idCita, String dniPaciente, String fechaHora, String estado) {
        // Validaciones centralizadas en la fábrica
        if (dniPaciente == null || dniPaciente.length() != 8) {
            throw new IllegalArgumentException("No se puede crear la cita: El DNI del paciente debe tener 8 dígitos.");
        }
        if (idCita == null || idCita.isEmpty()) {
            throw new IllegalArgumentException("No se puede crear la cita: El ID de cita es obligatorio.");
        }
        return new CitaMedica(idCita, dniPaciente, fechaHora, estado);
    }
}
