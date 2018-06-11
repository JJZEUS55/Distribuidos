/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.DTO;

import com.dist.bd.ConexionBD;
import com.dist.juego.Carta;
import com.dist.juego.Mazo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author geoge
 */
public class BDJugador {

    private ConexionBD mysql;

    public void guardarJuagador(int numJugador, String ip, int puerto, String host) {
        mysql = new ConexionBD();
        System.out.println("------ BD JUGADOR: guardarJugador ------");
        try (Connection cn = mysql.ConectarpokePro(host)) {
            PreparedStatement ps1 = cn.prepareStatement("INSERT INTO jugadores VALUES(?, ?, ?)");
            ps1.setInt(1, numJugador);
            ps1.setString(2, ip);
            ps1.setInt(3, puerto);
            ps1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Mazo getMazoJugador(int numJugador, String host) {
        String nombre, tipo1, tipo2;
        int num, hp, ataque, defensa;
        Mazo m = new Mazo();
        Carta c = null;
        mysql = new ConexionBD();
        try (Connection cn = mysql.ConectarpokePro(host)) {
            PreparedStatement ps1 = cn.prepareStatement("select c.num, c.nombre, c.tipo1, c.tipo2, c.hp, c.ataque, c.defensa "
                    + "from cartas c, jugadorCartas j "
                    + "where c.num = j.cartaSeleccionada "
                    + "and j.numJugador = " + numJugador);
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                num = Integer.parseInt(rs.getObject(1).toString());
                nombre = rs.getObject(2).toString();
                tipo1 = rs.getObject(3).toString();
                tipo2 = rs.getObject(4).toString();
                hp = Integer.parseInt(rs.getObject(5).toString());
                ataque = Integer.parseInt(rs.getObject(6).toString());
                defensa = Integer.parseInt(rs.getObject(7).toString());
                c = new Carta(num, nombre, tipo1, tipo2, hp, ataque, defensa);
                m.addCartasMazo(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

}
