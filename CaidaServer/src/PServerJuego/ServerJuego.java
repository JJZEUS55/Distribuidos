package PServerJuego;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Alan
 */
public class ServerJuego {
    private int PUERTO; //Puerto para la conexión
    protected ServerSocket ss; 
    protected Socket sock;
    private boolean token;

    
    private DataInputStream entrada;
    private DataOutputStream salida;
    
    public ServerJuego(int puerto) 
    {
        this.PUERTO = puerto;   
        this.token = false;
    }
    
    public void iniciar() // inicializa el servidor y comienza el accept
    {      
        try 
        {
            ss = new ServerSocket(PUERTO);
            System.out.println("Esperando Conexion");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        System.out.println("Servidor en funcionamiento");            
    }
    
    public void acceptar() 
    {
        try 
        {
            sock = ss.accept();
            entrada = new DataInputStream(sock.getInputStream());
            salida = new DataOutputStream(sock.getOutputStream());
            System.out.println("Canales listos");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }      
    }
    
    
}
