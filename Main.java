import java.util.Scanner;

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

        while (salir != 6) {
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Listar estudiante");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Editar estudiante");
            System.out.println("5. Eliminar estudiante");
            System.out.println("6. Salir");
            salir = entrada.nextInt();
            if (salir == 6) {
                System.out.println("hasta pronto");
            } else if (salir != 1 || salir != 2 || salir != 3 || salir != 4 || salir != 5) {
                System.out.println("Opción no válida. Intente de nuevo!");
            }
        }

    }
}