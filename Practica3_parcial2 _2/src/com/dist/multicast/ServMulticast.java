/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.multicast;

import com.dist.juego.Carta;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 *
 * @author geoge
 */
public class ServMulticast {

    public void iniciarServidorMulticast() throws UnknownHostException, IOException {
        Carta c1 = new Carta();
        c1.getCartaAleatoria();
        String multiCastAddress = "224.0.0.1";
        final int multiCastPort = 52684;

        //Create Socket
        System.out.println("Creando Socket " + multiCastAddress + " y puero " + multiCastPort + ".");
        InetAddress group = InetAddress.getByName(multiCastAddress);
        MulticastSocket s = new MulticastSocket(multiCastPort);
        s.joinGroup(group);

        //Prepare Data       
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(c1);
        System.out.println("Carta enviada");
        System.out.println(c1);
        byte[] data = baos.toByteArray();

        //Send data
        s.send(new DatagramPacket(data, data.length, group, multiCastPort));
    }

}
