
package token;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Alan
 */
public class tokenCliente 
{
    private Socket sock;
    private static String HOST;
    private static int PUERTO;
    private String HoraServidor;
    private String IP;
    private int jugador;
    private DataOutputStream salida; // enviar mensajes
    private DataInputStream Entrada; //recibir mensajes
    
    public tokenCliente(String host, int puerto) 
    {
        try {
            sock = new Socket(host, puerto);
            Entrada = new DataInputStream(sock.getInputStream());
            salida = new DataOutputStream(sock.getOutputStream());          
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        System.out.println("Se ha conectado el cliente");
    }
    
    public void enviarToken()
    {
        System.out.println("EnviandoToken");
        enviarMSJ("Token");
    }
    
    private void enviarMSJ(String buffer)
    {
        try {
            salida.writeUTF(buffer);
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
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
            System.out.println("Error de entrada/salida.");
        }
        return buffer;
    }
}
