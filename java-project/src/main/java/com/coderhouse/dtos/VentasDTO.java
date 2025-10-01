package com.coderhouse.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentasDTO {
	
	private Long clienteId;
	
	private List<Long> productoIds;

	
	

}
