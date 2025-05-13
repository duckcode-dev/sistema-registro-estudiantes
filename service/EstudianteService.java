package service;

import java.util.ArrayList;
import java.util.List;

import model.Estudiante;

public class EstudianteService {

    private static final List<Estudiante> estudiantes = new ArrayList<>();
    private static int contador = 1;

    public boolean agregarEstudiante(Estudiante estudiante, String[] datos) {
        estudiante.setId(generarId());
        estudiante.setNombre(datos[0]);
        estudiante.setCarrerra(datos[1]);
        estudiante.setPromedio(datos[2]);
        estudiantes.add(estudiante);
        System.out.println("Estudiante agregado con éxito! ID : " + estudiante.getId());
        return true;
    }

    public boolean BuscarEstudiante(String[] datos) {
        boolean validarEstudiante = false;
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

    public static String generarId() {
        return String.format("EST%03d", contador++);
    }

}
