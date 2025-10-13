package com.coderhouse.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Modelo de Venta de Clientes DTO", title = "ventas DTO")
public class VentasDTO {
	
	@Schema(description = "Cliente ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	private Long clienteId;
	
	@Schema(description = "Listado de ID's de Productos", requiredMode = Schema.RequiredMode.REQUIRED, example = "['1', '2', '3']")
	private List<Long> productoIds;

	
	

}
