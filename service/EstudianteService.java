package service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Estudiante;

public class EstudianteService {

    private static final List<Estudiante> estudiantes = new ArrayList<>();
    private static int contador = 1;
    private static boolean validarEstudiante;

    // funcion para agregar estudiante
    public boolean agregarEstudiante(Estudiante estudiante, String[] datos) {
        estudiante.setId(generarId());
        estudiante.setNombre(datos[0]);
        estudiante.setCarrerra(datos[1]);
        estudiante.setPromedio(datos[2]);
        estudiantes.add(estudiante);
        System.out.println("Estudiante agregado con éxito! ID : " + estudiante.getId());
        return true;
    }

    // funcion para buscar estudiante por nombre, carrera, promedio
    public boolean buscarEstudiante(String[] datos) {

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNombre().equalsIgnoreCase(datos[0]) && estudiante.getCarrerra().equalsIgnoreCase(datos[1])
                    && estudiante.getPromedio().equalsIgnoreCase(datos[2])) {
                validarEstudiante = true;
                System.out.println("Estudiante ya existe ID: " + estudiante.getId());
                break;
            } else {
                validarEstudiante = false;
            }
        }
        return validarEstudiante;
    }

    // generador de id
    public static String generarId() {
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
                validarEstudiante = true;
                System.out.println("Estudiante Encontrado");
                System.out.println("información del estudiante: ");
                System.out.println("ID: " + estudiante.getId() + ", Nombre: " +
                        estudiante.getNombre() + ", Carrera: "
                        + estudiante.getCarrerra() + ", Promedio: " + estudiante.getPromedio());
                break;
            } else {
                validarEstudiante = false;
            }
        }
        return validarEstudiante;
    }

    // modificar estudiante
    public boolean editarEstudiante(Estudiante estudiante, String[] datos) {

        for (Estudiante estudianteDos : estudiantes) {
            if (estudianteDos.getId().equalsIgnoreCase(datos[3])) {
                validarEstudiante = true;
                estudianteDos.setNombre(datos[0]);
                estudianteDos.setCarrerra(datos[1]);
                estudianteDos.setPromedio(datos[2]);
                break;
            } else {
                validarEstudiante = false;
            }
        }
        return validarEstudiante;
    }

    // Eliminar Estudiante
    public void eliminarEstudiante(String id) {

        for (Estudiante estudianteTres : estudiantes) {
            if (estudianteTres.getId().equalsIgnoreCase(id)) {
                validarEstudiante = true;
                // eliminar estudiante
                estudiantes.remove(estudianteTres);
                break;
            }
        }
    }

    // método para exportar la lista a archivo .csv
    public boolean exportarCSV() {
        String ruta = "C:\\Users\\patri\\OneDrive\\Escritorio\\proyectos\\sistema-registro-estudiantes\\archivos csv\\estudiantes.csv";
        try (FileWriter writer = new FileWriter(ruta)) {
            // Escribir encabezados
            writer.write("ID,Nombre,Carrera,Promedio\n");

            // Escribir datos de cada estudiante
            for (Estudiante estudiante : estudiantes) {
                writer.write(String.format("%s,%s,%s,%s\n",
                        estudiante.getId(),
                        estudiante.getNombre(),
                        estudiante.getCarrerra(),
                        estudiante.getPromedio()));
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo CSV: " + e.getMessage());
            return false;
        }
    }
}
