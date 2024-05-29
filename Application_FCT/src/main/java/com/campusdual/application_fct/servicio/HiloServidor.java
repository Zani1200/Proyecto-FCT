package com.campusdual.application_fct.servicio;

import com.campusdual.application_fct.consultas.ServidorConsultas;
import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Participantes;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class HiloServidor extends Thread{
    private Socket cliente;
    //private BufferedReader in;
    private DataInputStream dataInputStream;
    private Session session;
    private Usuario usuario;
    private static Set<Socket> clientesSockets = new CopyOnWriteArraySet<>();

    public HiloServidor(Socket cliente, Session session) throws IOException {
        this.cliente = cliente;
        this.session = session;
        clientesSockets.add(cliente);
        //in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        dataInputStream = new DataInputStream(cliente.getInputStream());
    }

    @Override
    public void run() {
        try {
            do {
                System.out.println(cliente.toString());
                String[] datosUsuario = dataInputStream.readUTF().split(",");
                int id = Integer.parseInt(datosUsuario[0]);
                int usuConectado = Integer.parseInt((datosUsuario[4]));
                usuario = new Usuario(id, datosUsuario[1], datosUsuario[2], datosUsuario[3], usuConectado);

                String mensaje = dataInputStream.readUTF();
                ServidorConsultas registroUsuarioConsultas = new ServidorConsultas();
                Integer participanteId = registroUsuarioConsultas.getParticipante(usuario,new Chat(Integer.parseInt(datosUsuario[5]),datosUsuario[6],datosUsuario[7]));
                Participantes participante = new Participantes(participanteId);
                HibernateUtil.agregarMensaje(new Mensaje(participante, mensaje));

                String datosMensaje = usuario.getUsu_nombre() + "," + mensaje + "," + usuario.getUsu_foto();
                trasmisionMensaje(datosMensaje,datosUsuario[8]);

            } while (true);
        } catch (IOException e) {
            System.out.println("Error de conexion");
            clientesSockets.remove(cliente);
            System.out.println("El cliente se elimino");
        }
    }

    public void trasmisionMensaje(String mensaje, String port) {
        for (Socket cliente : clientesSockets) {
            try {
                int localPortCliente = cliente.getLocalPort();
                System.out.println(localPortCliente +","+port);
                if(localPortCliente == Integer.parseInt(port)) {
                    System.out.println("entro "+localPortCliente);
                    DataOutputStream dataOutputStream = new DataOutputStream(cliente.getOutputStream());
                    dataOutputStream.writeUTF(mensaje);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
