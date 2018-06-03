package PServerJuego;

import com.dist.juego.Carta;
import com.dist.juego.Mazo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    private boolean cambios;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private int Num_Jugadores;
    private Jugadores JugadorActual;
    private static ArrayList<Jugadores> ConjuntoJugadores;
    private Mazo mazoEnviar;

    public ServerJuego(int puerto) {
        this.PUERTO = puerto;
        this.cambios = false;
        this.Num_Jugadores = 0;
        this.ConjuntoJugadores = new ArrayList<Jugadores>();
    }

    public void iniciar() // inicializa el servidor 
    {
        try {
            ss = new ServerSocket(PUERTO);
            System.out.println("Servidor en funcionamiento");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }

    public void acceptar() {
        System.out.println("------ Class Servidor: acceptar ------");
        System.out.println("acceptar: esperando");
        String buffer;
        try {
            sock = ss.accept();
            entrada = new DataInputStream(sock.getInputStream());
            salida = new DataOutputStream(sock.getOutputStream());
            System.out.println("Canales listos");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        buffer = recibirMSJ();
        System.out.println("Esperar mensaje: " + buffer);
        String IP_puerto[];
        JugadorActual = new Jugadores(); // solo para agregar de manera temporal un jugador para despues pasarlo al arreglo       
        IP_puerto = buffer.split(":"); // recibe IP y puerto del juegador  
        System.out.println("acceptar: IP puerto " + IP_puerto[0] + "-" + IP_puerto[1]);
        Num_Jugadores++;
        JugadorActual.setEntrada(entrada);
        JugadorActual.setSalida(salida);
        JugadorActual.setJugador(Num_Jugadores);
        JugadorActual.setIp(IP_puerto[0]);
        JugadorActual.setPuerto(Integer.valueOf(IP_puerto[1]));
        JugadorActual.setSock(sock);
        ConjuntoJugadores.add(JugadorActual);
        Thread t = new atenderCliente(JugadorActual);
        atenderCliente t1 = (atenderCliente) t;
        t1.setMazotoCliente(mazoEnviar);
        t.start();        
        nuevo(buffer);

    }

    public void nuevo(String buffer) {
        System.out.println("------ Class Servidor: nuevo ------");
        System.out.println("acceptar: Cantidad de jugadores:" + ConjuntoJugadores.size());
        for (int i = 0; i < ConjuntoJugadores.size(); i++) {
            if (i == ConjuntoJugadores.size() - 2) //penultimo
            {
                System.out.println("acceptar: penultimo i=" + i);
                entrada = ConjuntoJugadores.get(i).getEntrada();
                salida = ConjuntoJugadores.get(i).getSalida();
                enviarMSJ("info");
                enviarMSJ(String.valueOf(ConjuntoJugadores.get(i).getJugador()));
                enviarMSJ(JugadorActual.getIp());
                enviarMSJ(String.valueOf(JugadorActual.getPuerto()));

            } else if (i == ConjuntoJugadores.size() - 1 && (i != 0)) // ultimo
            {
                System.out.println("acceptar: ultimo i=" + i);
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

    public int Cartas(String buffer) // Trama ---|-|
    {
        System.out.println("Mensajes: mensaje completo = " + buffer);
        System.out.println("Mensajes:" + buffer.substring(0, 3));
        int NumeroJugador = Integer.valueOf(buffer.substring(3));
        System.out.println("Cliente " + NumeroJugador + " Solicita Cartas");
        return 1;
    }

    private void enviarMSJ(String buffer) {
        try {
            salida.writeUTF(buffer);
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }

    private String recibirMSJ() {
        String buffer = "";
        try {
            buffer = entrada.readUTF();
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        return buffer;
    }

    public int getNum_Jugadores() {
        return Num_Jugadores;
    }

    public Socket getSock() {
        return sock;
    }

    public DataInputStream getEntrada() {
        return entrada;
    }

    public DataOutputStream getSalida() {
        return salida;
    }

    public ArrayList<Jugadores> getConjuntoJugadores() {
        return ConjuntoJugadores;
    }
    
    public void setMazoServidor(Mazo m){
        this.mazoEnviar = m;
    }

}
