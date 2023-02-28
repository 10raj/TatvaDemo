package com.example.UserManagementService.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
	
	private String name;
	private String userName;
	private String password;
	private String email;
	private List<Integer> roleCodes;
}
