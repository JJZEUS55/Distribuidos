package servidorC;
import servidorC.InfoPC;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class clase_server {
    private int PUERTO; //Puerto para la conexión
    private String HOST; //Host para la conexión
    protected ServerSocket ss; //Socket del servidor
    protected Socket sock;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private int prioridad;
    private boolean iniciarSeleccion = false;

    public clase_server(int port) {
        PUERTO = port;
    }
    
    public void iniciar() 
    {      
        try 
        {
            ss = new ServerSocket(PUERTO);
            System.out.println("Esperando Conexion");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }

    public InfoPC aceptar(int jugador) 
    {   
        InfoPC JugadorNuevo = new InfoPC();
        try 
        {
            sock = ss.accept();
            entrada = new DataInputStream(sock.getInputStream());
            salida = new DataOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
        //System.out.println(recibirMSJ());
        JugadorNuevo.setNumero(jugador);
        JugadorNuevo.setEntrada(entrada);
        JugadorNuevo.setSalida(salida);
        System.out.println("accept check || accept bully");
        return JugadorNuevo;
    }
    
    
    
    public void check (InfoPC ob[], int cant)
    {
        for (int i = 0; i < cant; i++) {
            entrada = ob[i].getEntrada();
            salida = ob[i].getSalida();
            enviarMSJ("ok5seg");
        }
    }
    
    public boolean ProcesoSeleccion(InfoPC ob[], int Cbully)
    {
        boolean Elegido = true;
        String buffer;
        int comparador = 0;
        int ChoseenOne = 0;
        
        System.out.println("cantidad de equipos" + Cbully);
        prioridad = ((int) (Math.random() * 100) + 1);
        for (int i = 0; i < 2; i++) { // solicitando las prioridades de cada equipo
            System.out.println("ronda:"+i);
            entrada = ob[i].getEntrada();
            salida = ob[i].getSalida();
            enviarMSJ(String.valueOf(prioridad)); // envia prioridad del servidor
            buffer = recibirMSJ(); // recibe la del cliente
            if(buffer.equals("ok"))
                ob[i].setPrioridad(0);
            else
            {
                ob[i].setPrioridad(Integer.valueOf(buffer));
                Elegido = false;
            }
        }
        if(Elegido == true) // si ningun cliente se propuso, el servidor sera el elegido
        {
            System.out.println("Prioridad:"+prioridad);
            System.out.println("Elegido, iniciando nuevo servidor principal");
            for (int i = 0; i < 2; i++) { // enviando respuestas 
                entrada = ob[i].getEntrada();
                salida = ob[i].getSalida();
                enviarMSJ("No");
            }
            return true;
        }
        else
        {
            System.out.println("Descartado para ser servidor, checando propuestas");
            for (int i = 0; i < Cbully; i++) {
                if (ob[i].getPrioridad() > comparador) {
                    comparador = ob[i].getPrioridad();
                    ChoseenOne = i;
                }
            }
            System.out.println("Servidor propuesto jugador:"+ ChoseenOne + "con prioridad de:"+ ob[ChoseenOne].getPrioridad());
        }
        for (int i = 0; i < 2; i++) { // enviando respuestas 
            entrada = ob[i].getEntrada();
            salida = ob[i].getSalida();
            if(i==ChoseenOne)
                enviarMSJ("Elegido");
            else
                enviarMSJ("No");
        }
        System.out.println("Termino de funcion");
        return false;
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
    
    public void finalizar() throws Throwable
    {
        try {
            this.finalize();
        } catch (Exception e) {}
    }
    
        
    public int getPUERTO() {
        return PUERTO;
    }

    public void setPUERTO(int PUERTO) {
        this.PUERTO = PUERTO;
    }

    public String getHOST() {
        return HOST;
    }

    public void setHOST(String HOST) {
        this.HOST = HOST;
    }
    
    public boolean isIniciarSeleccion() {
        return iniciarSeleccion;
    }

    public void setIniciarSeleccion(boolean iniciarSeleccion) {
        this.iniciarSeleccion = iniciarSeleccion;
    }
    
}
