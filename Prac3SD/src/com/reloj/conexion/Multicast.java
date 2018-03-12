package com.reloj.conexion;

// MCServer.java
import com.reloj.logica.Reloj;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Multicast {

    private byte[] horas;

    public Multicast() {
        horas = new byte[3];
    }

    public void iniciarMulticastServidor(Reloj r) {
        System.out.println("Arrancando el servidor multicast...\n");

        MulticastSocket s;
        try {
            s = new MulticastSocket();
            InetAddress group = InetAddress.getByName("232.0.0.1");


            horas[0] = (byte) r.getHoras();
            horas[1] = (byte) r.getMinutos();
            horas[2] = (byte) r.getSegundos();
//            System.out.println("Horas" + horas[0] 
//                    + "\nMinutos" + horas[1] 
//                    + "\nSegundos" + horas[2]);
            DatagramPacket dgp = new DatagramPacket(horas, horas.length, group,
                    10001);

            s.send(dgp);
            System.out.println("" + dgp.getAddress() + dgp.getSocketAddress() + dgp.getPort() + s.getInterface());

            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Multicast.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
}
