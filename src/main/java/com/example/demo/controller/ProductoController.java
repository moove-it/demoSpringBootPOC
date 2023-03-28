package com.example.demo.controller;

import com.example.demo.model.Producto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private Map<Long, Producto> productos = new HashMap<>();

    @GetMapping
    public List<Producto> obtenerProductos() {
        return new ArrayList<>(productos.values());

    }
    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productos.get(id);

    }
    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto) {
        producto.setId((long) (productos.size() + 1));
        productos.put(producto.getId(), producto);

        return producto;

    }
    @PutMapping("{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Producto productoExistente = productos.get(id);
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productos.put(id, productoExistente);

        return productoExistente;

    }
    @DeleteMapping("{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productos.remove(id);

    }

}
