# ApiRESTful

Este proyecto es una API RESTful para la gestión de productos utilizando Spring Boot y JPA (Java Persistence API). Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre productos almacenados en una base de datos MySQL. Además, proporciona funciones de búsqueda avanzada, como filtrar productos por nombre, precio exacto, rango de precios y una combinación de estos criterios.

## Características
- **Operaciones CRUD**: Crear, leer, actualizar y eliminar productos.
- **Búsqueda avanzada**: Buscar productos por nombre, precio y rango de precios.
- **Validación de entrada**: Se utilizan validaciones para garantizar la integridad de los datos recibidos.
- **Excepciones**: Manejo de excepciones para respuestas claras y precisas ante errores de validación o de operación.

## Tecnologías
- **Spring Boot**: Framework principal para el desarrollo de la API.
- **Spring Data JPA**: Para la gestión de la persistencia en la base de datos.
- **MySQL**: Base de datos relacional utilizada para almacenar los productos.
- **Hibernate**: ORM para la gestión de entidades JPA.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.

## Requisitos
- JDK 17 o superior.
- MySQL 5.7 o superior.

## Instalación

1. Clonar el repositorio:

    ```bash
    git clone https://github.com/tu-usuario/ApiRESTful.git
    ```

2. Configurar la base de datos MySQL:
   - Crear una base de datos llamada `Productos_db`.
   - Configurar las credenciales de la base de datos en el archivo `application.properties`.

3. Ejecutar la aplicación:

    ```bash
    mvn spring-boot:run
    ```

## Endpoints
- `GET /productos`: Obtener todos los productos.
- `GET /productos/{id}`: Obtener un producto por su ID.
- `POST /productos`: Crear un nuevo producto.
- `PUT /productos/{id}`: Actualizar un producto existente.
- `DELETE /productos/{id}`: Eliminar un producto.
- `GET /productos/buscar`: Buscar productos por nombre y/o precio.

## Contribuciones
¡Las contribuciones son bienvenidas! Si deseas mejorar o agregar nuevas características, por favor crea un pull request o abre un issue.
