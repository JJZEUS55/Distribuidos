/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.DTO;

import com.dist.bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author geoge
 */
public class BDJugador {
    
    private ConexionBD mysql;
    
    public void guardarJuagador(int numJugador, String ip, int puerto){
        mysql = new ConexionBD();
        System.out.println("------ BD JUGADOR: guardarJugador ------");
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
    
}
