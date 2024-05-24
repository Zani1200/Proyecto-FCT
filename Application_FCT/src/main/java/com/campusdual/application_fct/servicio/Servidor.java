package com.campusdual.application_fct.servicio;


import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Servidor {

    public static void main(String[] args) throws IOException {
        Session session = HibernateUtil.getSessionfactory().openSession();
        ServerSocket servidor = new ServerSocket(6000);
        System.out.println("-------------SERVIDOR INICIADO-------------");
        do{
            Socket usuarioSocket = new Socket();
            usuarioSocket = servidor.accept();
            HiloServidor hiloServidor = new HiloServidor(usuarioSocket,session);
            hiloServidor.start();
        } while (true);
    }
}
