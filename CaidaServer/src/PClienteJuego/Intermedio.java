
package PClienteJuego;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Intermedio {
    private InfoServidor Servidores[];
    private DataOutputStream Salida; // enviar mensajes
    private DataInputStream Entrada; 
    private Socket sock;

    public Intermedio() {
        this.Servidores = new InfoServidor[4];
        Servidores[0].setIP("");
        Servidores[0].setPuerto(0);
        Servidores[1].setIP("");
        Servidores[1].setPuerto(1);
        Servidores[2].setIP("");
        Servidores[2].setPuerto(2);
        Servidores[3].setIP("");
        Servidores[3].setPuerto(3);
    }
    
    public void Conexion()
    {
        
        int numero = (int) (Math.random() * 4) + 1;
        while(true)
        {
            try {
                sock = new Socket(Servidores[numero].getIP(), Servidores[numero].getPuerto());
                Entrada = new DataInputStream(sock.getInputStream());
                Salida = new DataOutputStream(sock.getOutputStream());
                break;
            } catch (UnknownHostException e) {
                System.out.println("El host no existe o no está activo.");
                //guardar en la base de datos esa informacion
            } catch (IOException e) {
                System.out.println("Error de entrada/salida.");
            }
        }
        System.out.println("Conexion: Se ha establecido el canal");    
    }
    
    //set Entrada, Salida y sock del cliente principal
    public void finalizarConexion()
    {
        
    }
}


