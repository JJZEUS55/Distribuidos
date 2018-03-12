package com.reloj.conexion;

import com.reloj.conexion.Conexion;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Cliente extends Conexion {

    private Scanner entrada;
    private String mensaje;

    public Cliente() throws IOException {
        super("cliente");
    } //Se usa el constructor para cliente de Conexion

    public void startClient() //Método para iniciar el cliente
    {
        try {
            //Flujo de datos hacia el servidor
            salidaServidor = new DataOutputStream(cs.getOutputStream());

            entrada = new Scanner(System.in);
            //Se enviarán dos mensajes
            mensaje = entrada.nextLine();

            //Se escribe en el servidor usando su flujo de datos
            salidaServidor.writeUTF(mensaje);

            cs.close();//Fin de la conexión

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
