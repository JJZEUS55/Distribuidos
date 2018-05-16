
package token;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Alan
 */
public class tokenServer 
{
    private int PUERTO; //Puerto para la conexión
    protected ServerSocket ss; 
    protected Socket sock;
    private boolean token;

    
    private DataInputStream entrada;
    private DataOutputStream salida;
    
    public tokenServer(int puerto) 
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
           
   public void EsperarToken()
   {
       String buffer;
       System.out.println("Esperando Token...");
       buffer = recibirMSJ();
       if(buffer.equals("Token"))
       {         
           System.out.println("Tengo el poder");
           token = true;
       }       
 

   }
   
   private void enviarMSJ(String buffer)
    {
        try 
        {
            salida.writeUTF(buffer);
        }
        catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }
    
    private String recibirMSJ()
    {
        String buffer = "";
        try 
        {
            buffer = entrada.readUTF();
        }
        catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        return buffer;
    }
    
    public boolean isToken() {
        return token;
    }

    public void setToken(boolean token) {
        this.token = token;
    }
}
