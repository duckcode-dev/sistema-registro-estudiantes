package model;

public class Estudiante {
    private String id;
    private String nombre;
    private String carrerra;
    private String promedio;

    // constructor vacío
    public Estudiante() {
    }

    // constructor parametrizado
    public Estudiante(String id, String nombre, String carrerra, String promedio) {
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
        if (promedio == null || promedio.trim().isEmpty()) {
            throw new IllegalArgumentException("El promedio del estudiante no puede ser null o vacía");
        }

        this.id = id;
        this.nombre = nombre;
        this.carrerra = carrerra;
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

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        if (promedio == null || promedio.trim().isEmpty()) {
            throw new IllegalArgumentException("El promedio no puede ser null o vacía");
        }
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "Estudiante [id=" + id + ", nombre=" + nombre + ", carrerra=" + carrerra + ", promedio=" + promedio
                + "]";
    }

}
