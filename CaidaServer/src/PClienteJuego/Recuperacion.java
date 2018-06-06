
package PClienteJuego;

import PServerJuego.vistaServerJuego;
import PServerJuego.vistaServerJuego1;
import com.dist.bd.ConexionBD;
import com.dist.juego.Carta;
import com.dist.juego.Mazo;
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
    private Mazo MazoRecuperado;
    
    
    public Recuperacion ()
    {
        this.BD = new ConexionBD();
        this.MazoRecuperado = new Mazo();
        this.IP ="0.0.0.0";
        this.puerto = 0; 
        
    }
    
    public void iniciar(boolean token, int numJugador)
    {
        int cont = 0;
        int jugadoresPartidaPasada = 0;
        ResultSet rs1;
        Carta c = new Carta();
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
                if (rs1.getObject(1) != null) 
                {
                    if (Integer.valueOf(rs1.getObject(4).toString()) == Ronda) // la carta fue escogida en la ultima Ronda
                    {
                        c = new Carta();
                        cont++;
                        c.EstablecerCarta(Integer.valueOf(rs1.getObject(2).toString()));                        
                        c.setActiva(false);
                        MazoRecuperado.addCartasMazo(c);
                        System.out.println("Recuperacion: agregar a mazo A"+rs1.getObject(2));
                    }                   
                }
                else if(rs1.getObject(1) == null)
                {
                    c = new Carta();
                    c.EstablecerCarta(Integer.valueOf(rs1.getObject(2).toString()));
                    c.setActiva(true);
                    MazoRecuperado.addCartasMazo(c);
                    System.out.println("Recuperacion: agregar a mazo B"+rs1.getObject(2));
    
                }
            } while (rs1.next());
            System.out.println("Recuperacion: tam mazo anterior:"+MazoRecuperado.getTamano());
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

    public Mazo getMazoRecuperado() {
        return MazoRecuperado;
    }
    
        
        
    
}
