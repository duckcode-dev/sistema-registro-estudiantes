package service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import model.Estudiante;

public class EstudianteService {

    private final List<Estudiante> estudiantes = new ArrayList<>();
    private int contador = 1;

    // funcion para agregar estudiante
    public boolean agregarEstudiante(String nombre, String carrera, double promedio) {
        Estudiante estudiante = new Estudiante(generarId(), nombre, carrera, promedio);
        estudiantes.add(estudiante);
        System.out.println("Estudiante agregado con éxito! ID : " + estudiante.getId());
        return true;
    }

    // funcion para buscar estudiante por nombre, carrera, promedio
    public boolean buscarEstudiante(String nombre, String carrera, double promedio) {

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNombre().equalsIgnoreCase(nombre) && estudiante.getCarrerra().equalsIgnoreCase(carrera)
                    && Double.compare(estudiante.getPromedio(), promedio) == 0) {
                System.out.println("Estudiante ya existe ID: " + estudiante.getId());
                return true;
            }
        }
        return false;
    }

    // generador de id
    public String generarId() {
        return String.format("EST%03d", contador++);
    }

    public void listarEstudiantes() {
        for (Estudiante estudiantecuatro : estudiantes) {
            System.out.println("ID: " + estudiantecuatro.getId() + ", Nombre: " +
                    estudiantecuatro.getNombre() + ", Carrera: "
                    + estudiantecuatro.getCarrerra() + ", Promedio: " + estudiantecuatro.getPromedio());
        }
    }

    // bsucar estudiante por id
    public boolean BuscarEstudiante(String id) {

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId().equalsIgnoreCase(id)) {
                System.out.println("Estudiante Encontrado");
                System.out.println("información del estudiante: ");
                System.out.println("ID: " + estudiante.getId() + ", Nombre: " +
                        estudiante.getNombre() + ", Carrera: "
                        + estudiante.getCarrerra() + ", Promedio: " + estudiante.getPromedio());
                return true;
            }
        }
        return false;
    }

    // modificar estudiante
    public boolean editarEstudiante(String id, String nombre, String carrera, double promedio) {

        for (Estudiante estudianteDos : estudiantes) {
            if (estudianteDos.getId().equalsIgnoreCase(id)) {
                estudianteDos.setNombre(nombre);
                estudianteDos.setCarrerra(carrera);
                estudianteDos.setPromedio(promedio);
                return true;
            }
        }
        return false;
    }

    // Eliminar Estudiante
    public void eliminarEstudiante(String id) {

        for (Estudiante estudianteTres : estudiantes) {
            if (estudianteTres.getId().equalsIgnoreCase(id)) {
                // eliminar estudiante
                estudiantes.remove(estudianteTres);
                break;
            }
        }
    }

    // método para exportar la lista a archivo .csv
    public boolean exportarCSV() {
        Path ruta = Paths.get("archivos csv", "estudiantes.csv");
        try {
            Files.createDirectories(ruta.getParent());
        } catch (IOException e) {
            System.out.println("No se pudo crear el directorio de exportación: " + e.getMessage());
            return false;
        }

        try (java.io.BufferedWriter writer = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
            // Escribir encabezados
            writer.write("ID,Nombre,Carrera,Promedio\n");

            // Escribir datos de cada estudiante
            for (Estudiante estudiante : estudiantes) {
                writer.write(String.format("%s,%s,%s,%s\n",
                        escaparCampoCsv(estudiante.getId()),
                        escaparCampoCsv(estudiante.getNombre()),
                        escaparCampoCsv(estudiante.getCarrerra()),
                        escaparCampoCsv(String.valueOf(estudiante.getPromedio()))));
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo CSV en " + ruta.toAbsolutePath() + ": " + e.getMessage());
            return false;
        }
    }

    private String escaparCampoCsv(String valor) {
        String valorEscapado = valor.replace("\"", "\"\"");
        return "\"" + valorEscapado + "\"";
    }
}
