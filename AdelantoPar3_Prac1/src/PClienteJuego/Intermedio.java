
package PClienteJuego;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Intermedio {
    private InfoServidor Servidores;
    private DataOutputStream Salida; // enviar mensajes
    private DataInputStream Entrada; 
    private Socket sock;

    public Intermedio() 
    {
        Servidores = new InfoServidor();
    }
    
    public void Conexion()
    {
        System.out.println("Tratando Conexion");
        int numero;
        
        while(true)
        {
            numero = (int) (Math.random() * 4);
            System.out.println(numero);
            System.out.println(Servidores.getIP(numero));
            System.out.println(Servidores.getPuerto(numero));
            try {
                sock = new Socket(Servidores.getIP(numero), Servidores.getPuerto(numero));
                Entrada = new DataInputStream(sock.getInputStream());
                Salida = new DataOutputStream(sock.getOutputStream());
                vistaClienteJuego.Cliente_Principal.setSock(sock);
                vistaClienteJuego.Cliente_Principal.setEntrada(Entrada);
                vistaClienteJuego.Cliente_Principal.setSalida(Salida);
                System.out.println("Conexion: Se ha establecido el canal"); 
                break;
            } catch (UnknownHostException e) {
                System.out.println("El host no existe o no está activo.");
                continue;
            } catch (IOException e) {
                System.out.println("Error de entrada/salida.");
                continue;
            }
        }
           
    }
    
    //set Entrada, Salida y sock del cliente principal
    public void finalizarConexion()
    {
        
    }
}


