package com.campusdual.application_fct.util;

import com.campusdual.application_fct.entities.Usuario;

public class UsuarioHandler {

    public static Usuario USUARIO;

    public UsuarioHandler(Usuario usuario) {
        UsuarioHandler.USUARIO = usuario;
    }
}
