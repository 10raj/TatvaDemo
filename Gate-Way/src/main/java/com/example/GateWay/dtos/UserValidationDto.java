package com.example.GateWay.dtos;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserValidationDto {

	private String username;
	private List<RolesDto>  roles;
}