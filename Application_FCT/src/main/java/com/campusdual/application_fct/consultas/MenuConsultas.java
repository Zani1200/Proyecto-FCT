package com.campusdual.application_fct.consultas;

import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuConsultas implements ConsultasGeneral{

    @Override
    public Usuario validarUsuario(String nombre, String contrasenha) {
        return null;
    }
    @Override
    public List<Mensaje> getMensajesGrupo() {
        Session session = HibernateUtil.getSessionfactory().openSession();
        List<Object[]> mensajesQuery = session.createQuery("SELECT j.mensaje, m.usu_id, m.usu_nombre, m.usu_foto FROM Mensaje j " +
                "INNER JOIN Usuario m " +
                "ON j.id_usu = m.usu_id " +
                "ORDER BY j.msj_id",Object[].class).getResultList();
        List<Mensaje> mensajeArrayList = new ArrayList<>();
        for(Object[] columna : mensajesQuery){
            Mensaje mensaje = new Mensaje(new Usuario((Integer) columna[1],(String) columna[2], (String) columna[3]),(String) columna[0]);
            mensajeArrayList.add(mensaje);
        }
        return mensajeArrayList;
    }
}
