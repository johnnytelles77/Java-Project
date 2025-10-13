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

import com.coderhouse.dtos.VentasDTO;
import com.coderhouse.models.Cliente;
import com.coderhouse.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Gestion de Clientes", description = "Endpoints para gestionar Clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	
	@Operation(summary = "Obtener Lista de Clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de Clientes Obtenida Correctamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al Obtener los Clientes", 
			content = @Content),
			@ApiResponse(responseCode = "500", description = "Error Interno del Servidor", 
			content = @Content),
	})
	@GetMapping
	public ResponseEntity<List<Cliente>> getAllClientes() {
		try {
			List<Cliente> clientes = clienteService.getAllClientes();
			return ResponseEntity.ok(clientes); //200
		}catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build(); // 404
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}

	
	@Operation(summary = "Obtener un Cliente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente Obtenido Correctamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al Obtener el Cliente", 
			content = @Content),
			@ApiResponse(responseCode = "500", description = "Error Interno del Servidor", 
			content = @Content),
	})
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
		try {
				Cliente cliente = clienteService.findById(id);
				return ResponseEntity.ok(cliente); // 200
		}catch(IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); // 404
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}

	
	@Operation(summary = "Crear Cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cliente Creado Correctamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
					}),
			@ApiResponse(responseCode = "400", description = "Error al Intentar Crear el Cliente", 
			content = @Content),
			@ApiResponse(responseCode = "500", description = "Error Interno del Servidor", 
			content = @Content),
	})
	@PostMapping
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		try {
			Cliente clienteCreado = clienteService.saveCliente(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado); // 201 created
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
	

	@Operation(summary = "Modificar Cliente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cliente Modificado Correctamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al Intentar Modificar el Cliente", 
			content = @Content),
			@ApiResponse(responseCode = "500", description = "Error Interno del Servidor", 
			content = @Content),
	})
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateClienteById(@PathVariable Long id, @RequestBody Cliente clienteModificado){
		try {
			Cliente updateCliente = clienteService.updateClienteById(id, clienteModificado);
			return ResponseEntity.ok(updateCliente);
		}catch(IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); // 404		
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
	
	
	@Operation(summary = "Eliminar Cliente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Cliente Eliminado Correctamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al Intentar Eliminar el Cliente", 
			content = @Content),
			@ApiResponse(responseCode = "500", description = "Error Interno del Servidor", 
			content = @Content),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClienteById(@PathVariable Long id){
		try {
			clienteService.deleteClienteById(id);
			return ResponseEntity.noContent().build();//204
			
		}catch(IllegalArgumentException e) {
				return ResponseEntity.notFound().build(); // 404	
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
	
	
	@Operation(summary = "Vender Producto a Cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Venta Procesada Correctamente",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
					}),
			@ApiResponse(responseCode = "404", description = "Error al Intentar Procesar Venta", 
			content = @Content),
			@ApiResponse(responseCode = "500", description = "Error Interno del Servidor", 
			content = @Content),
	})
	@PostMapping("/vender")
	public ResponseEntity<Cliente> venderProductoACliente(@RequestBody VentasDTO dto){
		try {
			Cliente cliente = clienteService.venderProductoACliente(dto);
			return ResponseEntity.ok(cliente);//200
			
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build(); // 400

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
		}
	}

}