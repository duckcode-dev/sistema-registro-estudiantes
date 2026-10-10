package service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import model.Estudiante;

/**
 * Clase que representa el servicio para gestionar estudiantes.
 * Proporciona métodos para agregar, buscar, editar y eliminar estudiantes,
 * así como para guardar y cargar datos desde un archivo CSV.
 * 
 * @author Patricio Fernández
 * @author github.com/duckcode-dev
 * 
 */

public class EstudianteService {

    public enum ResultadoOperacion {
        EXITOSA,
        NO_ENCONTRADO,
        ERROR_PERSISTENCIA
    }

    private static final Path RUTA_CSV = Paths.get("archivos csv", "estudiantes.csv").toAbsolutePath();
    private final List<Estudiante> estudiantes = new ArrayList<>();
    private int contador = 1;

    public EstudianteService() {
        cargarDesdeCsv();
    }

    // funcion para agregar estudiante
    public ResultadoOperacion agregarEstudiante(String nombre, String carrera, double promedio) {
        int contadorAnterior = contador;
        Estudiante estudiante = new Estudiante(generarId(), nombre, carrera, promedio);
        estudiantes.add(estudiante);
        if (guardarEnCsv()) {
            return ResultadoOperacion.EXITOSA;
        }

        estudiantes.remove(estudiante);
        contador = contadorAnterior;
        return ResultadoOperacion.ERROR_PERSISTENCIA;
    }

