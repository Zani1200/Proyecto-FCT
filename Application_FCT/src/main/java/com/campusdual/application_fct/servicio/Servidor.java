package com.campusdual.application_fct.servicio;


import com.campusdual.application_fct.controller.LoginController;
import com.campusdual.application_fct.controller.MenuController;
import com.campusdual.application_fct.entities.Usuario;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Servidor {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ServerSocket servidor = new ServerSocket(6000);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        System.out.println("-------------SERVIDOR INICIADO-------------");
        do{
            Socket usuarioSocket = new Socket();
            usuarioSocket = servidor.accept();
            HiloServidor hiloServidor = new HiloServidor(usuarioSocket);
            hiloServidor.start();
        } while (true);
    }
}
