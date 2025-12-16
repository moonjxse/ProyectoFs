public package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
    private String imagen;

    // Constructores vacíos y con datos
    public Producto() {}
    public Producto(String nombre, Double precio, Integer stock, String imagen) {
        this.nombre = nombre; this.precio = precio; this.stock = stock; this.imagen = imagen;
    }

    // Getters y Setters (OBLIGATORIOS)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
} Producto {
    
}
