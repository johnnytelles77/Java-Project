package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.VentasDTO;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	
	public  List<Cliente> getAllClientes(){
		return clienteRepository.findAll();
	}
	
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
	}
	
	@Transactional
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public Cliente updateClienteById(Long id, Cliente clienteDetails) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
		
		cliente.setNombre(clienteDetails.getNombre());
		cliente.setEmail(clienteDetails.getEmail());
		
	    if (clienteDetails.getTelefono() != null && !clienteDetails.getTelefono().isEmpty()) {
	        cliente.setTelefono(clienteDetails.getTelefono());
	    }
	    return clienteRepository.save(cliente);
	}
	
	public void deleteClienteById(Long id) {
	    if (!clienteRepository.existsById(id)) {
	        throw new IllegalArgumentException("Cliente con id " + id + " no existe");
	    }
	    clienteRepository.deleteById(id);
	}
	
	@Transactional
	public Cliente venderProductoACliente(VentasDTO dto) {
		Cliente cliente = clienteRepository.findById(dto.getClienteId())
				.orElseThrow( () -> new IllegalArgumentException("Cliente no encontrado.!"));
		for (Long productoId : dto.getProductoIds()) {
			Producto producto = productoRepository.findById(productoId)
					.orElseThrow( () -> new IllegalArgumentException("Producto no encontrado.!"));
			cliente.getProductos().add(producto);
			producto.getClientes().add(cliente);
			productoRepository.save(producto);
		}
		return clienteRepository.save(cliente);

		
	}

	

}
