package com.campusdual.application_fct.excepciones;

import javax.persistence.PersistenceException;

public class NoExistenMensajes extends PersistenceException {

    public NoExistenMensajes() {
    }

    public NoExistenMensajes(String message) {
        super(message);
    }
}
