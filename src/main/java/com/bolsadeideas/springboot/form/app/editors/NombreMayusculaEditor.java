package com.bolsadeideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

//Clase para convertir  texto digitado a mayuscula
public class NombreMayusculaEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		setValue(text.toUpperCase().trim());
	}

	
}
