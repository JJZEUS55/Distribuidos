
package PClienteJuego;

import PServerJuego.vistaServerJuego;
import PServerJuego.vistaServerJuego1;
import com.dist.bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Alan
 */
public class Recuperacion {
    private int Ronda;
    private ConexionBD BD;
    private String IP;
    private int puerto;
    
    
    public Recuperacion ()
    {
        BD = new ConexionBD();
        
    }
    
    public void iniciar(boolean token, int numJugador)
    {
        int cont = 0;
        int jugadoresPartidaPasada = 0;
        ResultSet rs1;
        try(Connection cn = BD.ConectarpokePro())
        {
            Statement s1 = cn.createStatement();
            rs1 = s1.executeQuery("SELECT * FROM servidor order by ronda desc");
            rs1.first();
            if (rs1.getObject(4) == null) {
                System.out.println("Recuperacion: Mejor me inicio de nuevo");
                Ronda = 1;
            }
            else
                this.Ronda = Integer.valueOf(rs1.getObject(4).toString());
            System.out.println("Recuperacion: Ronda:" + Ronda);
            do 
            {
                System.out.println("Recuperacion: carta rep:"+rs1.getObject(2)); 
                if (rs1.getObject(1) != null) // la carta fue escogida
                {
                    if (Integer.valueOf(rs1.getObject(4).toString()) == Ronda) 
                    {
                        cont++;
                        // colocar carta en false
                        // agregar al mazo
                    }
                    
                }
            } while (rs1.next());
            rs1 = s1.executeQuery("SELECT * FROM jugadores");
            rs1.last();
            jugadoresPartidaPasada = rs1.getRow();
            if(cont < jugadoresPartidaPasada)
                System.out.println("Recuperacion: Continuar Ronda anterior");
            else
                System.out.println("Recuperacion: Ronda anterior terminada generando nuevo mazo");
            
        
        
            if (token) 
            {
                numJugador += 1;
                if (numJugador > jugadoresPartidaPasada) {
                    numJugador = 1;
                }
                rs1.first();
                do
                {
                    if (Integer.valueOf(rs1.getObject(1).toString()) == numJugador) 
                    {
                        IP = rs1.getObject(2).toString();
                        puerto = Integer.valueOf(rs1.getObject(3).toString());
                        System.out.println("Recuperacion: Token para:"+numJugador+":"+IP+":"+puerto);   
                    }
                } while(rs1.next());
            }
            else
                System.out.println("Recuperacion: El token no lo tengo, se reestablece mediante los jugadores");                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }

    public String getIP() {
        return IP;
    }

    public int getPuerto() {
        return puerto;
    }
    
        
        
    
}
