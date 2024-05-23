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
            flujoSalida.writeUTF(new Scanner(System.in).nextLine());
            String mensanje = flujoEntrada.readUTF();
            System.out.println(mensanje);
        }while (true);

    }
}
