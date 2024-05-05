package com.campusdual.application_fct.servicio;

import com.campusdual.application_fct.entities.Usuario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloServidor extends Thread{
    private Socket socketCliente;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Usuario usuario;

    public HiloServidor(Socket socketCliente) throws IOException {
        this.socketCliente = socketCliente;
        dataInputStream = new DataInputStream(socketCliente.getInputStream());
        dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());
    }

    @Override
    public void run() {
        System.out.println(socketCliente.getPort()+" Port");
        System.out.println(socketCliente.getLocalPort()+" Local Port");
        System.out.println(socketCliente.getInetAddress()+" InetAddress");
        System.out.println(socketCliente.getLocalAddress() + "LocalAddress");
        System.out.println(socketCliente.toString());
        do {
            try {
                String mensaje = dataInputStream.readUTF();
                System.out.println(mensaje);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }
}
