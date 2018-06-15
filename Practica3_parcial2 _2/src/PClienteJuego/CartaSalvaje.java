package PClienteJuego;

import com.dist.bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CartaSalvaje {
    ConexionBD mysql;


    public CartaSalvaje() {
        mysql = new ConexionBD();
    }
    
    public void EstablecerVida(int vida)
    {
        try (Connection cn = mysql.ConectarpokePro()) {
            PreparedStatement ps2 = cn.prepareStatement("UPDATE salvaje SET vida = ? WHERE id = 1 ");
            ps2.setInt(1, vida);
            ps2.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public int getVida()
    {
        try (Connection cn = mysql.ConectarpokePro())
        {
            Statement s1 = cn.createStatement();
            ResultSet rs1 = s1.executeQuery("SELECT * FROM salvaje" );
            rs1.first();            
            return rs1.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
       
}
    
    

