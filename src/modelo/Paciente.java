package modelo;

public class Paciente {
    private String dni;
    private String nombreCompleto;
    private String codigoHc;

    public Paciente(String dni, String nombreCompleto, String codigoHc) {
        setDni(dni);
        this.nombreCompleto = nombreCompleto;
        this.codigoHc = codigoHc;
    }

    // Getters y Setters
    public String getDni() { return dni; }
    public void setDni(String dni) {
        if (dni == null || dni.length() != 8) {
            throw new IllegalArgumentException("El DNI debe contener exactamente 8 dígitos.");
        }
        this.dni = dni;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCodigoHc() { return codigoHc; }
    public void setCodigoHc(String codigoHc) { this.codigoHc = codigoHc; }

    // Enmascaramiento conforme a la Ley N° 29733 (Protección de Datos Personales)
    public String getDniProtegido() {
        if (dni != null && dni.length() == 8) {
            return dni.substring(0, 2) + "****" + dni.substring(6);
        }
        return "********";
    }

    @Override
    public String toString() {
        return "Paciente [DNI Protegido: " + getDniProtegido() + " | Nombre: " + nombreCompleto + " | HC: " + codigoHc + "]";
    }
}
