/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.coordinador;

import com.dist.bd.ConexionBD;
import java.awt.Image;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author geoge
 */
public class Cartas implements Serializable {

    private int num;
    private String nombre;
    private String tipo1;
    private String tipo2;
    private int hp;
    private int ataque;
    private int defensa;
    private transient ConexionBD mysql;
    private transient ImageIcon icono;
    private static int[] cartasSeleccionadas = new int[200];
    private static int cont = 0;

    public void getCartaAleatoria() {
        Random r = new Random();
        do {
            num = r.nextInt(151) + 1;            
        } while (verificarDisponibilidadCarta(num) == false);

        mysql = new ConexionBD();
        try (Connection cn = mysql.Conectar()) {
            Statement s1 = cn.createStatement();
            ResultSet rs1 = s1.executeQuery("SELECT * FROM datos WHERE num=" + num);

            while (rs1.next()) {
                this.nombre = rs1.getObject(2).toString();
                this.tipo1 = rs1.getObject(3).toString();
                this.tipo2 = rs1.getObject(4).toString();
                this.hp = Integer.parseInt(rs1.getObject(5).toString());
                this.ataque = Integer.parseInt(rs1.getObject(6).toString());
                this.defensa = Integer.parseInt(rs1.getObject(7).toString());
            }

            cont++;

        } catch (SQLException e) {
            System.err.println("Problema cartas " + e);
        }

    }

    public boolean verificarDisponibilidadCarta(int numCarta) {
        boolean aux = false;
        for (int cartasSeleccionada : cartasSeleccionadas) {
            if (numCarta == cartasSeleccionada) {
                aux = false;
                System.out.println("Se repitio carta #" + numCarta + "!!! Seleccionando otra");
                break;
            } else {
                cartasSeleccionadas[cont] = numCarta;
                aux = true;
                break;
            }
        }
        return aux;
    }

    public void addImagenCarta() {
        this.icono = new ImageIcon("Imagenes\\" + this.num + ".png");
        Image imgEscalada = this.icono.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_AREA_AVERAGING);
        this.icono = new ImageIcon(imgEscalada);
        System.out.println("numero " + this.num);
    }

    public int getNum() {
        return this.num;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTipo1() {
        return tipo1;
    }

    public String getTipo2() {
        return tipo2;
    }

    public int getHp() {
        return hp;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public ImageIcon getImagenPokemon() {
        return this.icono;
    }

    @Override
    public String toString() {
        return "Cartas{" + "num=" + num + ", nombre=" + nombre + ", tipo1=" + tipo1 + ", tipo2=" + tipo2 + ", hp=" + hp + ", ataque=" + ataque + ", defensa=" + defensa + '}';
    }

}