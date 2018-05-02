package com.dist.sockets;

import com.dist.coordinador.Cartas;
import com.dist.coordinador.InfoPC;
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
    InfoPC Equipos[] = new InfoPC[5];
    DataOutputStream salida; // enviar mensajes
    DataInputStream Entrada; //recibir mensajes
    
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

    public void startServer()//Método para iniciar el servidor
    {

        try {
            System.out.println("Esperando..."); //Esperando conexión

            cs = ss.accept();

            ObjectOutputStream ob = new ObjectOutputStream(cs.getOutputStream());
            DataOutputStream dos = new DataOutputStream(cs.getOutputStream());

            ob.writeObject(car);

            dos.writeBoolean(true);
            System.out.println("Se escribio el objeto");

            numCli++;
            //Necesario cerrar los 2 si no erro JVM address already in use jvm_bind 
            dis = new DataInputStream(cs.getInputStream());
            System.out.println(dis.readUTF());
            System.out.println("Cerrando conexion");

            //ss.close();
        } catch (IOException e) {
            System.out.println("Problema en: " + e.getMessage());

        }
    }

    public void startServerActivaCliente(int numCliente) {
        DataOutputStream dos;
        try {
            cs = ss.accept();
            dos = new DataOutputStream(cs.getOutputStream());
//            if(numCliente == 1){
            dos.writeBoolean(true);
//            }else{
//                dos.writeBoolean(false);
//            }            
            dos.writeInt(numCliente);
            System.out.println("Se ha mandado activacion del cliente");

//            cs.close();
            //ss.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void servidorChidoInciar()
    {
        System.out.println("Esperando...ServidorChido"); //Esperando conexión
        try 
        {
            cs = ss.accept();
            Entrada = new DataInputStream(cs.getInputStream());
            salida = new DataOutputStream(cs.getOutputStream());
            System.out.println(Entrada.readUTF());
            
        } catch (IOException e) {
            System.out.println("Problema en: " + e.getMessage());
            

        }
    }
    
    public int getNumCli() {
        return this.numCli;
    }

    @Override
    public void run() {
        startServer();
    }

}
