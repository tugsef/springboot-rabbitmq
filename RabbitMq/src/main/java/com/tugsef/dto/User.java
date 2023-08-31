package com.tugsef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private String id;
	private String firstName;
	private String lastName;
}
