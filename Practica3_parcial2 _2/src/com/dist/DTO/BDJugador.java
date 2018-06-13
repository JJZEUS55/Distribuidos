/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.DTO;

import com.dist.bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author geoge
 */
public class BDJugador {
    
    private ConexionBD mysql;
    
    public void guardarJuagador(int numJugador, String ip, int puerto){
        mysql = new ConexionBD();
        System.out.println("guardarJugador ");
        try(Connection cn = mysql.ConectarpokePro()){
            PreparedStatement ps1 = cn.prepareStatement("INSERT INTO jugadores VALUES(?, ?, ?)");
            ps1.setInt(1, numJugador);
            ps1.setString(2, ip);
            ps1.setInt(3, puerto);
            ps1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }
    
    public int obtenerUltimoJ()
    {
        mysql = new ConexionBD();
        try (Connection cn = mysql.ConectarpokePro()) 
        {
            Statement s1 = cn.createStatement();
            ResultSet rs1 = s1.executeQuery("SELECT numJugador FROM jugadores");
            rs1.last();
            return Integer.valueOf(rs1.getObject(1).toString());
        } 
        catch(Exception e){System.out.println(e);}
        return 0;
    }
    
}
