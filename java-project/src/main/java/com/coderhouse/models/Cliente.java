package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



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
@Schema(description = "Modelo de Cliente", title = "Modelo de Cliente")
@Entity
@Table(name = "Clientes")
public class Cliente {

	@Schema(description = "ID del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	@Schema(description = "nombre del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Juan")
    @Column(name = "nombre", nullable = false)
    private String nombre;

	@Schema(description = "Email del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "juan31@gmail.com")
    @Column(name = "email")
    private String email;

	@Schema(description = "Telefono del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "9193331217")
    @Column(name = "telefono", unique = true, nullable = false)
    private String telefono;

    // Lado propietario de la relación ManyToMany con Producto
	@Schema(description = "Listado de productos que compro el cliente")
    @ManyToMany(mappedBy = "clientes", fetch = FetchType.EAGER)
    private List<Producto> productos = new ArrayList<>();



    @Schema(description = "Cuando compro el producto", example = "11/03/1991")
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
}

