package com.example.demo.controller;

import com.example.demo.models.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:3000") // ¡PERMITE QUE REACT SE CONECTE!
public class ProductoController {

    @Autowired
    private ProductoRepository repository;

    // 1. Obtener todos (GET)
    @GetMapping
    public List<Producto> listarProductos() {
        return repository.findAll();
    }

    // 2. Guardar nuevo (POST) - Solo Admin
    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {
        return repository.save(producto);
    }

    // 3. Eliminar (DELETE) - Solo Admin
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        repository.deleteById(id);
    }
}