package com.reloj.conexion;

import com.reloj.logica.Reloj;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Conexion //Se hereda de conexión para hacer uso de los sockets y demás
{

    private byte[] mensaje;
    public static InetAddress[] ipPermitidos;
    private int conexiones;

    public Servidor() throws IOException {
        super("servidor");
        mensaje = new byte[3];
        ipPermitidos = new InetAddress[3];
        conexiones = 0;
        iniciarIPsPermitidas();
    } //Se usa el constructor para servidor de Conexion

    public void startServer()//Método para iniciar el servidor
    {

        try {

            System.out.println("Esperando..."); //Esperando conexión

            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente
//            while (conexiones < 3) {                
//                cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente
//                ipPermitidos[conexiones] = cs.getInetAddress();
//                conexiones++;
//            }
            if (cs.getInetAddress().equals(ipPermitidos[0])) {
                System.out.println("soy pc alienware mv1");
            } else if (cs.getInetAddress().equals(ipPermitidos[2])) {
                System.out.println("Soy pc samsung");
            }else if (cs.getInetAddress().equals(ipPermitidos[1])){
                System.out.println("soy pc samsung mv1");
            }

//            System.out.println("Cliente en línea ");
//            for (int i = 0; i < 3; i++) {
//                System.out.println(ipPermitidos[i]);
//            }
//            System.out.println(cs.getInetAddress());
//            InetAddress i = InetAddress.getByName("10.0.0.20");
//            if (cs.getInetAddress().equals(i)) {
//                System.out.println("Soy la maquina 1");
//            }
            //System.out.println("Cliente numero: " + dis.read());

            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            salidaCliente = new DataOutputStream(cs.getOutputStream());

            //Se le envía un mensaje al cliente usando su flujo de salida
            //salidaCliente.writeUTF("Petición recibida y aceptada " + "\nTiene ip 10.0.0.6");
            //Se obtiene el flujo entrante desde el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));

            while ((mensajeServidor = entrada.readLine()) != null) //Mientras haya mensajes desde el cliente
            {
                System.out.println(mensajeServidor);
            }
            salidaServidor.writeUTF("Conexion terminada con cliente 1");

            System.out.println("Fin de la conexión");
            cs.close();

            
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public void enviarHora(Reloj r) {
        try {
            mensaje[0] = (byte) r.getHoras();
            mensaje[1] = (byte) r.getMinutos();
            mensaje[2] = (byte) r.getSegundos();
            salidaCliente.write(mensaje);
        } catch (IOException ex) {
            System.out.println("No se pudo enviar la Hora al cliente " + ex.getMessage());
        }
    }

    public InetAddress getCliente() {
         return cs.getInetAddress();
    }
    
    public void cerrarCliente(){
        try {
            cs.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void iniciarIPsPermitidas() {
        try {
            ipPermitidos[0] = InetAddress.getByName("10.0.0.28");
            ipPermitidos[1] = InetAddress.getByName("10.0.0.20");
            ipPermitidos[2] = InetAddress.getByName("10.0.0.27");
        } catch (UnknownHostException ex) {
            System.out.println("No se pudieron inicar las IPs conocidas " + ex.getMessage());
        }
    }

}
