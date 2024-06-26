package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.excepciones.NoExisteUsuario;
import com.campusdual.application_fct.excepciones.UsuarioActivo;
import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class LoginConsultas implements ConsultasGeneral{
    @Override
    public Object validarUsuario(String nombre, String contrasenha) throws UsuarioActivo, NoResultException {
        Session session =  HibernateUtil.getSessionfactory().openSession();
        Usuario usuario = null;
        try {
            usuario = session.createQuery("SELECT j FROM Usuario j WHERE j.usu_nombre = :nombre AND j.usu_contrasenha = :contrasenha", Usuario.class)
                    .setParameter("nombre", nombre).setParameter("contrasenha", contrasenha).getSingleResult();
        } catch (NoResultException e){
            throw new NoExisteUsuario("El usuario o contraseña incorrectos");

        }
        Integer usuarioActivo = (Integer) session.createQuery("SELECT j.usu_activo " +
                "FROM Usuario j " +
                "WHERE usu_id = :id").setParameter("id", usuario.getUsu_id()).getSingleResult();

        if(usuarioActivo == 0){
            session.beginTransaction();
            Query actualizarActividad = session.createQuery("UPDATE Usuario u " +
                    "SET u.usu_activo = 1 " +
                    "WHERE u.usu_id = :id").setParameter("id", usuario.getUsu_id());
            int i = actualizarActividad.executeUpdate();
            System.out.println("Se ha realizado "+i+" cambio/s");
            session.getTransaction().commit();
            session.close();
            return usuario;
        } else {
            throw new UsuarioActivo("El usuario ya esta activo");
        }
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
        return null;
    }


}
