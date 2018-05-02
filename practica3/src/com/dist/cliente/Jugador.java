/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.cliente;

import com.dist.bd.ConexionBD;
import com.dist.coordinador.Cartas;
import com.dist.coordinador.Mazo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author geoge
 */
public class Jugador {

    private Mazo mazoCartas;
    private ConexionBD mysql;

    public void guardarCartaBD(int i) {
        mysql = new ConexionBD();

        try (Connection cn = mysql.ConectarJugador()) {
            String query = "INSERT INTO datos (num, nombre, tipo1, tipo2, hp, ataque, defensa) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement pst = cn.prepareStatement(query);
            pst.setInt(1, mazoCartas.getCincoCartas().get(i).getNum());
            pst.setString(2, mazoCartas.getCincoCartas().get(i).getNombre());
            pst.setString(3, mazoCartas.getCincoCartas().get(i).getTipo1());
            pst.setString(4, mazoCartas.getCincoCartas().get(i).getTipo2());
            pst.setInt(5, mazoCartas.getCincoCartas().get(i).getHp());
            pst.setInt(6, mazoCartas.getCincoCartas().get(i).getAtaque());
            pst.setInt(7, mazoCartas.getCincoCartas().get(i).getDefensa());          
            int n = pst.executeUpdate();
            if (n > 0) {
                System.out.println("EXITO AL REGISTRAR CARTA A BD");
            }else{
                System.err.println("FALLO AL REGISTRAR CARTA A BD");
            }
        } catch (Exception e) {
        }
    }
    
    public void guardarMazoBD(){
        for (int i = 0; i < mazoCartas.getTamano(); i++) {
            guardarCartaBD(i);
        }
    }

    public void setMazoCartas(Mazo m) {
        this.mazoCartas = m;
    }

    public Mazo getMazoCartas() {
        return this.mazoCartas;
    }

}
