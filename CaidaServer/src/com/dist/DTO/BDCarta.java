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
import java.sql.SQLException;

/**
 *
 * @author geoge
 */
public class BDCarta {

    private Carta carta;
    private Mazo m;
    private ConexionBD mysql;
    private static final String bd = "pokePro1";

    public BDCarta() {
    }

    public BDCarta(Mazo m) {
        this.m = m;
    }

    public BDCarta(Carta c) {
        this.carta = c;
    }

    public void borrarTodoTablas() {
        mysql = new ConexionBD();
        try (Connection cn = mysql.ConectarpokePro()) {
            PreparedStatement ps0 = cn.prepareStatement("SET SQL_SAFE_UPDATES = 0;");
            PreparedStatement ps1 = cn.prepareStatement("delete from cartas;");
            PreparedStatement ps2 = cn.prepareStatement("delete from servidor;");
            PreparedStatement ps3 = cn.prepareStatement("delete from jugadores;");
            PreparedStatement ps4 = cn.prepareStatement("delete from jugadorCartas;");
            
            ps0.execute();
            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            ps4.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarMazoServidor(Mazo mazo) {
        mysql = new ConexionBD();
        try (Connection cn = mysql.ConectarpokePro()) {
            //Guardando Cartas
            PreparedStatement ps1 = cn.prepareStatement("INSERT INTO cartas VALUES(?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement ps2 = cn.prepareStatement("INSERT INTO servidor (cartasRepartidas) VALUES(?)");
            for (int i = 0; i < mazo.getTamano(); i++) {
                ps1.setInt(1, mazo.getCartas().get(i).getNum());
                ps1.setString(2, mazo.getCartas().get(i).getNombre());
                ps1.setString(3, mazo.getCartas().get(i).getTipo1());
                ps1.setString(4, mazo.getCartas().get(i).getTipo2());
                ps1.setInt(5, mazo.getCartas().get(i).getHp());
                ps1.setInt(6, mazo.getCartas().get(i).getAtaque());
                ps1.setInt(7, mazo.getCartas().get(i).getDefensa());
                ps2.setInt(1, mazo.getCartas().get(i).getNum());
                ps1.executeUpdate();
                ps2.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarCartaCliente(int numJugador, String horaLamp, Carta c, int ronda) {
        mysql = new ConexionBD();
        try (Connection cn = mysql.ConectarpokePro()) {
            PreparedStatement ps1 = cn.prepareStatement("INSERT INTO jugadorCartas VALUES(?, ?, ?, ?)");
            PreparedStatement ps2 = cn.prepareStatement("UPDATE servidor SET clienteConectado = ?, horaLamport = ?, ronda = ? WHERE cartasRepartidas = " + c.getNum());

            ps1.setInt(1, numJugador);
            ps1.setInt(2, c.getNum());
            ps1.setString(3, horaLamp);
            ps1.setInt(4, ronda);

            ps2.setInt(1, numJugador);
            ps2.setString(2, horaLamp);
            ps2.setInt(3, ronda);
            ps1.executeUpdate();
            ps2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
