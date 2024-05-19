package com.campusdual.application_fct.servicio;

import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HiloServidor extends Thread{
    private Socket socketCliente;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Usuario usuario;
    private List<Mensaje> mensajeList = new ArrayList<>();

    public HiloServidor(Socket socketCliente) throws IOException {
        this.socketCliente = socketCliente;
        dataInputStream = new DataInputStream(socketCliente.getInputStream());
        dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());
    }

    @Override
    public void run() {
        try {
            do {
                System.out.println(socketCliente.toString());
                String[] datosUsuario = dataInputStream.readUTF().split(",");
                int id = Integer.parseInt(datosUsuario[0]);
                int usuConectado = Integer.parseInt((datosUsuario[4]));
                usuario = new Usuario(id, datosUsuario[1], datosUsuario[2], datosUsuario[3], usuConectado);

                String mensaje = dataInputStream.readUTF();
                //HibernateUtil.agregarMensaje(new Mensaje(usuario, mensaje));

                dataOutputStream.writeUTF(usuario.getUsu_nombre() + "\n" +
                        mensaje);

            } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "HiloServidor{" +
                "mensajeList=" + mensajeList +
                '}';
    }
}
