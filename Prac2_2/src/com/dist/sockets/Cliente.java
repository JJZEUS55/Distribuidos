package com.dist.sockets;

import com.dist.coordinador.Cartas;
import com.dist.coordinador.Mazo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Cliente extends Conexion implements Runnable{

    private Scanner entrada;
    private String mensaje;

    public Cliente() throws IOException {
        super("cliente");
    } //Se usa el constructor para cliente de Conexion

    public Mazo startClient() //Método para iniciar el cliente
    {
        Mazo c = null;
        try(ObjectInputStream ob = new ObjectInputStream(cs.getInputStream())) {
        
            c = (Mazo) ob.readObject();
            System.out.println(c.getCincoCartas().get(0));
            System.out.println(c.getCincoCartas().get(1));
            System.out.println(c.getCincoCartas().get(2));
            System.out.println(c.getCincoCartas().get(3));
            System.out.println(c.getCincoCartas().get(4));
            cs.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Problema " + e.getMessage());
        }
        
        return c;
    }

    @Override
    public void run() {
        startClient();
    }
}
