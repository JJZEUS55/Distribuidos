 package PServerJuego;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
    private String buffer;
    private int Num_Jugadores;
    private ArrayList<Jugadores> ConjuntoJugadores;
    
    public ServerJuego(int puerto) 
    {
        this.PUERTO = puerto;   
        this.token = false;
        this.Num_Jugadores = 0;
        this.ConjuntoJugadores = new ArrayList<Jugadores>();
    }
    
    public void iniciar() // inicializa el servidor 
    {      
        try 
        {
            ss = new ServerSocket(PUERTO);
            System.out.println("Servidor en funcionamiento");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }                    
    }
    
    public void acceptar() 
    {
        System.out.println("acceptar: esperando");
        String IP_puerto[];
        Jugadores temporal = new Jugadores(); // solo para agregar de manera temporal un jugador para despues pasarlo al arreglo
        try 
        {
            sock = ss.accept();
            entrada = new DataInputStream(sock.getInputStream());
            salida = new DataOutputStream(sock.getOutputStream());
            System.out.println("Canales listos");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        IP_puerto = recibirMSJ().split(":"); // recibe IP y puerto del juegador  
        System.out.println("acceptar: " +buffer);
        Num_Jugadores++;
        temporal.setEntrada(entrada);
        temporal.setSalida(salida);
        temporal.setJugador(Num_Jugadores);
        temporal.setIp(IP_puerto[0]);
        temporal.setPuerto(Integer.valueOf(IP_puerto[1]));        
        ConjuntoJugadores.add(temporal);
        System.out.println("acceptar: Enviando numero de jugador" );
        System.out.println("acceptar: Cantidad de jugadores:"+ ConjuntoJugadores.size());
        enviarMSJ(String.valueOf(Num_Jugadores)); // le regresa su numero de jugador
        //Enviar IP y puerto del siguiente
        
    }
    
    public void iniciarJuego() // para enviar informacion sobre el siguiente jugador
    {
        Jugadores x; // solo para almacenar de manera temporal el jugador que se esta usando en el ciclo
        for (int i = 0; i < ConjuntoJugadores.size(); i++) 
        {
            x = ConjuntoJugadores.get(i);
            entrada = x.getEntrada();
            salida = x.getSalida();
            if (i < ConjuntoJugadores.size()-1) 
            {
                System.out.println("Puerto:"+ ConjuntoJugadores.get(i+1).getPuerto());
                enviarMSJ(ConjuntoJugadores.get(i+1).getIp());
                enviarMSJ(String.valueOf(ConjuntoJugadores.get(i+1).getPuerto()));
            }
            else
            {
                System.out.println("Puerto:"+ ConjuntoJugadores.get(0).getPuerto());
                enviarMSJ(ConjuntoJugadores.get(0).getIp());
                enviarMSJ(String.valueOf(ConjuntoJugadores.get(0).getPuerto()));            
            }
            
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
    
    
}
