package com.campusdual.application_fct.servicio;

import com.campusdual.application_fct.entities.Usuario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HiloServidor extends Thread{
    private Socket socketCliente;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private static List<Usuario> usuarioList = new ArrayList<>();
    private Usuario usuario;

    public HiloServidor(Socket socketCliente) throws IOException {
        this.socketCliente = socketCliente;
        dataInputStream = new DataInputStream(socketCliente.getInputStream());
        dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());
    }

    @Override
    public void run() {
        try {
            System.out.println(socketCliente.toString());
            String[] datosUsuario = dataInputStream.readUTF().split(",");
            int id = Integer.parseInt(datosUsuario[0]);
            int usuConectado = Integer.parseInt((datosUsuario[3]));
            usuario = new Usuario(id,datosUsuario[1],datosUsuario[2],usuConectado);
            usuarioList.add(usuario);
                do {
                    String mensaje = dataInputStream.readUTF();
                    System.out.println(usuario.getUsu_nombre()+"\n" +
                            mensaje);
                } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
