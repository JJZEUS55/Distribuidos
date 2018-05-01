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

    public Servidor(Mazo c) throws IOException {
        super("servidor");
        mensaje = new byte[3];
        ipPermitidos = new InetAddress[3];
        conexiones = 0;
        car = c;
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
            ob.writeObject(car);
            System.out.println("Se escribio el objeto");
            
            numCli++;

            //NO SE PORQUE PERO SI LO LEO EN EL SERVIDOR SI SIRVE DESPUES EN EL CLIENTE NO SE A QUE SE DEBA
            //NO BORRAR
            ObjectInputStream ob1 = new ObjectInputStream(cs.getInputStream());
            Mazo a = (Mazo) ob1.readObject();
            System.out.println(a);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Problema en: " + e.getMessage());

        }
    }
    
    public int getNumCli(){
        return this.numCli;
    }

    @Override
    public void run() {
        startServer();
    }

}
