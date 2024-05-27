package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.excepciones.UsuarioActivo;

import java.util.List;

public class HelloControllerConsultas implements ConsultasGeneral{
    private Usuario usuario;

    public HelloControllerConsultas() {
    }

    public HelloControllerConsultas(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Object validarUsuario(String nombre, String contrasenha) throws UsuarioActivo {
        return null;
    }

    @Override
    public List<Mensaje> getMensajesGrupo(Integer idChat) {
        return null;
    }

    @Override
    public Integer getParticipante(Usuario usuario, Chat chat) {
        return null;
    }

    @Override
    public List<String> getTodosParticipantes(Integer chatId) {
        return null;
    }
}
