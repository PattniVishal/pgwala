package com.pgwala.propertyService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
	int status;
	String message;
	Object data;
}
