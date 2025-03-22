package org.islasfilipinas.ApiRESTful;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    
    private final ProductoRepository productoRepository;

    // Constructor para inyectar el repositorio de productos
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    
    // Listar todos los productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();  // Devuelve todos los productos del repositorio
    }

    // Buscar producto por ID
    public Optional<Producto> obtenerProductoPorId(int id) {
        return productoRepository.findById(id);  // Devuelve un Optional con el producto encontrado por ID
    }

    // Crear un nuevo producto
    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);  // Guarda el nuevo producto en el repositorio
    }

    // Eliminar un producto por ID
    public boolean eliminarProducto(int id) {
        
        // Verifica si el producto existe antes de eliminarlo
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);  // Elimina el producto del repositorio
            return true;  // Retorna true si la eliminación fue exitosa
        }
        return false;  // Retorna false si el producto no existe
    }

    // Actualizar un producto por ID
    public Producto actualizarProducto(int id, Producto producto) {
        // Busca el producto por su ID
        Optional<Producto> productoExistente = productoRepository.findById(id);
        
        if (productoExistente.isPresent()) {
            Producto productoActualizado = productoExistente.get();  // Obtiene el producto existente
            productoActualizado.setNombre(producto.getNombre());  // Actualiza el nombre
            productoActualizado.setPrecio(producto.getPrecio());  // Actualiza el precio
            return productoRepository.save(productoActualizado);  // Guarda el producto actualizado
        } else {
            return null;  // Retorna null si el producto no existe
        }
    }
    
    // Filtrar productos por nombre y/o precio
    public List<Producto> buscarProductos(String nombre, Double precio) {
        // Si se pasan ambos parámetros (nombre y precio), se filtra por ambos
        if (nombre != null && precio != null) {
            return productoRepository.findByNombreContainingAndPrecio(nombre, precio);
        } 
        // Si solo se pasa el nombre, se filtra solo por nombre
        else if (nombre != null) {
            return productoRepository.findByNombreContaining(nombre);
        } 
        // Si solo se pasa el precio, se filtra solo por precio
        else if (precio != null) {
            return productoRepository.findByPrecio(precio);
        } 
        // Si no se pasan parámetros, se devuelven todos los productos
        else {
            return productoRepository.findAll();
        }
    }

    // Actualización parcial de productos (por campos específicos)
    public Producto actualizarProductoParcial(int id, Map<String, Object> campos) {
        // Busca el producto por su ID
        Producto producto = productoRepository.findById(id).orElse(null);
        
        if (producto != null) {
            // Itera sobre los campos proporcionados en el mapa y actualiza solo los que están presentes
            campos.forEach((key, value) -> {
                switch (key) {
                    case "nombre":
                        producto.setNombre((String) value);  // Actualiza el nombre
                        break;
                    case "precio":
                        producto.setPrecio((Double) value);  // Actualiza el precio
                        break;
                }
            });
            return productoRepository.save(producto);  // Guarda el producto con las actualizaciones parciales
        }
        return null;  // Retorna null si el producto no se encuentra
    }
}

