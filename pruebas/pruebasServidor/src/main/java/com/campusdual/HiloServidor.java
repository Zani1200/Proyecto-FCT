package com.campusdual;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class HiloServidor extends Thread{
    private Socket socketCliente;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String mensaje = null;
    private static int numeroCliente = 0;

    public HiloServidor(Socket socketCliente) throws IOException {
        this.socketCliente = socketCliente;
        dataInputStream = new DataInputStream(socketCliente.getInputStream());
        dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());
        numeroCliente += 1;
    }

    @Override
    public void run() {
        do {
            try {
                System.out.println(socketCliente.toString());
                String mensaje = dataInputStream.readUTF();
                System.out.println(mensaje);
                dataOutputStream.writeUTF(mensaje);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }

}
