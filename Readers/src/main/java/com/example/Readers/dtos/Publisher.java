package com.example.Readers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher{

	private Long id;
	private String name;
	private String address;
}
