package org.islasfilipinas.ApiRESTful;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Clase que representa la entidad Producto en la base de datos.
 */
@Entity // Indica que esta clase es una entidad de JPA (se mapeará a una tabla en la base de datos).
public class Producto {

    @Id // Marca este campo como la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID (autoincremental en bases de datos relacionales).
    private int id;

    @NotBlank(message = "El nombre es obligatorio") // Valida que el campo no sea nulo ni vacío.
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres") // Define restricciones en la longitud del nombre.
    @Column(nullable = false) // Indica que el campo no puede ser nulo en la base de datos.
    private String nombre;

    @NotNull(message = "El precio es obligatorio") // Valida que el precio no sea nulo.
    @Positive(message = "El precio debe ser un número positivo y mayor que cero") // Asegura que el precio sea un número positivo.
    @Column(nullable = false) // Campo obligatorio en la base de datos.
    private Double precio;

    @Column(name = "fecha_creacion", updatable = false) // Define la columna con un nombre específico y evita que se actualice después de la creación.
    private LocalDateTime fechaCreacion;

    /**
     * Método ejecutado automáticamente antes de persistir un nuevo producto en la base de datos.
     * Asigna la fecha y hora actuales al campo fechaCreacion.
     */
    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}


