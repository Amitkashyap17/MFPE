package com.cognizant.exception;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

}
