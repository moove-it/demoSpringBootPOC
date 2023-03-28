package com.example.demo;

import com.example.demo.model.Producto;
import com.example.demo.utils.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getTest() throws Exception {
		mockMvc.perform(get("/api/productos")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
	@Test
	public void postTest() throws Exception {
		Utilities utils = new Utilities();
		String productoJson = utils.createAProduct();

		mockMvc.perform(MockMvcRequestBuilders
						.post("/api/productos")
						.content(String.valueOf(productoJson))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
	@Test
	public void getProductByIdTest() throws Exception {
		Utilities utils = new Utilities();
		String productoJson = utils.createAProduct();

		mockMvc.perform(MockMvcRequestBuilders
					.post("/api/productos")
					.content(String.valueOf(productoJson))
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mockMvc.perform(get("/api/productos/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.nombre").value("Producto de ejemplo"))
				.andExpect(jsonPath("$.descripcion").value("Este es un producto de ejemplo"))
				.andExpect(jsonPath("$.precio").value(99.99));

	}

}
