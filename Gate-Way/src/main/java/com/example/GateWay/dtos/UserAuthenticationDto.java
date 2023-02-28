package com.example.GateWay.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationDto {

	private String user;
	private String password;
	private String email;
}