    // funcion para buscar estudiante por nombre, carrera, promedio
    public boolean existeEstudiante(String nombre, String carrera, double promedio) {

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNombre().equalsIgnoreCase(nombre) && estudiante.getCarrera().equalsIgnoreCase(carrera)
                    && Double.compare(estudiante.getPromedio(), promedio) == 0) {
                return true;
            }
        }
        return false;
    }

    // generador de id
    public String generarId() {
        return String.format("EST%03d", contador++);
    }

    public List<Estudiante> listarEstudiantes() {
        return Collections.unmodifiableList(new ArrayList<>(estudiantes));
    }

    public Optional<Estudiante> buscarPorId(String id) {

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId().equalsIgnoreCase(id)) {
                return Optional.of(estudiante);
            }
        }
        return Optional.empty();
    }

    public List<Estudiante> buscarPorNombre(String textoBuscado) {
        String busquedaNormalizada = textoBuscado.trim().toLowerCase(Locale.ROOT);
        List<Estudiante> coincidencias = new ArrayList<>();

        for (Estudiante estudiante : estudiantes) {
            String nombreNormalizado = estudiante.getNombre().toLowerCase(Locale.ROOT);
            if (nombreNormalizado.contains(busquedaNormalizada)) {
                coincidencias.add(estudiante);
            }
        }
        return coincidencias;
    }

    // modificar estudiante
    public ResultadoOperacion editarEstudiante(String id, String nombre, String carrera, double promedio) {

        for (Estudiante estudianteDos : estudiantes) {
            if (estudianteDos.getId().equalsIgnoreCase(id)) {
                String nombreAnterior = estudianteDos.getNombre();
                String carreraAnterior = estudianteDos.getCarrera();
                double promedioAnterior = estudianteDos.getPromedio();
                estudianteDos.setNombre(nombre);
                estudianteDos.setCarrera(carrera);
                estudianteDos.setPromedio(promedio);
                if (guardarEnCsv()) {
                    return ResultadoOperacion.EXITOSA;
                }

                estudianteDos.setNombre(nombreAnterior);
                estudianteDos.setCarrera(carreraAnterior);
                estudianteDos.setPromedio(promedioAnterior);
                return ResultadoOperacion.ERROR_PERSISTENCIA;
            }
        }
        return ResultadoOperacion.NO_ENCONTRADO;
    }

    // Eliminar Estudiante
    public ResultadoOperacion eliminarEstudiante(String id) {

        for (int indice = 0; indice < estudiantes.size(); indice++) {
            Estudiante estudianteTres = estudiantes.get(indice);
            if (estudianteTres.getId().equalsIgnoreCase(id)) {
                estudiantes.remove(indice);
                if (guardarEnCsv()) {
                    return ResultadoOperacion.EXITOSA;
                }

                estudiantes.add(indice, estudianteTres);
                return ResultadoOperacion.ERROR_PERSISTENCIA;
            }
        }
        return ResultadoOperacion.NO_ENCONTRADO;
    }

    private boolean guardarEnCsv() {
        Path archivoTemporal = null;
        try {
            Files.createDirectories(RUTA_CSV.getParent());
            archivoTemporal = Files.createTempFile(RUTA_CSV.getParent(), "estudiantes-", ".tmp");

            try (java.io.BufferedWriter writer = Files.newBufferedWriter(archivoTemporal, StandardCharsets.UTF_8)) {
                writer.write("ID,Nombre,Carrera,Promedio\n");

                for (Estudiante estudiante : estudiantes) {
                    writer.write(String.format("%s,%s,%s,%s\n",
                            escaparCampoCsv(estudiante.getId()),
                            escaparCampoCsv(estudiante.getNombre()),
                            escaparCampoCsv(estudiante.getCarrera()),
                            escaparCampoCsv(String.valueOf(estudiante.getPromedio()))));
                }
            }

            reemplazarArchivo(archivoTemporal);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            if (archivoTemporal != null) {
                try {
                    Files.deleteIfExists(archivoTemporal);
                } catch (IOException e) {
                    // El archivo temporal se eliminará en una futura ejecución si aún existe.
                }
            }
        }
    }

    private void reemplazarArchivo(Path archivoTemporal) throws IOException {
        try {
            Files.move(archivoTemporal, RUTA_CSV,
                    StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            if (!Files.exists(archivoTemporal)) {
                throw e;
            }
            Files.move(archivoTemporal, RUTA_CSV, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private void cargarDesdeCsv() {
        if (!Files.exists(RUTA_CSV)) {
            return;
        }

        try {
            List<String> lineas = Files.readAllLines(RUTA_CSV, StandardCharsets.UTF_8);
            for (int indice = 1; indice < lineas.size(); indice++) {
                try {
                    List<String> campos = separarCamposCsv(lineas.get(indice));
                    if (campos.size() != 4) {
                        throw new IllegalArgumentException("Cantidad de columnas inválida");
                    }
                    int numeroId = obtenerNumeroId(campos.get(0));
                    Estudiante estudiante = new Estudiante(
                            campos.get(0), campos.get(1), campos.get(2), Double.parseDouble(campos.get(3)));
                    estudiantes.add(estudiante);
                    actualizarContador(numeroId);
                } catch (IllegalArgumentException e) {
                    // La fila inválida se omite sin interrumpir la carga de las siguientes.
                }
            }
        } catch (IOException e) {
            // Si el archivo no puede leerse, el servicio se inicia sin registros cargados.
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

        if (entreComillas) {
            throw new IllegalArgumentException("Campo CSV con comillas sin cerrar");
        }

        campos.add(campo.toString());
        return campos;
    }

    private int obtenerNumeroId(String id) {
        if (!id.matches("EST\\d+")) {
            throw new IllegalArgumentException("Formato de ID inválido");
        }

        try {
            int numeroId = Integer.parseInt(id.substring(3));
            if (numeroId < 1 || numeroId == Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Número de ID fuera de rango");
            }
            return numeroId;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Número de ID fuera de rango", e);
        }
    }

    private void actualizarContador(int numeroId) {
        contador = Math.max(contador, numeroId + 1);
    }

    private String escaparCampoCsv(String valor) {
        String valorEscapado = valor.replace("\"", "\"\"");
        return "\"" + valorEscapado + "\"";
    }
}
