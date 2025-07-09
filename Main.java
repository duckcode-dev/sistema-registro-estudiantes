import java.util.Scanner;

import model.Estudiante;
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

        String[] datos = new String[4];

        String id = "";
        String nombre = "";
        String carrera = "";
        String promedio = "";
        double promedioCadena = 0;
        boolean validacionAgregar;
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

                    while (promedioCadena < 1 || promedioCadena > 7) {
                        try {
                            System.out.println("Promedio:");
                            promedio = entrada.nextLine();
                            promedioCadena = Double.parseDouble(promedio);
                        } catch (NumberFormatException e) {
                            System.out.println("error!, ingrese promedio válido.");
                        }
                        if (promedioCadena < 1 || promedioCadena > 7) {
                            System.out.println("error!, ingrese promedio válido entre 1 y 7.");
                        }
                    }

                    datos[0] = nombre;
                    datos[1] = carrera;
                    datos[2] = promedio;

                    validacionAgregar = estudianteService.buscarEstudiante(datos);

                    if (validacionAgregar == false) {
                        Estudiante estudiante = new Estudiante();
                        estudianteService.agregarEstudiante(estudiante, datos);
                    }
                    nombre = "";
                    carrera = "";
                    promedioCadena = 0;
                    break;

                case 2:
                    System.out.println("***LISTAR ESTUDIANTES****");
                    estudianteService.listarEstudiantes();
                    break;

                case 3:
                    System.out.println("***BUSCAR ESTUDIANTE POR ID***");
                    System.out.println("Ingrese el ID del estudiante:");
                    id = entrada.nextLine();
                    validarEstudiante = estudianteService.BuscarEstudiante(id);
                    if (validarEstudiante == false) {
                        System.out.println("¡Estudiante no existe!.");
                    }
                    break;
                case 4:
                    System.out.println("***EDITAR ESTUDIANTE***");
                    System.out.println("Ingrese el ID del estudiante:");
                    id = entrada.nextLine();
                    validarEstudiante = estudianteService.BuscarEstudiante(id);
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

                        while (promedioCadena < 1 || promedioCadena > 7) {
                            try {
                                System.out.println("Promedio:");
                                promedio = entrada.nextLine();
                                promedioCadena = Double.parseDouble(promedio);
                                System.out.println();
                            } catch (NumberFormatException e) {
                                System.out.println("error!, ingrese promedio válido.");
                            }
                            if (promedioCadena < 1 || promedioCadena > 7) {
                                System.out.println("error!, ingrese promedio válido entre 1 y 7.");
                            }
                        }

                        datos[0] = nombre;
                        datos[1] = carrera;
                        datos[2] = promedio;
                        datos[3] = id;

                        Estudiante estudianteModificar = new Estudiante();
                        validarEstudiante = estudianteService.editarEstudiante(estudianteModificar, datos);

                        if (validarEstudiante) {
                            System.out.println("¡datos modificados de manera exitosa!");
                        }

                        nombre = "";
                        carrera = "";
                        promedioCadena = 0;
                        id = "";
                    }
                    break;
                case 5:
                    System.out.println("***ELIMINAR ESTUDIANTE***");
                    System.out.println("Ingrese el ID del estudiante:");
                    id = entrada.nextLine();
                    validarEstudiante = estudianteService.BuscarEstudiante(id);
                    if (validarEstudiante == false) {
                        System.out.println("¡estudiante no encontrado.!");
                    } else {
                        estudianteService.eliminarEstudiante(id);
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
}