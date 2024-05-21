package com.campusdual;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class HiloServidor extends Thread{
    private ServerSocket servidorPrinciapl;
    private Socket socketCliente;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String mensaje = null;
    private static int numeroCliente = 0;

    public HiloServidor(Socket socketCliente, ServerSocket servidorPrincipal) throws IOException {
        this.socketCliente = socketCliente;
        this.servidorPrinciapl = servidorPrincipal;
        dataInputStream = new DataInputStream(socketCliente.getInputStream());
        dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());
        numeroCliente += 1;
    }

    @Override
    public void run() {
        do {
            try {
                System.out.println("El cliente "+numeroCliente+" "+socketCliente.toString()+" ha entrado al servidor principal");
                int port = dataInputStream.readInt();
                ServerSocket serverSocket = new ServerSocket(port);
                dataOutputStream.writeUTF("Servidor secundario creado");
                socketCliente = serverSocket.accept();
                System.out.println("El cliente "+numeroCliente+" "+ socketCliente.toString() +" ha entrado al servidor secundario");
                while (!Objects.equals(mensaje, "")) {
                    mensaje = dataInputStream.readUTF();
                    System.out.println(mensaje);
                    dataOutputStream.writeUTF(mensaje);
                }
                mensaje = null;
                serverSocket.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }

}
