package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ServidorConsultas implements ConsultasGeneral{
    @Override
    public Usuario validarUsuario(String nombre, String contrasenha) {
        return null;
    }

    @Override
    public List<Mensaje> getMensajesGrupo(Integer idChat) {
        return null;
    }

    @Override
    public Integer getParticipante(Usuario usuario, Chat chat) {
        Session session =  HibernateUtil.getSessionfactory().openSession();
        session.beginTransaction();
        System.out.println(usuario.getUsu_id()+" "+(chat.getChat_id()));
        Integer participantesId = session.createQuery("SELECT p.part_id FROM Participantes p " +
                "INNER JOIN Usuario u ON p.id_usu = u.usu_id " +
                "INNER JOIN Chat c ON p.id_chat = c.chat_id " +
                "WHERE u.usu_id = :usu_id AND c.chat_id = :chat_id", Integer.class).setParameter("usu_id",usuario.getUsu_id())
                .setParameter("chat_id",(chat.getChat_id())).getSingleResult();
        System.out.println(participantesId);
        session.getTransaction().commit();
        session.close();
        return participantesId;
    }

    @Override
    public List<String> getTodosParticipantes(Integer chatId) {
        return null;
    }


}
