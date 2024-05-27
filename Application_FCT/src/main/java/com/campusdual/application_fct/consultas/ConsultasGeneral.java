package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.excepciones.UsuarioActivo;

import java.util.List;

public interface ConsultasGeneral {

    Object validarUsuario(String nombre, String contrasenha) throws UsuarioActivo;

    List<Mensaje> getMensajesGrupo(Integer idChat);

    Integer getParticipante(Usuario usuario, Chat chat);

    List<String> getTodosParticipantes(Integer chatId);

    void setActivo(Integer idUsuario);
    boolean validarCambioNombre(Usuario usuario, String nombre);
    boolean validarCambioContraseña(String contaseña, String contaseñaRepetida, Usuario usuario);
    void setFoto(String foto, Usuario usuario);
}
