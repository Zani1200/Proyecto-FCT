package com.campusdual.application_fct.servicio;

import org.hibernate.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable{
    Session session;
    int puerto;
    public Servidor(int puerto, Session session) {
        this.session = session;
        this.puerto = puerto;
    }

    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("-------------SERVIDOR INICIADO-------------");
            do {
                Socket usuarioSocket = new Socket();
                usuarioSocket = servidor.accept();
                HiloServidor hiloServidor = new HiloServidor(usuarioSocket, session);
                hiloServidor.start();
            } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
