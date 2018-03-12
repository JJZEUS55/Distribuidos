/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reloj.conexion;

/**
 * @author Antonio Bellido
 */
import com.reloj.logica.Reloj;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//Para probarlo, lanzar el MCServer y despues crear tantas 
//instancias de este como se quiera, espera a que haga el 
//join al grupo tarda unos segundos, y comienza a escribir 
//en la instancia del servidor (lo escrito saldrá en todos 
//estos clientes creados).
public class MulticastRecv {

    private byte[] buffer;

    public MulticastRecv() {
        buffer = new byte[256];
    }

    public void multicasRecibir() {
        MulticastSocket s;
        try {
            
            s = new MulticastSocket(10001);
            InetAddress group = InetAddress.getByName("232.0.0.1");

            s.joinGroup(group);
            System.out.println("Cliente en escucha....");


            DatagramPacket dgp = new DatagramPacket(buffer, buffer.length);

            s.receive(dgp);


            buffer = dgp.getData();

            System.out.println("horas: " + buffer[0]
                    + "\nMinutos: " + buffer[1]
                    + "\nSegundos: " + buffer[2]);

            s.leaveGroup(group);
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MulticastRecv.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Reloj getHora() {
        Reloj r = new Reloj(buffer[0], buffer[1], buffer[2]);
        return r;
    }

    public String imprimirHora() {
        return "horas: " + buffer[0]
                + "\nMinutos: " + buffer[1]
                + "\nSegundos: " + buffer[2];
    }

}
