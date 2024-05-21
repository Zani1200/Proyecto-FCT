package com.campusdual;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket cliente = new Socket("localhost", 6001);

        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

        do{
            System.out.println("Escribe el puerto");
            int port = new Scanner(System.in).nextInt();
            flujoSalida.writeInt(port);
            System.out.println(flujoEntrada.readUTF());
            Socket cliente1 = new Socket("localhost", port);
            System.out.println("Se ha creado el cliente nuevo");
            String mensaje = null;
            while (!Objects.equals(mensaje, "")) {
                mensaje = new Scanner(System.in).nextLine();
                flujoSalida.writeUTF(mensaje);
                System.out.println(flujoEntrada.readUTF());
            }
        }while (true);

    }
}
