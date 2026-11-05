---
permalink: jakyri/idat-evaluacion-parcial-backend/readme
title: README
type: note
---

# 🛠️ API RESTful para Gestión de Solicitudes de Soporte Técnico

Proyecto correspondiente a la **Evaluación Parcial** de la unidad didáctica **Desarrollo de los Componentes del Negocio** en **IDAT**.

---

## 👤 Datos del Estudiante (Desarrollo Individual)
* **Estudiante:** Russ Jack Tapia Apaza
* **Carrera:** Escuela de Tecnología - IDAT
* **Rol en el Proyecto:** Desarrollador Backend Full-Lifecycle
  * **Modelado POO:** Diseño de clases con herencia (`Persona` -> `Cliente`, `Tecnico`), encapsulamiento y polimorfismo.
  * **Persistencia en Memoria:** Simulación de base de datos usando `ConcurrentHashMap`, `ArrayList` y `AtomicLong`.
  * **API RESTful:** Controladores `@RestController`, respuestas `ResponseEntity` y códigos HTTP (`200`, `201`, `204`, `400`, `404`).
  * **Validaciones & Excepciones:** Bean Validation (`@Valid`, `@NotBlank`, `@Size`, `@NotNull`) y `@RestControllerAdvice`.
  * **Documentación & QA:** Integración con Swagger OpenAPI 3 y diseño/ejecución de pruebas funcionales en Postman.

---

## 📌 Contexto Problemático
Una empresa de servicios tecnológicos registraba las solicitudes de soporte técnico en papel y correos electrónicos desordenados, generando extravío de información y demoras críticas en los tiempos de respuesta. 

Esta solución implementa una **API RESTful robusta con Spring Boot y Java**, estructurada en arquitectura limpia de capas, con persistencia simulada en estructuras de datos en memoria (`Map` y `List`), validaciones estrictas y documentación estandarizada sin dependencias de base de datos externa.

---

## 🛠️ Tecnologías y Dependencias
* **Lenguaje:** Java 17+
* **Framework:** Spring Boot 3.2.4
* **Módulos:**
  * `spring-boot-starter-web`: Implementación de endpoints REST y servidor embebido Apache Tomcat.
  * `spring-boot-starter-validation`: Validaciones declarativas (`@Valid`, `@NotNull`, `@NotBlank`, `@Size`).
  * `springdoc-openapi-starter-webmvc-ui`: Documentación interactiva Swagger / OpenAPI 3.
  * `spring-boot-starter-test`: Suite de pruebas unitarias.
* **Persistencia Local Simulada:** `ConcurrentHashMap`, `ArrayList`, `AtomicLong`.
* **Pruebas de Integración:** Postman.

---

## 🏛️ Estructura del Proyecto (Arquitectura en Capas)
```text
src/main/java/pe/edu/idat/soporte/
├── controller/
│   └── SolicitudController.java          # Endpoints CRUD con ResponseEntity
├── dto/
│   ├── ErrorResponseDTO.java             # Formato estándar de error
│   ├── SolicitudRequestDTO.java          # Datos de entrada validados con @Valid
│   └── SolicitudResponseDTO.java         # Datos de respuesta hacia el cliente
├── exception/
│   ├── BadRequestException.java          # Excepción personalizada 400
│   ├── GlobalExceptionHandler.java       # Manejador centralizado (@RestControllerAdvice)
│   └── ResourceNotFoundException.java   # Excepción personalizada 404
├── model/
│   ├── Cliente.java                      # Subclase de Persona
│   ├── EstadoSolicitud.java              # Enum de ciclo de vida
│   ├── Persona.java                      # Superclase abstracta (Herencia y Polimorfismo)
│   ├── PrioridadSolicitud.java           # Enum de prioridades
│   ├── Solicitud.java                    # Entidad principal del negocio
│   └── Tecnico.java                      # Subclase de Persona
├── repository/
│   ├── InMemorySolicitudRepository.java  # Simulación de persistencia con Map y List
│   └── SolicitudRepository.java          # Contrato de persistencia desacoplado
├── service/
│   ├── SolicitudService.java             # Interfaz de lógica de negocio
│   └── SolicitudServiceImpl.java         # Implementación con catálogos en memoria
└── SoporteTecnicoApiApplication.java     # Clase principal de arranque
```

---

## ⚙️ Instrucciones de Instalación y Ejecución

### Prerrequisitos
* Tener instalado el **Java Development Kit (JDK 17 o superior)**.
* Disponer de **Apache Maven** (o usar el wrapper incluido `./mvnw`).

### Pasos para compilar y ejecutar:
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/russ-jack/idat-evaluacion-parcial-backend.git
   cd idat-evaluacion-parcial-backend
   ```
2. Compilar el proyecto con Maven:
   ```bash
   ./mvnw clean install
   ```
3. Iniciar la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```
4. El servidor se iniciará en el puerto `8080`: `http://localhost:8080`

---

## 📖 Documentación Interactiva (Swagger OpenAPI 3)
Una vez iniciada la aplicación, accede desde cualquier navegador a la interfaz visual interactiva de Swagger:
👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

Permite explorar y probar directamente todos los endpoints desde el navegador.

---

## 🧪 Pruebas con Postman

En la carpeta [`postman/`](./postman/) se encuentra la colección lista para importar:
* **Archivo:** `postman/Soporte_Tecnico_IDAT.postman_collection.json`

### Endpoints implementados:
| Método | Endpoint | Descripción | Código HTTP |
|:------:|:---------|:------------|:-----------:|
| `POST` | `/api/v1/solicitudes` | Registrar una nueva solicitud | `201 Created` / `400 Bad Request` |
| `GET` | `/api/v1/solicitudes` | Listar todas las solicitudes | `200 OK` |
| `GET` | `/api/v1/solicitudes?estado=EN_PROCESO` | Filtrar solicitudes por estado | `200 OK` |
| `GET` | `/api/v1/solicitudes/{id}` | Consultar detalle de solicitud por ID | `200 OK` / `404 Not Found` |
| `PUT` | `/api/v1/solicitudes/{id}` | Actualizar datos, estado o técnico | `200 OK` / `404 Not Found` |
| `DELETE` | `/api/v1/solicitudes/{id}` | Eliminar permanentemente la solicitud | `204 No Content` / `404 Not Found` |