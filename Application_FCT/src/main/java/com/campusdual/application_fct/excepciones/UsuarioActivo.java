package com.campusdual.application_fct.excepciones;

public class UsuarioActivo extends Exception{

    public UsuarioActivo() {

    }
    public UsuarioActivo(String message) {
        super(message);
    }
}
