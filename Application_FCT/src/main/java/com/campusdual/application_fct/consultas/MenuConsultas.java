package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Participantes;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class MenuConsultas implements ConsultasGeneral{

    @Override
    public Usuario validarUsuario(String nombre, String contrasenha) {
        return null;
    }

    @Override
    public List<Mensaje> getMensajesGrupo(Integer idChat) {
        System.out.println(idChat);
            Session session = HibernateUtil.getSessionfactory().openSession();
            List<Object[]> mensajesQuery = session.createQuery("SELECT j.mensaje, u.usu_id, u.usu_nombre, u.usu_foto FROM Mensaje j " +
                    "INNER JOIN Participantes p ON j.id_part = p.part_id " +
                    "INNER JOIN Usuario u ON p.id_usu = u.usu_id " +
                    "INNER JOIN Chat c ON p.id_chat = c.chat_id " +
                    "WHERE c.chat_id = :id_chat" +
                    " ORDER BY j.msj_id", Object[].class).setParameter("id_chat",idChat).getResultList();
            List<Mensaje> mensajeArrayList = new ArrayList<>();
            for (Object[] columna : mensajesQuery) {
                Mensaje mensaje = new Mensaje(new Participantes(new Usuario((Integer) columna[1],(String) columna[2], (String) columna[3])),(String) columna[0]);
                mensajeArrayList.add(mensaje);
            }
            return mensajeArrayList;
    }

    @Override
    public Integer getParticipante(Usuario usuario, Chat chat) {
        return null;
    }
}
