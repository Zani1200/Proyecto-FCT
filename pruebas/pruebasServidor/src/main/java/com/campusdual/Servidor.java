package com.campusdual;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(6001);
        System.out.println("-------------SERVIDOR INICIADO-------------");
        do{
            Socket usuarioSocket = new Socket();
            usuarioSocket = servidor.accept();
            HiloServidor hiloServidor = new HiloServidor(usuarioSocket);
            hiloServidor.start();
        } while (true);
    }
}
