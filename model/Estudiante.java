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
    private String carrerra;
    private double promedio;

    // constructor vacío
    public Estudiante() {
    }

    // constructor parametrizado
    public Estudiante(String id, String nombre, String carrerra, double promedio) {
        // Validación para evitar valores null en campos críticos
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del estudiante no puede ser null o vacío");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante no puede ser null o vacío");
        }
        if (carrerra == null || carrerra.trim().isEmpty()) {
            throw new IllegalArgumentException("La carrera del estudiante no puede ser null o vacía");
        }
        this.id = id;
        this.nombre = nombre;
        this.carrerra = carrerra;
        setPromedio(promedio);
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
        this.nombre = nombre;
    }

    public String getCarrerra() {
        return carrerra;
    }

    public void setCarrerra(String carrerra) {
        if (carrerra == null || carrerra.trim().isEmpty()) {
            throw new IllegalArgumentException("La carrera no puede ser null o vacía");
        }
        this.carrerra = carrerra;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        if (!Double.isFinite(promedio) || promedio < 1.0 || promedio > 7.0) {
            throw new IllegalArgumentException("El promedio debe estar entre 1.0 y 7.0");
        }
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "Estudiante [id=" + id + ", nombre=" + nombre + ", carrerra=" + carrerra + ", promedio=" + promedio
                + "]";
    }

}
