# Sistema de Registro de Estudiantes

## Descripción
Este proyecto implementa un sistema CRUD (Crear, Leer, Actualizar, Eliminar) para gestionar registros de estudiantes. Permite almacenar y manipular información como nombre, ID, carrera y promedio académico de los estudiantes.

## Características
- Agregar estudiantes : Registra nuevos estudiantes con validación de datos.
- Listar estudiantes : Muestra todos los estudiantes registrados en el sistema.
- Buscar por ID : Permite encontrar un estudiante específico mediante su identificador único.
- Editar información : Actualiza los datos de estudiantes existentes.
- Eliminar registros : Elimina estudiantes del sistema.
- Persistencia automática en CSV : Guarda los cambios en un archivo CSV después de agregar, editar o eliminar estudiantes.

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
javac Main.java model/Estudiante.java service/EstudianteService.java
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

### Persistencia en CSV
- Los datos se guardan automáticamente en un archivo CSV ubicado en la carpeta "archivos csv"
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
Este proyecto está bajo la Licencia MIT.

Copyright (c) 2026 duckcode-dev

Por la presente se concede permiso, sin cargo, a cualquier persona que obtenga una copia de este software y de los archivos de documentación asociados (el "Software"), para utilizar el Software sin restricción, incluidos, entre otros, los derechos de usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar y/o vender copias del Software, y permitir a las personas a quienes se les proporcione el Software que lo hagan, sujeto a las siguientes condiciones:

El aviso de copyright anterior y este aviso de permiso se incluirán en todas las copias o partes sustanciales del Software.

EL SOFTWARE SE PROPORCIONA "TAL CUAL", SIN GARANTÍA DE NINGÚN TIPO, EXPRESA O IMPLÍCITA, INCLUIDAS, ENTRE OTRAS, LAS GARANTÍAS DE COMERCIABILIDAD, IDONEIDAD PARA UN PROPÓSITO PARTICULAR Y NO INFRACCIÓN. EN NINGÚN CASO LOS AUTORES O TITULARES DEL COPYRIGHT SERÁN RESPONSABLES DE NINGUNA RECLAMACIÓN, DAÑO U OTRA RESPONSABILIDAD, YA SEA EN UNA ACCIÓN CONTRACTUAL, EXTRACONTRACTUAL O DE OTRO TIPO, QUE SURJA DE, O EN RELACIÓN CON, EL SOFTWARE O EL USO U OTRO TIPO DE ACCIONES EN EL SOFTWARE.

