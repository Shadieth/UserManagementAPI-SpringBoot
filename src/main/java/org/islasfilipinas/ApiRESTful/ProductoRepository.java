package org.islasfilipinas.ApiRESTful;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad Producto.
 * Proporciona métodos personalizados para filtrar productos según diferentes criterios.
 */
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
    /**
     * Busca productos cuyo nombre contenga la palabra clave especificada.
     * @param nombre Palabra clave a buscar dentro del nombre del producto.
     * @return Lista de productos que contienen la palabra clave en su nombre.
     */
    List<Producto> findByNombreContaining(String nombre);

    /**
     * Busca productos que tengan exactamente el precio especificado.
     * @param precio Precio exacto del producto.
     * @return Lista de productos con el precio indicado.
     */
    List<Producto> findByPrecio(Double precio);

    /**
     * Busca productos cuyo nombre contenga la palabra clave y tengan el precio especificado.
     * @param nombre Palabra clave a buscar en el nombre.
     * @param precio Precio exacto del producto.
     * @return Lista de productos que coincidan con ambos criterios.
     */
    List<Producto> findByNombreContainingAndPrecio(String nombre, Double precio);

    /**
     * Busca productos cuyo precio esté dentro de un rango determinado.
     * @param precioMin Precio mínimo del rango.
     * @param precioMax Precio máximo del rango.
     * @return Lista de productos dentro del rango de precios especificado.
     */
    List<Producto> findByPrecioBetween(Double precioMin, Double precioMax);
}
