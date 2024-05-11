package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;

import java.util.List;

public class RegistroUsuarioConsultas implements ConsultasGeneral{
    @Override
    public Usuario validarUsuario(String nombre, String contrasenha) {
        return null;
    }

    @Override
    public List<Mensaje> getMensajesGrupo() {
        return null;
    }
}
