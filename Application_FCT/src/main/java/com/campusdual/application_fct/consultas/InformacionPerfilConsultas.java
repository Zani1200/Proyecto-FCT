package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.excepciones.UsuarioActivo;
import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class InformacionPerfilConsultas implements ConsultasGeneral{
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

    @Override
    public void setActivo(Integer idUsuario) {

    }
}
