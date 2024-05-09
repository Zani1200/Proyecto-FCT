package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;

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
        } catch (NoResultException e) {
            System.out.println("MALLLLLLLLLLLLLLLLLL");
        }
        System.out.println(usuario);
        return usuario;
    }

    @Override
    public List<Mensaje> getMensajesGrupo() {
        return null;
    }


}


