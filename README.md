# Biblioteca Gamer API

Este proyecto es una API REST desarrollada con **Spring Boot** para la gestión de una biblioteca de videojuegos. Permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre una colección de juegos.

## Descripción

La aplicación proporciona un backend para registrar juegos, detallando su información y permitiendo la persistencia de datos en una base de datos PostgreSQL. Utiliza Spring Data JPA para el acceso a datos y validaciones de Jakarta para asegurar la integridad de la información.

### Funcionalidades principales:
- Listar todos los juegos registrados.
- Obtener detalles de un juego por su ID.
- Agregar nuevos juegos a la biblioteca.
- Actualizar la información de juegos existentes.
- Eliminar juegos de la base de datos.

## Requisitos

Para poder levantar este proyecto localmente, necesitarás tener instalado lo siguiente:

1.  **Java 21**: El proyecto utiliza las últimas características de Java 21.
2.  **Maven 3.x**: Se incluye el Maven Wrapper (`mvnw`), por lo que no es estrictamente necesario tenerlo instalado globalmente, pero sí una instalación de Java compatible.
3.  **PostgreSQL**: Base de datos relacional para la persistencia.
4.  **Variable de Entorno**: Debes configurar la variable de entorno `DB_PASSWORD` con la contraseña de tu usuario de PostgreSQL.

## Configuración de la Base de Datos

Por defecto, la aplicación busca una base de datos con los siguientes parámetros:

- **URL**: `jdbc:postgresql://localhost:5432/biblioteca_gamer`
- **Usuario**: `postgres`
- **Base de datos**: `biblioteca_gamer` (Asegúrate de crearla antes de iniciar la aplicación).

## Cómo ejecutar el proyecto

1.  Clona el repositorio.
2.  Asegúrate de tener PostgreSQL corriendo y la base de datos `biblioteca_gamer` creada.
3.  Configura la variable de entorno `DB_PASSWORD`.
4.  Desde la raíz del proyecto, ejecuta el siguiente comando en tu terminal:

    ```bash
    ./mvnw spring-boot:run
    ```

    O si estás en Windows:

    ```powershell
    .\mvnw.cmd spring-boot:run
    ```

La API estará disponible en `http://localhost:8080/api/juegos`.

## 🔗 Proyecto Frotend (Opcional)

**Nota:** Este backend se complementa con una frontend que consume la API de este proyecto para su funcionalidad. El proyecto frontend se encuentra en el siguiente enlace:

👉 [Repositorio Frontend](https://github.com/CarlosDavid-AM/biblioteca_gamer_frontend) | | https://github.com/CarlosDavid-AM/biblioteca_gamer_frontend

_Asegúrece de tener el frontend corriendo en `http://localhost:5173` para visualizar los resultados._
