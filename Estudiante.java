public class Estudiante {
    private String id;
    private String nombre;
    private String carrerra;
    private double promedio;

    public Estudiante(String id, String nombre, String carrerra, double promedio) {
        this.id = id;
        this.nombre = nombre;
        this.carrerra = carrerra;
        this.promedio = promedio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrerra() {
        return carrerra;
    }

    public void setCarrerra(String carrerra) {
        this.carrerra = carrerra;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "Estudiante [id=" + id + ", nombre=" + nombre + ", carrerra=" + carrerra + ", promedio=" + promedio
                + "]";
    }

}
