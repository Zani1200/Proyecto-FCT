package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.excepciones.UsuarioActivo;
import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HelloControllerConsultas implements ConsultasGeneral{

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
        Session session = HibernateUtil.getSessionfactory().openSession();
        session.beginTransaction();
        Query actualizarActividad = session.createQuery("UPDATE Usuario u " +
                "SET u.usu_activo = '0' " +
                "WHERE u.usu_id = :idUsuario").setParameter("idUsuario",idUsuario);
        int i = actualizarActividad.executeUpdate();
        System.out.println("Se ha realizado "+i+" cambio/s");
        session.getTransaction().commit();
        session.close();
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
}
