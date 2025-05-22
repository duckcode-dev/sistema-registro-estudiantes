# Sistema de Registro de Estudiantes

## Descripción
Este proyecto implementa un sistema CRUD (Crear, Leer, Actualizar, Eliminar) para gestionar registros de estudiantes. Permite almacenar y manipular información como nombre, ID, carrera y promedio académico de los estudiantes.

## Características
- Agregar estudiantes : Registra nuevos estudiantes con validación de datos.
- Listar estudiantes : Muestra todos los estudiantes registrados en el sistema.
- Buscar por ID : Permite encontrar un estudiante específico mediante su identificador único.
- Editar información : Actualiza los datos de estudiantes existentes.
- Eliminar registros : Elimina estudiantes del sistema.
- Exportar a CSV : Guarda la información de los estudiantes en un archivo CSV para su uso externo.

Estructura del Proyecto
sistema-registro-estudiantes/
├── Main.java                  # Punto de entrada de la aplicación
├── model/
│   └── Estudiante.java        # Clase modelo para los estudiantes
├── service/
│   └── EstudianteService.java # Servicios para gestionar estudiantes
└── archivos csv/
    └── estudiantes.csv        # Archivo de exportación de datos

## Tecnologías Utilizadas
- Java
- Programación Orientada a Objetos (POO)
- Colecciones (ArrayList)
- Arquitectura MVC (Modelo-Vista-Controlador)

## Requisitos
- JDK 8 o superior
- Entorno de desarrollo Java (Eclipse, IntelliJ, etc.)

## Cómo Ejecutar
1. Compile los archivos Java:

```bash
javac Main.java model/Estudiante.java 
```

2. Ejecute la aplicación:

```bash
java Main
```

## Funcionalidades Detalladas
### Gestión de Estudiantes
- Los estudiantes se identifican con un ID único generado automáticamente (formato: EST001, EST002, etc.)
- Se valida que los campos no estén vacíos
- El promedio debe estar en el rango de 1.0 a 7.0

### Exportación a CSV
- Los datos se exportan a un archivo CSV ubicado en la carpeta "archivos csv"
- El formato incluye: ID, Nombre, Carrera, Promedio

## Demostración de Conceptos
Este proyecto demuestra:

- Comprensión de Programación Orientada a Objetos
- Uso de colecciones en Java
- Separación de responsabilidades (patrón MVC)
- Validación de datos de entrada
- Manejo de archivos

## Autor
Patricio Fernández

## Licencia
Licencia MIT

