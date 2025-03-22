package org.islasfilipinas.ApiRESTful;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * Controlador REST para gestionar operaciones CRUD sobre la entidad Producto.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Obtiene la lista de todos los productos disponibles.
     * @return ResponseEntity con la lista de productos o código 204 si está vacía.
     */
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.listarProductos();
        return productos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(productos);
    }

    /**
     * Obtiene un producto específico por su ID.
     * @param id Identificador del producto.
     * @return ResponseEntity con el producto encontrado o un mensaje de error si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerProducto(@PathVariable int id) {
        Producto producto = productoService.obtenerProductoPorId(id).orElse(null);

        if (producto == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Producto con ID " + id + " no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(producto);
    }

    /**
     * Agrega un nuevo producto a la base de datos.
     * @param producto Datos del producto a agregar.
     * @param result Resultado de la validación.
     * @return ResponseEntity con el producto creado o errores de validación.
     */
    @PostMapping
    public ResponseEntity<?> agregarProducto(@Valid @RequestBody Producto producto, BindingResult result) {
        System.out.println("📌 Se recibió una solicitud para agregar producto");

        if (result.hasErrors()) {
            System.out.println("⚠️ Errores detectados:");
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Producto nuevoProducto = productoService.agregarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    /**
     * Actualiza completamente un producto por su ID.
     * @param id Identificador del producto.
     * @param producto Datos actualizados del producto.
     * @param result Resultado de la validación.
     * @return ResponseEntity con el producto actualizado o errores de validación.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable int id, @Valid @RequestBody Producto producto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Producto productoActualizado = productoService.actualizarProducto(id, producto);

        return (productoActualizado != null) ? ResponseEntity.ok(productoActualizado) 
                                             : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    /**
     * Elimina un producto por su ID.
     * @param id Identificador del producto a eliminar.
     * @return ResponseEntity con mensaje de éxito o error si el producto no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
        boolean eliminado = productoService.eliminarProducto(id);
        
        return eliminado ? ResponseEntity.status(HttpStatus.NO_CONTENT).body("Producto eliminado correctamente") 
                         : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    /**
     * Filtra productos por nombre o precio.
     * @param nombre Nombre del producto (opcional).
     * @param precio Precio del producto (opcional).
     * @return ResponseEntity con la lista de productos filtrados.
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarProductos(@RequestParam(required = false) String nombre,
                                                           @RequestParam(required = false) Double precio) {
        List<Producto> productos = productoService.buscarProductos(nombre, precio);
        return ResponseEntity.ok(productos);
    }

    /**
     * Realiza una actualización parcial de un producto por su ID.
     * @param id Identificador del producto.
     * @param campos Mapa con los campos a actualizar.
     * @return ResponseEntity con el producto actualizado o error si no existe.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Producto> actualizarProductoParcial(@PathVariable int id, @RequestBody Map<String, Object> campos) {
        Producto productoActualizado = productoService.actualizarProductoParcial(id, campos);
        
        return (productoActualizado != null) ? ResponseEntity.ok(productoActualizado) 
                                             : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}

