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
@Schema(description = "Modelos de Categoria", title = "Modelo de Categoria")
@Entity
@Table(name = "categorias")
public class Categoria {

	@Schema(description = "ID del Categoria", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;

	@Schema(description = "Nombre de la categoria", requiredMode = Schema.RequiredMode.REQUIRED, example = "Laptos")
    @Column(unique = true, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Producto> productos = new ArrayList<>();
    


}
