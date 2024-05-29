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
        Session session =  HibernateUtil.getSessionfactory().openSession();
        System.out.println(idChat);
            List<Object[]> mensajesQuery = session.createQuery("SELECT j.mensaje, u.usu_id, u.usu_nombre, u.usu_foto, j.msj_id FROM Mensaje j " +
                    "INNER JOIN Participantes p ON j.id_part = p.part_id " +
                    "INNER JOIN Usuario u ON p.id_usu = u.usu_id " +
                    "INNER JOIN Chat c ON p.id_chat = c.chat_id " +
                    "WHERE c.chat_id = :id_chat" +
                    " ORDER BY j.msj_id", Object[].class).setParameter("id_chat",idChat).getResultList();
            List<Mensaje> mensajeArrayList = new ArrayList<>();
            for (Object[] columna : mensajesQuery) {
                Mensaje mensaje = new Mensaje((Integer) columna[4],new Participantes(new Usuario((Integer) columna[1],(String) columna[2], (String) columna[3])),(String) columna[0]);
                mensajeArrayList.add(mensaje);
            }
        session.close();
            return mensajeArrayList;
    }

    @Override
    public Integer getParticipante(Usuario usuario, Chat chat) {
        return null;
    }

    @Override
    public List<String> getTodosParticipantes(Integer chatid) {
        Session session =  HibernateUtil.getSessionfactory().openSession();
        session.beginTransaction();
        List<Object[]> participantes = session.createQuery("SELECT u.usu_nombre, u.usu_foto, u.usu_activo FROM Usuario u " +
                        "INNER JOIN Participantes p ON u.usu_id = p.id_usu " +
                        "INNER JOIN Chat c ON p.id_chat = c.chat_id " +
                        "WHERE c.chat_id = :chat_id", Object[].class).setParameter("chat_id",(chatid)).getResultList();
        List<String> usuarioList = new ArrayList<>();
        for (Object[] participante : participantes){
            if((Integer) participante[2] == 1) {
                participante[2] = "en linea";
            }else {
                participante[2] = "no en linea";
            }
            usuarioList.add(participante[0]+","+participante[1]+","+(participante[2]));
        }
        session.getTransaction().commit();
        session.close();
        return usuarioList;
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
        return null;
    }
}
