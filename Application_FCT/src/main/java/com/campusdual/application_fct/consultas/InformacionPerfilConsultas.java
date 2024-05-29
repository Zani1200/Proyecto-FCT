package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.excepciones.UsuarioActivo;
import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Objects;

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
    public List<Chat> getTodosChat(Integer idUsuario) {
        return null;
    }

    @Override
    public void setActivo(Integer idUsuario) {

    }
    @Override
    public boolean validarCambioNombre(Usuario usuario, String nombre) {
        Session session =  HibernateUtil.getSessionfactory().openSession();
        try {
            session.createQuery("SELECT j.usu_nombre FROM Usuario j WHERE j.usu_nombre = :nombre", String.class)
                    .setParameter("nombre", nombre).getSingleResult();
            return false;
        } catch (NoResultException e){
            session.beginTransaction();
            Query actualizarActividad = session.createQuery("UPDATE Usuario u SET usu_nombre = :nombre WHERE usu_id = :usu_id")
                    .setParameter("usu_id",usuario.getUsu_id()).setParameter("nombre",nombre);
            int i = actualizarActividad.executeUpdate();
            System.out.println("Se ha realizado "+i+" cambio/s");
            session.getTransaction().commit();
            session.close();
            return true;
        }
    }
    @Override
    public boolean validarCambioContraseña(String contraseña, String contaseñaRepetida, Usuario usuario) {
        if(Objects.equals(contraseña, contaseñaRepetida)){
            Session session = HibernateUtil.getSessionfactory().openSession();
            session.beginTransaction();
            Query actualizarActividad = session.createQuery("UPDATE Usuario u SET u.usu_contrasenha = :contrasenha WHERE u.usu_id = :usu_id")
                    .setParameter("usu_id",usuario.getUsu_id()).setParameter("contrasenha",contraseña);
            int i = actualizarActividad.executeUpdate();
            System.out.println("Se ha realizado "+i+" cambio/s");
            session.getTransaction().commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public void setFoto(String foto,Usuario usuario) {
        Session session = HibernateUtil.getSessionfactory().openSession();
        session.beginTransaction();
        Query actualizarActividad = session.createQuery("UPDATE Usuario u SET u.usu_foto = :foto WHERE u.usu_id = :usu_id")
                .setParameter("usu_id",usuario.getUsu_id()).setParameter("foto",foto);
        int i = actualizarActividad.executeUpdate();
        System.out.println("Se ha realizado "+i+" cambio/s");
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Object setPuerto() {
        return null;
    }
}
