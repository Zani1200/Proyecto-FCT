package com.campusdual.application_fct.excepciones;

public class ExisteUsuario extends Exception{

    public ExisteUsuario() {
    }

    public ExisteUsuario(String message) {
        super(message);
    }
}
