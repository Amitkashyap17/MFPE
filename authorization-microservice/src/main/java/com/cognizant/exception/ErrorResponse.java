package com.cognizant.exception;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private String message;
	private int statusCode;
	private Long exceptionTime;

}
