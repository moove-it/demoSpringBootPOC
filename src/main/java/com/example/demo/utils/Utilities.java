package com.example.demo.utils;

import com.example.demo.model.Producto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utilities {
    public Utilities(){

    }
    public String createAProduct() throws JsonProcessingException {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto de ejemplo");
        producto.setDescripcion("Este es un producto de ejemplo");
        producto.setPrecio(99.99);

        ObjectMapper mapper = new ObjectMapper();

        String productoJson = mapper.writeValueAsString(producto);
        return productoJson;

    }

}
