package clase10.transacciones.model;

public class Odontologo {
    private int id;
    private String nombre;
    private String matricula;

    public Odontologo(int id, String nombre, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public Odontologo(String nombre, String matricula) {
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Odontologo" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", matricula='" + matricula + '\'';
    }
}
