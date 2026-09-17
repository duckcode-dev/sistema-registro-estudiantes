package service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Estudiante;

public class EstudianteService {

    private static final Path RUTA_CSV = Paths.get("archivos csv", "estudiantes.csv");
    private final List<Estudiante> estudiantes = new ArrayList<>();
    private int contador = 1;

    public EstudianteService() {
        cargarDesdeCsv();
    }

    // funcion para agregar estudiante
    public void agregarEstudiante(String nombre, String carrera, double promedio) {
        Estudiante estudiante = new Estudiante(generarId(), nombre, carrera, promedio);
        estudiantes.add(estudiante);
        guardarEnCsv();
        System.out.println("Estudiante agregado con éxito! ID : " + estudiante.getId());
    }

    // funcion para buscar estudiante por nombre, carrera, promedio
    public boolean existeEstudiante(String nombre, String carrera, double promedio) {

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
    public boolean buscarPorId(String id) {

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
                guardarEnCsv();
                return true;
            }
        }
        return false;
    }

    // Eliminar Estudiante
    public boolean eliminarEstudiante(String id) {

        for (Iterator<Estudiante> iterator = estudiantes.iterator(); iterator.hasNext();) {
            Estudiante estudianteTres = iterator.next();
            if (estudianteTres.getId().equalsIgnoreCase(id)) {
                iterator.remove();
                guardarEnCsv();
                return true;
            }
        }
        return false;
    }

    // método para exportar la lista a archivo .csv
    public boolean exportarCSV() {
        return guardarEnCsv();
    }

    private boolean guardarEnCsv() {
        try {
            Files.createDirectories(RUTA_CSV.getParent());
        } catch (IOException e) {
            System.out.println("No se pudo crear el directorio de exportación: " + e.getMessage());
            return false;
        }

        try (java.io.BufferedWriter writer = Files.newBufferedWriter(RUTA_CSV, StandardCharsets.UTF_8)) {
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
            System.out.println("Error al escribir el archivo CSV en " + RUTA_CSV.toAbsolutePath() + ": " + e.getMessage());
            return false;
        }
    }

    private void cargarDesdeCsv() {
        if (!Files.exists(RUTA_CSV)) {
            return;
        }

        try {
            List<String> lineas = Files.readAllLines(RUTA_CSV, StandardCharsets.UTF_8);
            for (int indice = 1; indice < lineas.size(); indice++) {
                List<String> campos = separarCamposCsv(lineas.get(indice));
                if (campos.size() != 4) {
                    System.out.println("Se omitió una fila CSV inválida: " + (indice + 1));
                    continue;
                }

                try {
                    Estudiante estudiante = new Estudiante(
                            campos.get(0), campos.get(1), campos.get(2), Double.parseDouble(campos.get(3)));
                    estudiantes.add(estudiante);
                    actualizarContador(campos.get(0));
                } catch (IllegalArgumentException e) {
                    System.out.println("Se omitió una fila CSV inválida: " + (indice + 1));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo CSV: " + e.getMessage());
        }
    }

    private List<String> separarCamposCsv(String linea) {
        List<String> campos = new ArrayList<>();
        StringBuilder campo = new StringBuilder();
        boolean entreComillas = false;

        for (int indice = 0; indice < linea.length(); indice++) {
            char caracter = linea.charAt(indice);
            if (caracter == '"') {
                if (entreComillas && indice + 1 < linea.length() && linea.charAt(indice + 1) == '"') {
                    campo.append('"');
                    indice++;
                } else {
                    entreComillas = !entreComillas;
                }
            } else if (caracter == ',' && !entreComillas) {
                campos.add(campo.toString());
                campo.setLength(0);
            } else {
                campo.append(caracter);
            }
        }

        campos.add(campo.toString());
        return campos;
    }

    private void actualizarContador(String id) {
        if (id.matches("EST\\d+")) {
            int numeroId = Integer.parseInt(id.substring(3));
            contador = Math.max(contador, numeroId + 1);
        }
    }

    private String escaparCampoCsv(String valor) {
        String valorEscapado = valor.replace("\"", "\"\"");
        return "\"" + valorEscapado + "\"";
    }
}
