package com.campusdual.application_fct.excepciones;

public class UsuarioNoValido extends Exception{
    public UsuarioNoValido() {
    }

    public UsuarioNoValido(String message) {
        super(message);
    }
}
