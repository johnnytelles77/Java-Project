package com.coderhouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coderhouse.dao.DaoFactory;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;
import com.coderhouse.models.Venta;

@SpringBootApplication
public class JavaProjectApplication implements CommandLineRunner{
	@Autowired
	private DaoFactory dao;

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		try {
			
			Producto producto1 = new Producto("Mac", 1500.0, 10);
			Producto producto2 = new Producto("Linux", 1000.0, 20);
			Producto producto3 = new Producto("Microsoft", 2000.0, 5);

			
			Cliente cliente1 = new Cliente("Johnny", "Raiford st", "9196678665");
			Cliente cliente2 = new Cliente("Juan", "Noble st", "9193339986");
			Cliente cliente3 = new Cliente("Joana", "Hillary st", "91965763210");
			
			dao.persistirCliente(cliente1);
			dao.persistirCliente(cliente2);
			dao.persistirCliente(cliente3);
			
			dao.persistirProducto(producto1);
			dao.persistirProducto(producto2);
			dao.persistirProducto(producto3);
			
			// Venta
			
	        Venta venta1 = new Venta(cliente1, List.of(producto1, producto2));
	        dao.persistirVenta(venta1);

	        System.out.println("Venta registrada: " + venta1);
				
		}catch(Exception e) {
			   e.printStackTrace(System.err);
		}
			
		}

	}
