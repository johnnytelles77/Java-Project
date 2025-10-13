package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Modelo de Producto", title = "Modelo de Producto")
@Entity
@Table(name = "Productos")
public class Producto {

	@Schema(description = "ID del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	@Schema(description = "Nombre del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Iphone 16")
    @Column(name = "nombre", nullable = false)
    private String nombre;

	@Schema(description = "Precio del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "$999.99")
    @Column(name = "precio", nullable = false)
    private Double precio;

	@Schema(description = "Stock del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @Column(name = "stock", nullable = false)
    private Integer stock;

    // Relación ManyToMany con Cliente (lado propietario)
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "producto_cliente", 
			joinColumns = @JoinColumn(name = "producto_id"), 
			inverseJoinColumns = @JoinColumn(name = "cliente_id"))
    @JsonIgnore
    private List<Cliente> clientes = new ArrayList<>();


    // Relación ManyToOne con Categoria
	@Schema(description = "Categoria del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Desktops")
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    
}
