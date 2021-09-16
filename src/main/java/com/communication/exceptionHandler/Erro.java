package com.communication.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Erro {
	private String status;
	private String messageErro;
	private String value;
	private String messageForDeveloper;

}
