
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
    private int prioridad; 
    private String IP;


    private boolean inicioF; 
    
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
    
    public int acceptar() 
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
        return 1;
    }
           
   public String EsperarMensaje() //puede ser el roken o los mensajes para seleccionar el nuevo servidor
   {
       int comparador;
       String buffer;   
       buffer = recibirMSJ();
       System.out.println(buffer);
       if(buffer.equals("Token"))
       {         
           System.out.println("Tengo el poder");
           token = true;
           return "Token";
       }       
       else
       {           
           System.out.println("Mi prioridad: " + prioridad);
           if(buffer.startsWith("fin")) // reenvia fin al siguiente
            {               
                System.out.println("Iniciando "+buffer);                
                return(buffer);                
            }            
            comparador = Integer.valueOf(buffer);
            if(prioridad > comparador) //Enviar su prioridad
            {
                System.out.println("Me propongo("+prioridad+" > "+comparador+")");
                return(""+prioridad);
            }
            else if(comparador == prioridad) // seleccionado como servidor, enviar fin
            {
                System.out.println("Soy el nuevo servidor");
                return("fin:"+IP);                    
            }
            else
            {
                System.out.println("No puedo ser servidor");
                return (buffer);//reenvia la prioridad del jugador anterior
            }            
       }
   }
      
   public void enviarMSJ(String buffer)
    {
        try 
        {
            salida.writeUTF(buffer);
        }
        catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }
    
    public String recibirMSJ()
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

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
    
    

}
