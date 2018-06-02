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
        Jugadores JugadorActual = new Jugadores(); // solo para agregar de manera temporal un jugador para despues pasarlo al arreglo
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
        System.out.println("acceptar: IP puerto " +IP_puerto[0]+ "-" + IP_puerto[1]);
        Num_Jugadores++;
        JugadorActual.setEntrada(entrada);
        JugadorActual.setSalida(salida);
        JugadorActual.setJugador(Num_Jugadores);
        JugadorActual.setIp(IP_puerto[0]);
        JugadorActual.setPuerto(Integer.valueOf(IP_puerto[1]));        
        ConjuntoJugadores.add(JugadorActual);
        System.out.println("acceptar: Cantidad de jugadores:"+ ConjuntoJugadores.size());
        for (int i = 0; i < ConjuntoJugadores.size(); i++) 
        {           
            if(i == ConjuntoJugadores.size()-2) //penultimo
            {
                System.out.println("acceptar: penultimo i="+i);
                entrada = ConjuntoJugadores.get(i).getEntrada();
                salida = ConjuntoJugadores.get(i).getSalida();
                enviarMSJ("info");
                enviarMSJ(String.valueOf(ConjuntoJugadores.get(i).getJugador()));
                enviarMSJ(JugadorActual.getIp());
                enviarMSJ(String.valueOf(JugadorActual.getPuerto()));
                
            } 
            else if(i == ConjuntoJugadores.size()-1 && (i != 0) ) // ultimo
            {
                System.out.println("acceptar: ultimo i="+i);
                entrada = ConjuntoJugadores.get(i).getEntrada();
                salida = ConjuntoJugadores.get(i).getSalida();
                enviarMSJ("info");
                enviarMSJ(String.valueOf(JugadorActual.getJugador())); 
                enviarMSJ(ConjuntoJugadores.get(0).getIp());
                enviarMSJ(String.valueOf(ConjuntoJugadores.get(0).getPuerto()));
            }
            
        }
        System.out.println("-------------");
    }
    
    public void iniciarJuego() // sin uso de momento pero
    {                          //se puede adaptar para enviar las cartas
        Jugadores x; // solo para almacenar de manera temporal el jugador que se esta usando en el ciclo
        for (int i = 0; i < ConjuntoJugadores.size(); i++) 
        {
            x = ConjuntoJugadores.get(i);
            entrada = x.getEntrada();
            salida = x.getSalida();
            enviarMSJ("mazo");
                        
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
