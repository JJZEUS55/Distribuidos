package PServerJuego;

import com.dist.juego.Carta;
import com.dist.juego.Mazo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class atenderCliente extends Thread {

    private DataInputStream entrada;
    private DataOutputStream salida;
    protected Socket sock;
    private int jugador;

    public atenderCliente(Jugadores j) {
        this.entrada = j.getEntrada();
        this.salida = j.getSalida();
        this.sock = j.getSock();
        this.jugador = j.getJugador();
    }

    @Override
    public void run() {
        System.out.println("----- Class Atender Cliente -------");
        System.out.println("Nuevo Hilo atender cliente");
        String buffer;

        while (true) {
            try {
                buffer = recibirMSJ();
                System.out.println("jugador " + jugador + ": " + buffer);

                System.out.println("------ Entrando SWITHC DESCARTIVAR ---------");
                switch (buffer) {
                    case "cartas":
                        System.out.println("pidio cartas");
                        enviarMSJ("hora");
                        enviarMSJ(vistaServerJuego.rel.imprimeHora());
                        enviarMSJ("cartas");
                        enviarRonda(vistaServerJuego1.numCartas);
                        enviarCarta(vistaServerJuego1.Servidor_Principal.mazoEnviar);
                        break;
                    case "seleccion1":
                        System.out.println("-------- DES 1 ---------");
                        System.out.println("Desactivando Carta 1");
                        vistaServerJuego1.Servidor_Principal.mazoEnviar.getCartas().get(0).setActiva(false);
                        if (vistaServerJuego1.Servidor_Principal.getConjuntoJugadores().size() == jugador) {
                            System.out.println("-------- DES NUEVO ---------");
                            System.out.println("Generando Nuevas Cartas");
                            TimeUnit.SECONDS.sleep(2);
                            vistaServerJuego1.jbtnSelecCartas.doClick();
                        }
                        break;
                    case "seleccion2":
                        System.out.println("-------- DES 2 ---------");
                        System.out.println("Desactivando Carta 2");
                        vistaServerJuego1.Servidor_Principal.mazoEnviar.getCartas().get(1).setActiva(false);
                        if (vistaServerJuego1.Servidor_Principal.getConjuntoJugadores().size() == jugador) {
                            System.out.println("-------- DES NUEVO ---------");
                            System.out.println("Generando Nuevas Cartas");
                            TimeUnit.SECONDS.sleep(2);
                            vistaServerJuego1.jbtnSelecCartas.doClick();
                        }
                        break;
                    case "seleccion3":
                        System.out.println("-------- DES 3 ---------");
                        System.out.println("Desactivando Carta 3");
                        vistaServerJuego1.Servidor_Principal.mazoEnviar.getCartas().get(2).setActiva(false);
                        if (vistaServerJuego1.Servidor_Principal.getConjuntoJugadores().size() == jugador) {
                            System.out.println("-------- DES NUEVO ---------");
                            System.out.println("Generando Nuevas Cartas");
                            TimeUnit.SECONDS.sleep(2);
                            vistaServerJuego1.jbtnSelecCartas.doClick();
                        }
                        break;
                    case "fin":
                        break;
                    default:
                        System.out.println("Err");
                        break;
                }

                if (buffer.equals("fin")) {
                    break;
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Termino");
        try {
            // closing resources
            this.entrada.close();
            this.salida.close();

        } catch (IOException e) {
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

    private void enviarCarta(Mazo m) {
        ObjectOutputStream ob = null;
        try {
            ob = new ObjectOutputStream(sock.getOutputStream());
            ob.writeObject(m);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(atenderCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void enviarRonda(int ronda) {
        try {
            salida.writeInt(ronda);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(atenderCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String recibirMSJ() {
        String buffer = "";
        try {
            buffer = entrada.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("(REC)Error de entrada/salida.");
        }
        return buffer;
    }

}
