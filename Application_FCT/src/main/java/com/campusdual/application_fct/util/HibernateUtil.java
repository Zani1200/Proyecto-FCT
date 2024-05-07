package com.campusdual.application_fct.util;

import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static StandardServiceRegistry registro;
    private static SessionFactory sessionfactory;

    public static SessionFactory getSessionfactory() {
        if (sessionfactory == null) {
            try {
                // Crea un registro
                registro = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml").build();
                // Crea un MetadataSources
                MetadataSources sources = new MetadataSources(registro);
                // Crea Metadata
                Metadata metadata = sources.getMetadataBuilder().build();
                // Create SessionFactory
                sessionfactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                if (registro != null)
                    StandardServiceRegistryBuilder.destroy(registro);
            }
        }
        return sessionfactory;
    }

    public static void agregarUsuarios(Usuario usuario){
        Session session = HibernateUtil.getSessionfactory().openSession();
        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();
        session.close();
    }

    public static void agregarMensaje(Mensaje mensaje){
        Session session = HibernateUtil.getSessionfactory().openSession();
        session.beginTransaction();
        session.save(mensaje);
        session.getTransaction().commit();
        session.close();
    }
}
