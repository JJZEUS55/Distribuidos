package prueba;

import com.dist.coordinador.Cartas;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Conexion //Se hereda de conexión para hacer uso de los sockets y demás
{

    private byte[] mensaje;
    public static InetAddress[] ipPermitidos;
    private int conexiones;
    public static InetAddress ipRequerida;


    public Servidor() throws IOException {
        super("servidor");
        mensaje = new byte[3];
        ipPermitidos = new InetAddress[3];
        conexiones = 0;
    } 

    public void startServer(Cartas car)//Método para iniciar el servidor
    {

        try {
            

            System.out.println("Esperando..."); //Esperando conexión

            cs = ss.accept();
            
            
//            dis = new DataInputStream(cs.getInputStream());
//            
//            System.out.println(dis.readUTF());
            
            //salidaServidor = new DataOutputStream(cs.getOutputStream());
            
            ObjectOutputStream ob = new ObjectOutputStream(cs.getOutputStream());
            
            ob.writeObject(car);
            System.out.println("Se escribio el objeto");         
            
        } catch (IOException e) {
            System.out.println("Problema en: " + e.getMessage());

        }
    }

    

}
