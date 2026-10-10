import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import model.Estudiante;
import service.EstudianteService;

/**
 * Clase principal que contiene el método main para ejecutar el sistema de
 * registro de estudiantes.
 * Proporciona un menú interactivo para agregar, listar, buscar, editar y
 * eliminar estudiantes.
 * 
 * @author Patricio Fernández
 * @author github.com/duckcode-dev
 * 
 */

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        int salir = 0;

        EstudianteService estudianteService = new EstudianteService();

        String id = "";
        String nombre = "";
        String carrera = "";
        double promedio = 0;
        EstudianteService.ResultadoOperacion resultadoOperacion;
        Optional<Estudiante> estudianteEncontrado;

        while (salir != 6) {
            System.out.println("");
            System.out.println("***SISTEMA DE REGISTRO DE ESTUDIANTES***");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Listar estudiante");
            System.out.println("3. Buscar por nombre");
            System.out.println("4. Editar estudiante");
            System.out.println("5. Eliminar estudiante");
            System.out.println("6. Salir");
            try {
                salir = Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un número entre 1 y 6.");
                continue;
            }
            switch (salir) {
                case 1:
                    System.out.println("***AGREGAR ESTUDIANTE****");

                    while (nombre.isEmpty()) {
                        System.out.println("Nombre:");
                        nombre = entrada.nextLine().trim();
                        if (nombre.isEmpty()) {
                            System.out.println("error!, ingrese nombre válido.");
                        }
                    }

                    while (carrera.isEmpty()) {
                        System.out.println("Carrera:");
                        carrera = entrada.nextLine().trim();
                        if (carrera.isEmpty()) {
                            System.out.println("error!, ingrese carrera válido.");
                        }
                    }

                    while (!esPromedioValido(promedio)) {
                        try {
                            System.out.println("Promedio:");
                            promedio = Double.parseDouble(entrada.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("error!, ingrese promedio válido.");
                        }
                        if (!esPromedioValido(promedio)) {
                            System.out.println("error!, ingrese promedio válido entre 1 y 7.");
                        }
                    }

                    if (!estudianteService.existeEstudiante(nombre, carrera, promedio)) {
                        resultadoOperacion = estudianteService.agregarEstudiante(nombre, carrera, promedio);
                        if (resultadoOperacion == EstudianteService.ResultadoOperacion.EXITOSA) {
                            System.out.println("Estudiante agregado con éxito!");
                        } else {
                            System.out.println("Error: no se pudo guardar el estudiante. No se realizaron cambios.");
                        }
                    } else {
                        System.out.println("El estudiante ya existe.");
                    }
                    nombre = "";
                    carrera = "";
                    promedio = 0;
                    break;

                case 2:
                    System.out.println("***LISTAR ESTUDIANTES****");
                    mostrarEstudiantes(estudianteService.listarEstudiantes());
                    break;

                case 3:
                    System.out.println("***BUSCAR ESTUDIANTE POR NOMBRE***");
                    System.out.println("Ingrese el nombre o parte del nombre:");
                    nombre = entrada.nextLine().trim();
                    if (nombre.isEmpty()) {
                        System.out.println("Error: ingrese un nombre para buscar.");
                    } else {
                        List<Estudiante> coincidencias = estudianteService.buscarPorNombre(nombre);
                        if (coincidencias.isEmpty()) {
                            System.out.println("¡No se encontraron estudiantes con ese nombre!");
                        } else {
                            System.out.println("Estudiantes encontrados:");
                            mostrarEstudiantes(coincidencias);
                        }
                    }
                    nombre = "";
                    break;
                case 4:
                    System.out.println("***EDITAR ESTUDIANTE***");
                    System.out.println("Ingrese el ID del estudiante:");
                    id = entrada.nextLine().trim();
                    estudianteEncontrado = estudianteService.buscarPorId(id);
                    if (!estudianteEncontrado.isPresent()) {
                        System.out.println("error!, ID no encontrado.");
                    } else {
                        System.out.println("Estudiante encontrado:");
                        mostrarEstudiante(estudianteEncontrado.get());
                        while (nombre.isEmpty()) {
                            System.out.println("Nombre:");
                            nombre = entrada.nextLine().trim();
                            if (nombre.isEmpty()) {
                                System.out.println("error!, ingrese nombre válido.");
                            }
                        }
                        while (carrera.isEmpty()) {
                            System.out.println("Carrera:");
                            carrera = entrada.nextLine().trim();
                            if (carrera.isEmpty()) {
                                System.out.println("error!, ingrese carrera válido.");
                            }
                        }

                        while (!esPromedioValido(promedio)) {
                            try {
                                System.out.println("Promedio:");
                                promedio = Double.parseDouble(entrada.nextLine());
                                System.out.println();
                            } catch (NumberFormatException e) {
                                System.out.println("error!, ingrese promedio válido.");
                            }
                            if (!esPromedioValido(promedio)) {
                                System.out.println("error!, ingrese promedio válido entre 1 y 7.");
                            }
                        }

                        resultadoOperacion = estudianteService.editarEstudiante(id, nombre, carrera, promedio);

                        if (resultadoOperacion == EstudianteService.ResultadoOperacion.EXITOSA) {
                            System.out.println("¡datos modificados de manera exitosa!");
                        } else {
                            System.out.println("Error: no se pudieron guardar los cambios. Se restauraron los datos anteriores.");
                        }

                        nombre = "";
                        carrera = "";
                        promedio = 0;
                        id = "";
                    }
                    break;
                case 5:
                    System.out.println("***ELIMINAR ESTUDIANTE***");
                    System.out.println("Ingrese el ID del estudiante:");
                    id = entrada.nextLine().trim();
                    resultadoOperacion = estudianteService.eliminarEstudiante(id);
                    if (resultadoOperacion == EstudianteService.ResultadoOperacion.NO_ENCONTRADO) {
                        System.out.println("¡estudiante no encontrado.!");
                    } else if (resultadoOperacion == EstudianteService.ResultadoOperacion.EXITOSA) {
                        System.out.println("Estudiante Eliminado!");
                    } else {
                        System.out.println("Error: no se pudo guardar la eliminación. El estudiante no fue eliminado.");
                    }
                    id = "";
                    break;
                case 6:
                    System.out.println("¡que tenga buen día!");
                    break;
                default:
                    System.out.println("Error: opción no válida. Ingrese un número entre 1 y 6.");
                    break;
            }
        }

        entrada.close();
    }

    private static boolean esPromedioValido(double promedio) {
        return Double.isFinite(promedio) && promedio >= 1.0 && promedio <= 7.0;
    }

    private static void mostrarEstudiantes(List<Estudiante> estudiantes) {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        for (Estudiante estudiante : estudiantes) {
            mostrarEstudiante(estudiante);
        }
    }

    private static void mostrarEstudiante(Estudiante estudiante) {
        System.out.println("ID: " + estudiante.getId() + ", Nombre: " + estudiante.getNombre()
                + ", Carrera: " + estudiante.getCarrera() + ", Promedio: " + estudiante.getPromedio());
    }
}
