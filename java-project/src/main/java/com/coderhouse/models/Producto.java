package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Entity
@Table(name = "Productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private Double precio;

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
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    
}
