package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.excepciones.UsuarioActivo;
import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

public class RegistroChatControllerConsultas implements ConsultasGeneral{
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
    public List<Chat> getTodosChat(Integer idUsuario) {
        return null;
    }


    @Override
    public void setActivo(Integer idUsuario) {

    }

    @Override
    public boolean validarCambioNombre(Usuario usuario, String nombre) {
        return false;
    }

    @Override
    public boolean validarCambioContraseña(String contaseña, String contaseñaRepetida, Usuario usuario) {
        return false;
    }

    @Override
    public void setFoto(String foto, Usuario usuario) {

    }

    @Override
    public Object setPuerto() {
        Integer ultimoPuerto;
        try {
            Session session = HibernateUtil.getSessionfactory().openSession();
            session.beginTransaction();
            ultimoPuerto = (Integer) session.createSQLQuery("SELECT c.chat_puerto FROM Chat c " +
                    "ORDER BY c.chat_puerto DESC " +
                    "LIMIT 1").getSingleResult();
            return ultimoPuerto+1;
        }catch (NoResultException e) {
             return 6000;
        }
    }
}
