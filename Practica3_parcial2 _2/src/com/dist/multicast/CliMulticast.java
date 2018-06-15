/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.multicast;

import com.dist.juego.Carta;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 *
 * @author geoge
 */
public class CliMulticast {

    public static boolean aparecePokemon = true;
    public static Carta cartaSalvaje;

    public CliMulticast() {
    }

    public void iniciarClienteMulticast() throws UnknownHostException, IOException {
        String multiCastAddress = "224.0.0.1";
        final int multiCastPort = 52684;
        final int bufferSize = 1024 * 4; //Maximum size of transfer object

        //Create Socket
        System.out.println("Creando socket" + multiCastAddress + " y puerto " + multiCastPort + ".");
        InetAddress group = InetAddress.getByName(multiCastAddress);
        MulticastSocket s = new MulticastSocket(multiCastPort);
        s.joinGroup(group);

        System.out.println("Esperando a recibir Pokemon Salvaje...");

        //Create buffer
        byte[] buffer = new byte[bufferSize];
        s.receive(new DatagramPacket(buffer, bufferSize, group, multiCastPort));
        System.out.println("Pokemon recibido!");
        
        //Deserialze object
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        ObjectInputStream ois = new ObjectInputStream(bais);
        try {
            Object readObject = ois.readObject();
            if (readObject instanceof Carta) {
                cartaSalvaje = (Carta) readObject;
                System.out.println("Carta es: " + cartaSalvaje);
                cartaSalvaje.setHP(cartaSalvaje.getHp()*6);
                this.aparecePokemon = true;  
            } else {
                System.out.println("No se ha recibido una carta");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
