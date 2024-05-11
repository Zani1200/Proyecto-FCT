package com.campusdual.application_fct.excepciones;

import javax.persistence.NoResultException;

public class NoExisteUsuario extends NoResultException {

    public NoExisteUsuario() {
    }

    public NoExisteUsuario(String message) {
        super(message);
    }
}
