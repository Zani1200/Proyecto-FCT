package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class LoginConsultas implements ConsultasGeneral {
    @Override
    public Usuario validarUsuario(String nombre, String contrasenha) {
        Usuario usuario = null;
        try {
            Session session = HibernateUtil.getSessionfactory().openSession();
            usuario = session.createQuery("SELECT j FROM Usuario j WHERE j.usu_nombre = :nombre AND j.usu_contrasenha = :contrasenha", Usuario.class)
                    .setParameter("nombre", nombre).setParameter("contrasenha", contrasenha).getSingleResult();
            System.out.println(usuario);

            Integer usuarioActivo = (Integer) session.createQuery("SELECT j.usu_activo " +
                    "FROM Usuario j " +
                    "WHERE usu_id = :id").setParameter("id", usuario.getUsu_id()).getSingleResult();
            System.out.println(usuarioActivo);

            if(usuarioActivo == 0){
                session.beginTransaction();
                Query actualizarActividad = session.createQuery("UPDATE Usuario u " +
                        "SET u.usu_activo = 1 " +
                        "WHERE u.usu_id = :id").setParameter("id", usuario.getUsu_id());
                int i = actualizarActividad.executeUpdate();
                System.out.println("Se ha realizado "+i+" cambio/s");
                session.getTransaction().commit();
                session.close();
                System.out.println(usuario);
                return usuario;
            } else {
                return ;
            }
        } catch (NoResultException e) {
            System.out.println("MALLLLLLLLLLLLLLLLLL");
            return null;
        }
    }

    @Override
    public List<Mensaje> getMensajesGrupo() {
        return null;
    }


}


