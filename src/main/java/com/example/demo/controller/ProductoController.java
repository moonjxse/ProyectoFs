package com.example.demo.controller;

import com.example.demo.models.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
@Tag(name = "Productos", description = "Gestión de productos")
public class ProductoController {

    @Autowired
    private ProductoRepository repository;

    // 🔹 LISTAR PRODUCTOS (Todos los usuarios)
    @GetMapping
    @Operation(summary = "Listar productos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida")
    })
    public List<Producto> listarProductos() {
        return repository.findAll();
    }

    // 🔹 CREAR PRODUCTO (Solo ADMIN)
    @PostMapping
    @Operation(summary = "Crear producto")
    public Producto guardarProducto(@RequestBody Producto producto) {
        if(producto.getSoloAdmin() == null) {
            producto.setSoloAdmin(false);
        }
        return repository.save(producto);
    }

    // 🔹 ACTUALIZAR PRODUCTO (Solo ADMIN)
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        return repository.findById(id).map(producto -> {
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setStock(productoActualizado.getStock());
            producto.setCategoria(productoActualizado.getCategoria());
            producto.setImagen(productoActualizado.getImagen());
            producto.setSoloAdmin(productoActualizado.getSoloAdmin() != null ? productoActualizado.getSoloAdmin() : false);
            return repository.save(producto);
        }).orElseGet(() -> {
            productoActualizado.setId(id);
            return repository.save(productoActualizado);
        });
    }

    // 🔹 ELIMINAR PRODUCTO (Solo ADMIN)
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto")
    public void eliminarProducto(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
