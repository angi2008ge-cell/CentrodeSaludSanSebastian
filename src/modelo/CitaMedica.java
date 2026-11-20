package modelo;

public class CitaMedica {
    private String idCita;
    private String dniPaciente;
    private String fechaHora;
    private String estado; // "Programada", "Atendida", "Cancelada"

    public CitaMedica(String idCita, String dniPaciente, String fechaHora, String estado) {
        this.idCita = idCita;
        this.dniPaciente = dniPaciente;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    // Getters y Setters
    public String getIdCita() { return idCita; }
    public void setIdCita(String idCita) { this.idCita = idCita; }

    public String getDniPaciente() { return dniPaciente; }
    public void setDniPaciente(String dniPaciente) { this.dniPaciente = dniPaciente; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Cita [ID: " + idCita + " | DNI Paciente: " + dniPaciente + " | Fecha/Hora: " + fechaHora + " | Estado: " + estado + "]";
    } 
}
