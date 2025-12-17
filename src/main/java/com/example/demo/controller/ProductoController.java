package com.example.demo.controller;

import com.example.demo.models.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
@Tag(name = "Productos", description = "Gestión de productos de la tienda de Halloween")
@SecurityRequirement(name = "Bearer Authentication")
public class ProductoController {

    @Autowired
    private ProductoRepository repository;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Listar productos", description = "Obtiene la lista de productos disponibles según el rol del usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente"),
        @ApiResponse(responseCode = "403", description = "No autorizado - Se requiere rol USER")
    })
    public List<Producto> listarProductos(Authentication authentication) {
        List<Producto> todosLosProductos = repository.findAll();

       
        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            
            return todosLosProductos;
        } else {
           
            return todosLosProductos.stream()
                .filter(producto -> !Boolean.TRUE.equals(producto.getSoloAdmin()))
                .collect(Collectors.toList());
        }
    }

   
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Crear producto", description = "Crea un nuevo producto en el catálogo (Solo administradores)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto creado exitosamente"),
        @ApiResponse(responseCode = "403", description = "No autorizado - Se requiere rol ADMIN")
    })
    public Producto guardarProducto(@RequestBody Producto producto) {
        return repository.save(producto);
    }

   
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarProducto(@PathVariable Long id) {
        repository.deleteById(id);
    }
}