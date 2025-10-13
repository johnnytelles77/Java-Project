package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dtos.AsignacionCategoriaProducto;
import com.coderhouse.models.Producto;
import com.coderhouse.services.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/api/productos")
@Tag(name = "Gestion de Productos", description = "Endpoints para gestionar Productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@Operation(summary = "Obtener Lista de Productos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de Productos Obtenida Correctamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al Obtener los Productos", 
			content = @Content),
			@ApiResponse(responseCode = "500", description = "Error Interno del Servidor", 
			content = @Content),
			
	})
	@GetMapping
	public ResponseEntity<List<Producto>> getAllProductos() {
		try {
			List<Producto> productos = productoService.getAllProductos();
			return ResponseEntity.ok(productos);
		}catch(IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); // 404
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		}
	}

	@Operation(summary = "Obtener un Producto por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto Obtenido Correctamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al Obtener el Producto", 
			content = @Content),
			@ApiResponse(responseCode = "500", description = "Error Interno del Servidor", 
			content = @Content),
			
	})
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
		try {
			Producto producto = productoService.getProductoById(id);
			return ResponseEntity.ok(producto);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build(); // 404
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		}
	}

	@Operation(summary = "Crear Producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "producto Creado Correctamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
					}),
			@ApiResponse(responseCode = "500", description = "Error Interno del Servidor", 
			content = @Content),
			
	})
	@PostMapping
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
		try {
			Producto createdProducto = productoService.createProducto(producto);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdProducto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		}
	}
	
	

	@Operation(summary = "Modificacion de Producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Producto Modificado Correctamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al Modificar el Producto", 
			content = @Content),
			@ApiResponse(responseCode = "500", description = "Error Interno del Servidor", 
			content = @Content),
			
	})
	@PutMapping("/{id}")
	public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
		try {
			Producto updatedProducto = productoService.updateProducto(id, productoDetails);
			return ResponseEntity.ok(updatedProducto);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();//404
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		}
	}

	
	@Operation(summary = "Eliminar Producto por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Producto Eliminado",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al Eliminar el Producto", 
			content = @Content),
			@ApiResponse(responseCode = "500", description = "Error Interno del Servidor", 
			content = @Content),
			
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
		try {
			productoService.deleteProducto(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();//404
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();//500
		}
	}
	
	
	@Operation(summary = "Asignar Categoria a un Producto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Categoria Asignada Correctamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al Intentar Obtener el Producto o Categoria", 
			content = @Content),
			@ApiResponse(responseCode = "500", description = "Error Interno del Servidor", 
			content = @Content),
			
	})
	@PostMapping("/asignar-categoria")
	public ResponseEntity<Producto> asignarCategoriaACurso(@RequestBody AsignacionCategoriaProducto dto){
		try {
			
			Producto productoActualizado = productoService.asignarCategoriaAProducto(
					dto.getProductoId(), 
					dto.getCategoriaId()
					);
			return ResponseEntity.ok(productoActualizado);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();//404
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();	//500	
		}
	}
}