
package PServerJuego;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class atenderCliente extends Thread{
    private DataInputStream entrada;
    private DataOutputStream salida;
    protected Socket sock;
    private int jugador;
    
    public atenderCliente(Jugadores j)
    {
        this.entrada = j.getEntrada();
        this.salida = j.getSalida();
        this.sock = j.getSock();
        this.jugador = j.getJugador();
    }
    
    @Override
    public void run()
    {
        System.out.println("Nuevo Hilo atender cliente");
        String buffer;
        while(true)
        {
            buffer = recibirMSJ();
            System.out.println("jugador "+jugador+": "+buffer);
            switch(buffer)
            {
                case "cartas":
                    System.out.println("pidio cartas");
                    if (vistaServerJuego.Servidor_Principal.getConjuntoJugadores().size() == jugador) {
                        System.out.println("Cambiar de ronda");
                    }
                    enviarMSJ("cartas"); 
                    
                    break;
                case "fin":
                    break; 
                default:
                    System.out.println("Err");
            }            
            if(buffer.equals("fin"))
                break;           
        }
        System.out.println("Termino");
        try
        {
            // closing resources
            this.entrada.close();
            this.salida.close();
             
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
        
    private void enviarMSJ(String buffer) {
        try {
            salida.writeUTF(buffer);
        } catch (IOException e) {
            System.out.println("(ENV)Error de entrada/salida.");
        }
    }
    
    private String recibirMSJ() {
        String buffer = "";
        try {
            buffer = entrada.readUTF();
        } catch (IOException e) {
            System.out.println("(REC)Error de entrada/salida.");
        }
        return buffer;
    }
    
}
