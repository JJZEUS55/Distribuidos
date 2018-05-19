package NuevoServer;
import token.tokenCliente;
import token.tokenServer;

/**
 *
 * @author Alan
 */
public class AlgoritmoNS 
{
    private int prioridad;
    private boolean inicioF;
    
    public AlgoritmoNS (int x)
    {
        this.prioridad = x;
        this.inicioF = false;
    }
    
    public int recibirPrioridad(String buffer)
    {
        int comparador;
        comparador = Integer.valueOf(buffer);
        if(comparador < prioridad) //Enviar prioridad
        {
            return 1;
        }
        else if(comparador == prioridad) // seleccionado como servidor enviar fin
        {
            inicioF = true;
            return 2;                    
        }
        else if(buffer.equals("fin")) // inicio de finalizar, reenvia fin al siguiente
        {
            if(inicioF == true)
            {
                return 0; // significa que fin llego al jugador que lo inicio, y ya no es necesario reenviar fin
            }
            return 3;
        }
        else
        { //Envia su propia prioridad
            return 4;
        }
    }
}
