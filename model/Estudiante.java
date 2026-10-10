package model;

/**
 * Clase que representa un estudiante.
 * Contiene información sobre el ID, nombre, carrera y promedio del estudiante.
 * 
 * @author Patricio Fernández
 * @author github.com/duckcode-dev
 * 
 */

public class Estudiante {
    private String id;
    private String nombre;
    private String carrera;
    private double promedio;

    // constructor vacío
    public Estudiante() {
    }

    // constructor parametrizado
    public Estudiante(String id, String nombre, String carrera, double promedio) {
        // Validación para evitar valores null en campos críticos
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del estudiante no puede ser null o vacío");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante no puede ser null o vacío");
        }
        if (carrera == null || carrera.trim().isEmpty()) {
            throw new IllegalArgumentException("La carrera del estudiante no puede ser null o vacía");
        }
        this.id = id;
        this.nombre = nombre.trim();
        this.carrera = carrera.trim();
        validarPromedio(promedio);
        this.promedio = promedio;
    }

    // Métodos getter y setter con validaciones
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede ser null o vacío");
        }
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser null o vacío");
        }
        this.nombre = nombre.trim();
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        if (carrera == null || carrera.trim().isEmpty()) {
            throw new IllegalArgumentException("La carrera no puede ser null o vacía");
        }
        this.carrera = carrera.trim();
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        validarPromedio(promedio);
        this.promedio = promedio;
    }

    private static void validarPromedio(double promedio) {
        if (!Double.isFinite(promedio) || promedio < 1.0 || promedio > 7.0) {
            throw new IllegalArgumentException("El promedio debe estar entre 1.0 y 7.0");
        }
    }

    @Override
    public String toString() {
        return "Estudiante [id=" + id + ", nombre=" + nombre + ", carrera=" + carrera + ", promedio=" + promedio
                + "]";
    }

}
