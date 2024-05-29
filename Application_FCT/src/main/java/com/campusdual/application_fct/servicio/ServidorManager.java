package com.campusdual.application_fct.servicio;


import com.campusdual.application_fct.util.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorManager {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private static final List<Servidor> servidoresList = new ArrayList<>();
    private static int ultimoPuerto = 6000;
    private static Session session = HibernateUtil.getSessionfactory().openSession();

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionfactory().openSession();
        for(int i=0;i<5;i++){
            anhadirServidores((ultimoPuerto));
            ultimoPuerto++;
        }
        try {
            while (true) {
                System.out.println("Si necesitas añadir mas servidores puedes poner el numero de servidores que necesitas añadir\n" +
                        "o para cerrar todos los servidores 'cerrar' ");
                String numeroServidores = new Scanner(System.in).nextLine();
                if (Integer.parseInt(numeroServidores) > 0) {
                    for (int i = 0; i < Integer.parseInt(numeroServidores); i++) {
                        anhadirServidores((ultimoPuerto));
                        ultimoPuerto++;
                    }
                } else if (Integer.parseInt(numeroServidores) == 0) {
                    System.out.println("No se ha añadido ningun servidor\n" +
                            "Si quieres añadir un servidor nuevo tienes que poner 1 o mas");
                } else if (numeroServidores.equals("cerrar")) {
                    executorService.shutdown();
                    System.out.println("Servidores cerrados");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Puerto inválido. Inténtalo de nuevo.");
        }
    }
    public static void anhadirServidores(int puerto){
        Servidor servidorSecundario = new Servidor(puerto,session);
        servidoresList.add(servidorSecundario);
        executorService.submit(servidorSecundario);
        System.out.println("---------------SERVIDOR AÑADIDO EN EL PUERTO "+puerto+"---------------");
    }
}
