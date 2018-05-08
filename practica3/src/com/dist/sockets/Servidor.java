package com.dist.sockets;

import com.dist.coordinador.Cartas;
import com.dist.coordinador.Mazo;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Conexion implements Runnable//Se hereda de conexión para hacer uso de los sockets y demás
{

    private byte[] mensaje;
    public static InetAddress[] ipPermitidos;
    private int conexiones;
    public static InetAddress ipRequerida;
    private Mazo car;
    private static int numCli = 0;
    private boolean siguiente = false;
    private int jugadorAIniciar = 0;

    public Servidor(Mazo c) throws IOException {
        super("servidor");
        mensaje = new byte[3];
        ipPermitidos = new InetAddress[3];
        conexiones = 0;
        car = c;
    }

    public Servidor() throws IOException {
        super("servidor");
        mensaje = new byte[3];
        ipPermitidos = new InetAddress[3];
        conexiones = 0;
    }

    public Servidor(int puerto) throws IOException {
        super("servidor", puerto);
        mensaje = new byte[3];
        ipPermitidos = new InetAddress[3];
        conexiones = 0;
    }

    public Mazo getMazo() {
        return car;
    }

    public void setMazo(Mazo car) {
        this.car = car;
    }

    public void startServer(Mazo car)//Método para iniciar el servidor
    {

        try {
            System.out.println("Esperando..."); //Esperando conexión

            cs = ss.accept();

            System.out.println("Se ha conectado el cliente");
            ObjectOutputStream ob = new ObjectOutputStream(cs.getOutputStream());
            DataOutputStream dos = new DataOutputStream(cs.getOutputStream());

            ob.writeObject(car);

            dos.writeBoolean(true);
            System.out.println("Se escribio el objeto");

            numCli++;
            //Necesario cerrar los 2 si no erro JVM address already in use jvm_bind 
            dis = new DataInputStream(cs.getInputStream());
            this.jugadorAIniciar = dis.readInt();
            System.out.println("SERVIDOR: Puede iniciar el jugador " + jugadorAIniciar);

            

        } catch (IOException e) {
            System.out.println("Problema en: " + e.getMessage());

        }
    }

    public void startServerActivaCliente(int numCliente) {
        DataOutputStream dos;
        try {
            cs = ss.accept();
            dos = new DataOutputStream(cs.getOutputStream());

            dos.writeBoolean(true);

            System.out.println("Se ha mandado un " + numCliente);
            dos.writeInt(numCliente);
            System.out.println("Se ha mandado activacion del cliente");

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mandarSiguienteJugador(int numj) {
        DataOutputStream dos;
            try {

                cs = ss.accept();
                dos = new DataOutputStream(cs.getOutputStream());

                dos.writeInt(numj);

                // System.out.println("-------------------------------------------");
                System.out.println("!!!!!!! MANDAR SIGUIENTE !!!!!!!!!");
                System.out.println("Se ha mandado un " + numj);
                System.out.println("El jugador " + numj + " puede pedir");

                //dis = new DataInputStream(cs.getInputStream());
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");           
                //System.out.println("-------------------------------------------");
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        

    }

    public int getNumCli() {
        return this.numCli;
    }

    public int getJugadorAIniciar() {
        return this.jugadorAIniciar;
    }

    @Override
    public void run() {
//        startServer();
    }

}
