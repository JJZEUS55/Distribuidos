
package clienteC;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class clase_cliente 
{
    private Socket sock;
    private static String HOST = "localhost";
    private static int PUERTO = 3080;
    private String IP;
    private int jugador;
    private int Intervalo = 500;
    private DataOutputStream salida; // enviar mensajes
    private DataInputStream Entrada; //recibir mensajes
    private boolean servidorOK = true;
    
    public clase_cliente(String host, int puerto) {
        try {
            sock = new Socket(host, puerto);
            Entrada = new DataInputStream(sock.getInputStream());
            salida = new DataOutputStream(sock.getOutputStream());          
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        System.out.println("Se inicio cliente check");
        enviarMSJ("hola");
    }
  
    public boolean check()
    {
        while(true)
        {
            System.out.println(recibirMSJ());
            if (servidorOK == false) 
                break;          
        }
        return servidorOK;
    }
    

    
    private void enviarMSJ(String buffer)
    {
        try {
            salida.writeUTF(buffer);
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida. ENV");
            servidorOK = false;
        }
    }
    
    private String recibirMSJ() 
    {
        String buffer = "";
        try {
            buffer = Entrada.readUTF();
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida. REC");
            servidorOK = false;
            try {sock.close();}
            catch(IOException e1){}
        }
        return buffer;
    }
    
    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }
    
    public int getIntervalo() {
        return Intervalo;
    }

    public void setIntervalo(int Intervalo) {
        this.Intervalo = Intervalo;
    }
}
