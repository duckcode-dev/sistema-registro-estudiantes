import java.util.Scanner;

import service.EstudianteService;

public class Main {

    public static void main(String[] args) {

        /*
         * 1. Sistema de Registro de Estudiantes
         * Descripción: CRUD para registrar estudiantes con campos como nombre, ID,
         * carrera, promedio, etc.
         * 
         * Extras: Puedes agregar validaciones y exportar a CSV.
         * 
         * Qué demuestra: Comprensión de POO, colecciones (ArrayList, HashMap) y
         * separación de responsabilidades (MVC).
         */

        Scanner entrada = new Scanner(System.in);

        int salir = 0;

        EstudianteService estudianteService = new EstudianteService();

        String id = "";
        String nombre = "";
        String carrera = "";
        double promedio = 0;
        boolean validarEstudiante;

        while (salir != 7) {
            System.out.println("");
            System.out.println("***SISTEMA DE REGISTRO DE ESTUDIANTES***");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Listar estudiante");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Editar estudiante");
            System.out.println("5. Eliminar estudiante");
            System.out.println("6. Exportar a CSV");
            System.out.println("7. Salir");
            salir = entrada.nextInt();
            entrada.nextLine();// Esta línea consume el salto de línea pendiente
            switch (salir) {
                case 1:
                    System.out.println("***AGREGAR ESTUDIANTE****");

                    while (nombre.isEmpty()) {
                        System.out.println("Nombre:");
                        nombre = entrada.nextLine();
                        if (nombre.isEmpty()) {
                            System.out.println("error!, ingrese nombre válido.");
                        }
                    }

                    while (carrera.isEmpty()) {
                        System.out.println("Carrera:");
                        carrera = entrada.nextLine();
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
                        estudianteService.agregarEstudiante(nombre, carrera, promedio);
                    }
                    nombre = "";
                    carrera = "";
                    promedio = 0;
                    break;

                case 2:
                    System.out.println("***LISTAR ESTUDIANTES****");
                    estudianteService.listarEstudiantes();
                    break;

                case 3:
                    System.out.println("***BUSCAR ESTUDIANTE POR ID***");
                    System.out.println("Ingrese el ID del estudiante:");
                    id = entrada.nextLine();
                    validarEstudiante = estudianteService.buscarPorId(id);
                    if (validarEstudiante == false) {
                        System.out.println("¡Estudiante no existe!.");
                    }
                    break;
                case 4:
                    System.out.println("***EDITAR ESTUDIANTE***");
                    System.out.println("Ingrese el ID del estudiante:");
                    id = entrada.nextLine();
                    validarEstudiante = estudianteService.buscarPorId(id);
                    if (validarEstudiante == false) {
                        System.out.println("error!, ID no encontrado.");
                    } else {
                        while (nombre.isEmpty()) {
                            System.out.println("Nombre:");
                            nombre = entrada.nextLine();
                            if (nombre.isEmpty()) {
                                System.out.println("error!, ingrese nombre válido.");
                            }
                        }
                        while (carrera.isEmpty()) {
                            System.out.println("Carrera:");
                            carrera = entrada.nextLine();
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

                        validarEstudiante = estudianteService.editarEstudiante(id, nombre, carrera, promedio);

                        if (validarEstudiante) {
                            System.out.println("¡datos modificados de manera exitosa!");
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
                    id = entrada.nextLine();
                    if (!estudianteService.eliminarEstudiante(id)) {
                        System.out.println("¡estudiante no encontrado.!");
                    } else {
                        System.out.println("Estudiante Eliminado!");
                    }
                    id = "";
                    break;
                case 6:
                    System.out.println("***EXPORTAR A CSV***");
                    if (estudianteService.exportarCSV()) {
                        System.out.println("Archivo CSV creado exitosamente!");
                    } else {
                        System.out.println("Error al crear el archivo CSV");
                    }
                    break;

                default:
                    System.out.println("¡que tenga buen día!");
                    break;
            }
        }

        entrada.close();
    }

    private static boolean esPromedioValido(double promedio) {
        return Double.isFinite(promedio) && promedio >= 1.0 && promedio <= 7.0;
    }
}
