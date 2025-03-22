package org.islasfilipinas.ApiRESTful; // Paquete donde se encuentra la clase principal de la aplicación.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * La anotación @SpringBootApplication indica que esta es una aplicación Spring Boot y habilita la configuración automática.
 */
@SpringBootApplication
public class ApiResTfulApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiResTfulApplication.class, args);
    }

    // Comando para ejecutar la aplicación como un JAR independiente:
    // java -jar ApiRESTful-0.0.1-SNAPSHOT.jar
}
