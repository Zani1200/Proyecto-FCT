package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class LoginConsultas implements ConsultasGeneral{
    @Override
    public Usuario validarUsuario(String nombre, String contrasenha) {
        Session session = HibernateUtil.getSessionfactory().openSession();
        Usuario usuario = session.createQuery("SELECT j FROM Usuario j WHERE j.usu_nombre = :nombre AND j.usu_contrasenha = :contrasenha", Usuario.class)
                .setParameter("nombre", nombre).setParameter("contrasenha", contrasenha).getSingleResult();
        System.out.println(usuario);
        return null;
    }
}
