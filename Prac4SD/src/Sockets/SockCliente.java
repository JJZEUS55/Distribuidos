package Sockets;

import Vistas.VistaCliente;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SockCliente {

    private Socket sock;
    private static String HOST = "localhost";
    private static int PUERTO = 3060;
    private String HoraServidor;
    DataOutputStream mensaje;
    BufferedReader Mensaje_Servidor;
    DataInputStream ms;
    String [] horaMod;

    public SockCliente() {
        try {
            sock = new Socket(HOST, PUERTO);
            Mensaje_Servidor = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            mensaje = new DataOutputStream(sock.getOutputStream());
            horaMod = new String[3];

        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }

    public SockCliente(String host, int puerto) {
        try {
            sock = new Socket(host, puerto);
            ms = new DataInputStream(sock.getInputStream());
            mensaje = new DataOutputStream(sock.getOutputStream());
            horaMod = new String[3];

        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }

    public void iniciar() {
        try {
            System.out.println("En com");
            mensaje.writeUTF("Cliente conectado");
            System.out.println("Esperando...");
            horaMod = ms.readUTF().split(":");
            VistaCliente.rel1.modificarHora(Integer.parseInt(horaMod[0]), Integer.parseInt(horaMod[1]), Integer.parseInt(horaMod[2]));
            sock.close();
            System.out.println("fin");
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }

    }
}
