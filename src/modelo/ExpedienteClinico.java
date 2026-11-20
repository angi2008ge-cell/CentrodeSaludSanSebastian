package modelo;
public class ExpedienteClinico {
    private String idExpediente;
    private String dniPaciente;
    private String diagnosticoProtegido;

    public ExpedienteClinico(String idExpediente, String dniPaciente, String diagnosticoProtegido) {
        this.idExpediente = idExpediente;
        this.dniPaciente = dniPaciente;
        this.diagnosticoProtegido = diagnosticoProtegido;
    }

    public String getIdExpediente() { return idExpediente; }
    public String getDniPaciente() { return dniPaciente; }
    public String getDiagnosticoProtegido() { return diagnosticoProtegido; }

    @Override
    public String toString() {
        return "Expediente [ID: " + idExpediente + " | DNI Paciente: " + dniPaciente + " | Diagnóstico Protegido: " + diagnosticoProtegido + "]";
    }
}
