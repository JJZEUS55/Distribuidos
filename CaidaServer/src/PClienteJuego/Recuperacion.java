
package PClienteJuego;

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
    
    public Recuperacion ()
    {
        BD = new ConexionBD();
        
    }
    
    public void iniciar()
    {
        int cont = 0;
        try(Connection cn = BD.ConectarpokePro())
        {
            Statement s1 = cn.createStatement();
            ResultSet rs1 = s1.executeQuery("SELECT * FROM servidor order by ronda desc");
            rs1.absolute(1);
            if (rs1.getObject(4) == null) {
                System.out.println("Mejor me inicio de nuevo");
                Ronda = 1;
            }
            else
                this.Ronda = Integer.valueOf(rs1.getObject(4).toString());
            System.out.println("Ronda:" + Ronda);
            while (rs1.next()) 
            {
                System.out.println("carta rep:"+rs1.getObject(2)); 
                if (rs1.getObject(1) != null)
                {
                    if (Integer.valueOf(rs1.getObject(4).toString()) == Ronda) 
                    {
                        cont++;
                        // colocar carta en false
                    }
                    
                }
            }
            if(cont == 3)
                System.out.println("Ronda anterior Terminada");
            else
                System.out.println("Continuar en ronda anterior");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
        
        
    
}
