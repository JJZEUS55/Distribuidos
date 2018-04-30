package prueba;

import com.dist.coordinador.Cartas;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
        
            ObjectInputStream ob = new ObjectInputStream(cs.getInputStream());

            Cartas c = (Cartas) ob.readObject();
            System.out.println(c);
            cs.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Problema " + e.getMessage());
        }
    }
}
