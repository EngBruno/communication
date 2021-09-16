package com.communication.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Erro {
	private String messageForUser;
	private String nameField;
	private String messageForDeveloper;

}
