package com.campusdual.application_fct.util;

import com.campusdual.application_fct.controller.RegistroUsuarioController;
import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Participantes;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.excepciones.ExisteUsuario;
import com.campusdual.application_fct.excepciones.NoExisteUsuario;
import com.campusdual.application_fct.excepciones.UsuarioNoValido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.NoResultException;

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

    public static void agregarUsuarios(Usuario usuario) throws ExisteUsuario, UsuarioNoValido{
        Session session =  HibernateUtil.getSessionfactory().openSession();
        try {
            if(usuario.getUsu_nombre().equals("") || usuario.getUsu_contrasenha().equals("")){
                throw new UsuarioNoValido("El usuario o contraseña no son validos");
            } else {
                session.createQuery("SELECT j FROM Usuario j WHERE j.usu_nombre = :nombre AND j.usu_contrasenha = :contrasenha", Usuario.class)
                        .setParameter("nombre", usuario.getUsu_nombre()).setParameter("contrasenha", usuario.getUsu_contrasenha()).getSingleResult();
                throw new ExisteUsuario("El usuario o contraseña ya existe");
            }
        } catch (NoResultException e){
            session.beginTransaction();
            session.save(usuario);
            session.getTransaction().commit();
            session.close();
        }

    }

    public static Chat agregarChat(Chat chat) {
        Session session =  HibernateUtil.getSessionfactory().openSession();
        session.beginTransaction();
        Chat nuevoChat = new Chat(chat.getChat_id(),chat.getChat_nombre(), chat.getChat_foto());
        session.save(nuevoChat);
        session.getTransaction().commit();
        session.close();
        return nuevoChat;
    }

    public static void agregarParticipantes(Participantes participante){
        Session session =  HibernateUtil.getSessionfactory().openSession();
        session.beginTransaction();
        session.save(participante);
        session.getTransaction().commit();
        session.close();
    }

    public static void agregarMensaje(Mensaje mensaje){
        Session session =  HibernateUtil.getSessionfactory().openSession();
        session.beginTransaction();
        session.save(mensaje);
        session.getTransaction().commit();
        session.close();
    }

    public static Chat buscarChat(Integer chatId){
        Session session =  HibernateUtil.getSessionfactory().openSession();
        session.beginTransaction();
        Chat nuevoChat = session.get(Chat.class,chatId);
        session.getTransaction().commit();
        session.close();
        return nuevoChat;
    }
}
