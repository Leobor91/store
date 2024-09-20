# Store Application

## Descripción

La Store Application es una aplicación de ejemplo desarrollada con Spring Boot y Gradle. Esta aplicación sigue una arquitectura en capas y está diseñada para gestionar clientes en una tienda.

## Estructura del Proyecto

El proyecto está organizado en las siguientes capas:

1. **Capa de Controladores (delivery)**:
   - Maneja las solicitudes HTTP y devuelve respuestas HTTP.
   - Ejemplo: `ClientControler.java`

2. **Capa de Servicios (application)**:
   - Contiene la lógica de negocio de la aplicación.
   - Define interfaces y sus implementaciones para los servicios.
   - Ejemplo: `ClientService.java`

3. **Capa de Repositorios (repository)**:
   - Maneja la interacción con la base de datos.
   - Utiliza Spring Data JPA para realizar operaciones CRUD.
   - Ejemplo: `ClientRepository.java`

4. **Capa de Dominio (domain)**:
   - Contiene las entidades del dominio de la aplicación.
   - Ejemplo: `Client.java`

5. **Capa de Configuración (config)**:
   - Contiene las clases de configuración de la aplicación.
   - Ejemplo: `StoreApplication.java`

## Requisitos

- Java 17
- Gradle 7.0 o superior
- Spring Boot 2.5.0 o superior

## Configuración de la Base de Datos

Asegúrate de configurar las propiedades de la base de datos en el archivo `application.properties` o `application.yml`. Aquí tienes un ejemplo de configuración para una base de datos H2:

```properties
# H2 Database (in-memory)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

Ejecución de la Aplicación
./gradlew bootRun

Ejecución de Pruebas
./gradlew test

Endpoints
La aplicación expone los siguientes endpoints:  
GET /api/clients/list: Obtiene la lista de todos los clientes.
Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para discutir cualquier cambio que desees realizar.  
Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

Esta estructura y descripción proporcionan una visión clara y concisa del proyecto, facilitando su comprensión y uso.

## Estructura del Proyecto

src
└── main
    ├── java
    │   └── com
    │       └── Store
    │           ├── application
    │           │   └── ClientService.java
    │           ├── config
    │           │   └── StoreApplication.java
    │           ├── delivery
    │           │   └── ClientControler.java
    │           ├── domain
    │           │   └── Client.java
    │           └── repository
    │               └── ClientRepository.java
    └── resources
        ├── application.properties
        └── schema.sql
