package com.dist.sockets;

import com.dist.coordinador.Cartas;
import com.dist.coordinador.Mazo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends Conexion implements Runnable {

    //IDEA HACER 3 CLIENTES DISTINTOS
    //HACER UN CONTADOR EN CLIENTES
    //ACIERTO ESA COSA QUE DICE ACTIVAR SI PUEDE FUNCIONAR
    private Scanner entrada;
    private String mensaje;
    private boolean activar = false;
    private Mazo m;
    private int clienteNumero;
    private boolean siguiente = false;

    public Cliente() throws IOException {
        super("cliente");
        System.out.println("Cliente s/n");
    } //Se usa el constructor para cliente de Conexion

    public Cliente(int puerto) throws IOException {
        super("cliente", puerto);
        System.out.println("Cliente: "+puerto);
    } //Se usa el constructor para cliente de Conexion

    public Mazo startClient(int cliNum) //Método para iniciar el cliente
    {
        try (ObjectInputStream ob = new ObjectInputStream(cs.getInputStream())) {

            System.out.println("Cliente Iniciado");
            m = null;
            dis = new DataInputStream(cs.getInputStream());

            m = (Mazo) ob.readObject();
            System.out.println(m.getCincoCartas().get(0));
            System.out.println(m.getCincoCartas().get(1));
            System.out.println(m.getCincoCartas().get(2));
            System.out.println(m.getCincoCartas().get(3));
            System.out.println(m.getCincoCartas().get(4));
            System.out.println("Cerrando conexion...");

            activar = dis.readBoolean();

            System.out.println("Activar = " + activar);
            salidaCliente = new DataOutputStream(cs.getOutputStream());
            salidaCliente.writeInt(cliNum + 1);
            System.out.println("Enviando un " + (cliNum + 1));

            //cs.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Problema " + e.getMessage());
        }

        return m;
    }

    public void startClientActivar() //Método para iniciar el cliente
    {
        try {

            dis = new DataInputStream(cs.getInputStream());
            activar = dis.readBoolean();
            System.out.println("Activar " + activar);
            System.out.println("Cliente Esperando Activarse");

            System.out.println("Cerrando conexion...");
            clienteNumero = dis.readInt();
            System.out.println("Se ha recibido un " + clienteNumero);
            System.out.println("Activar = " + activar);

        } catch (IOException e) {
            System.out.println("Problema " + e.getMessage());
        }

    }

    public boolean esperarJugadorAnterior(int numJugador) {
        System.out.println("Esperando jugadores");
        System.out.println("soy el numero de jugador " + numJugador);
        boolean act = false;
        try {
            int aux = 0;

            dis = new DataInputStream(cs.getInputStream());
               aux = dis.readInt();

            System.out.println("Se recibio el dato: " + aux);
            if (aux == numJugador) {
                System.out.println("Porfin puedo iniciar soy el cliente numero " + numJugador);
                act = true;
            } else {
                act = false;
            }
            
//            cs.close();
            //cs.close();

            return act;

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return act;

    }

    @Override
    public void run() {
//        startClient();
    }

    public boolean getActivar() {
        return this.activar;
    }

    public Mazo getMazoCliente() {
        return this.m;
    }

    public int getClienteNumero() {
        return this.clienteNumero;
    }
}
