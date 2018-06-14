/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.juego;

import com.dist.bd.ConexionBD;
import java.awt.Image;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author geoge
 */
public class Carta implements Serializable {

    private int num;
    private String nombre;
    private String tipo1;
    private String tipo2;
    private int hp;
    private int ataque;
    private int defensa;
    private boolean activa;
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
                this.activa = true;
            }

        } catch (SQLException e) {
            System.err.println("Problema cartas " + e);
        }

    }

    public void EstablecerCarta(int numC) {
        mysql = new ConexionBD();
        try (Connection cn = mysql.Conectar()) {
            Statement s1 = cn.createStatement();
            ResultSet rs1 = s1.executeQuery("SELECT * FROM datos WHERE num=" + numC);
            while (rs1.next()) {
                this.num = Integer.parseInt(rs1.getObject(1).toString());
                this.nombre = rs1.getObject(2).toString();
                this.tipo1 = rs1.getObject(3).toString();
                this.tipo2 = rs1.getObject(4).toString();
                this.hp = Integer.parseInt(rs1.getObject(5).toString());
                this.ataque = Integer.parseInt(rs1.getObject(6).toString());
                this.defensa = Integer.parseInt(rs1.getObject(7).toString());
                ingresarCartasOcupadas(this.num);
            }

        } catch (SQLException e) {
            System.err.println("Problema cartas " + e);
        }
        mysql = new ConexionBD();
        try (Connection cn = mysql.ConectarpokePro()) {

            Statement s1 = cn.createStatement();
            ResultSet rs1 = s1.executeQuery("SELECT * FROM cartas WHERE num=" + numC);
            rs1.first();
            if (rs1.getInt("estado") == 1) {
                this.activa = true;
            } else {
                this.activa = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
                cont++;
                break;
            }
        }
        return aux;
    }

    public void ingresarCartasOcupadas(int carta) {
        cartasSeleccionadas[carta] = carta;
    }

    public void addImagenCarta() {
        this.icono = new ImageIcon("Imagenes\\" + this.num + ".png");
        Image imgEscalada = this.icono.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_AREA_AVERAGING);
        this.icono = new ImageIcon(imgEscalada);
        //System.out.println("numero " + this.num);
    }

    public ImageIcon getIconPokemon() {
        ImageIcon icon = new ImageIcon("Imagenes\\" + this.num + ".png");
        Image imgEscalada = icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_AREA_AVERAGING);
        icon = new ImageIcon(imgEscalada);
        return icon;
    }

    public void Atacar(Carta cartaAtacar) {
        int hpRestante = cartaAtacar.getHp();
        int dano = (int) ((this.getAtaque() * Multiplicador(this.getTipo1(), cartaAtacar.getTipo1()))  - (cartaAtacar.getDefensa() / 3));
        double dano2 = ((this.getAtaque() * Multiplicador(this.getTipo1(), cartaAtacar.getTipo1()))  - (cartaAtacar.getDefensa() / 3));
        if(dano <= 1){
            hpRestante -= 1;
        }else{
            hpRestante -= dano;
        }
        
        System.out.println("hiciste un daño de " + (this.getAtaque() * Multiplicador(this.getTipo1(), cartaAtacar.getTipo1())));
        //JOptionPane.showMessageDialog(null, "Hiciste un daño de: " + dano, "Daño",JOptionPane.DEFAULT_OPTION);
        //JOptionPane.showMessageDialog(null, "Hiciste un daño de: " + dano2, "Daño",JOptionPane.DEFAULT_OPTION);
        cartaAtacar.setHP(hpRestante);
        if (cartaAtacar.getHp() <= 0) {
            System.out.println("Ganaste");
        }       
    }

//    public void defender(Carta CartaDelJugador) {
//        int HP_jugador = CartaDelJugador.getHp();
//        HP_jugador -= CartaSalvaje.getAtaque() * Multiplicador(CartaSalvaje.getTipo1(), CartaDelJugador.getTipo1());
//        if (HP_jugador <= 0) {
//            System.out.println("Murio tu pokemon");
//            ContadorMuertes++;
//        }
//        if (ContadorMuertes >= CantidadCartas) {
//            System.out.println("GG");
//        }
//    }

    private double Multiplicador(String tipoAtacante, String tipoDefensor) {
        double val = 1;
        switch (tipoAtacante) {
            case "bug":
                switch (tipoDefensor) {
                    case "steel":
                        val = 0.5;
                        break;
                    case "fire":
                        val = 0.5;
                        break;
                    case "fairy":
                        val = 0.5;
                        break;
                    case "fighting":
                        val = 0.5;
                        break;
                    case "grass":
                        val = 2;
                        break;
                    case "phychic":
                        val = 2;
                        break;
                    case "poison":
                        val = 0.5;
                        break;
                    case "flying":
                        val = 2;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "dragon":
                switch (tipoDefensor) {
                    case "steel":
                        val = 0.5;
                        break;
                    case "fairy":
                        val = 0;
                        break;
                    case "dragon":
                        val = 2;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "electric":
                switch (tipoDefensor) {
                    case "water":
                        val = 2;
                        break;
                    case "fire":
                        val = 0.5;
                        break;
                    case "electric":
                        val = 0.5;
                        break;
                    case "ground":
                        val = 0;
                        break;
                    case "flying":
                        val = 2;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "fairy":
                switch (tipoDefensor) {
                    case "steel":
                        val = 0.5;
                        break;
                    case "fire":
                        val = 0.5;
                        break;
                    case "dragon":
                        val = 2;
                        break;
                    case "fighting":
                        val = 2;
                        break;
                    case "poison":
                        val = 0.5;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "fighting":
                switch (tipoDefensor) {
                    case "steel":
                        val = 2;
                        break;
                    case "bug":
                        val = 0.5;
                        break;
                    case "fairy":
                        val = 0.5;
                        break;
                    case "ice":
                        val = 2;
                        break;
                    case "grass":
                        val = 2;
                        break;
                    case "normal":
                        val = 2;
                        break;
                    case "phychic":
                        val = 0.5;
                        break;
                    case "rock":
                        val = 2;
                        break;
                    case "poison":
                        val = 0.5;
                        break;
                    case "flying":
                        val = 0.0;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "fire":
                switch (tipoDefensor) {
                    case "steel":
                        val = 2;
                        break;
                    case "bug":
                        val = 2;
                        break;
                    case "water":
                        val = 2;
                        break;
                    case "ice":
                        val = 2;
                        break;
                    case "grass":
                        val = 2;
                        break;
                    case "dragon":
                        val = 0.5;
                        break;
                    case "fire":
                        val = 0.5;
                        break;
                    case "rock":
                        val = 0.5;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "flying":
                switch (tipoDefensor) {
                    case "steel":
                        val = 0.5;
                        break;
                    case "bug":
                        val = 2;
                        break;
                    case "electric":
                        val = 0.5;
                        break;
                    case "fighting":
                        val = 2;
                        break;
                    case "grass":
                        val = 2;
                        break;
                    case "rock":
                        val = 0.5;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "grass":
                switch (tipoDefensor) {
                    case "steel":
                        val = 0.5;
                        break;
                    case "bug":
                        val = 0.5;
                        break;
                    case "water":
                        val = 2;
                        break;
                    case "dragon":
                        val = 0.5;
                        break;
                    case "fire":
                        val = 0.5;
                        break;
                    case "grass":
                        val = 0.5;
                        break;
                    case "phychic":
                        val = 0.5;
                        break;
                    case "rock":
                        val = 2;
                        break;
                    case "poison":
                        val = 0.5;
                        break;
                    case "flying":
                        val = 0.5;
                        break;
                    case "ground":
                        val = 0.5;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "ground":
                switch (tipoDefensor) {
                    case "steel":
                        val = 2;
                        break;
                    case "bug":
                        val = 0.5;
                        break;
                    case "electic":
                        val = 2;
                        break;
                    case "fire":
                        val = 2;
                        break;
                    case "grass":
                        val = 0.5;
                        break;
                    case "rock":
                        val = 2;
                        break;
                    case "poison":
                        val = 2;
                        break;
                    case "flying":
                        val = 0;
                        break;
                    case "ground":
                        val = 0.5;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "ice":
                switch (tipoDefensor) {
                    case "steel":
                        val = 0.5;
                        break;
                    case "ice":
                        val = 0.5;
                        break;
                    case "water":
                        val = 0.5;
                        break;
                    case "dragon":
                        val = 2;
                        break;
                    case "fire":
                        val = 0.5;
                        break;
                    case "grass":
                        val = 2;
                        break;
                    case "flying":
                        val = 2;
                        break;
                    case "ground":
                        val = 2;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "normal":
                switch (tipoDefensor) {
                    case "steel":
                        val = 0.5;
                        break;
                    case "rock":
                        val = 0.5;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "poison":
                switch (tipoDefensor) {
                    case "steel":
                        val = 0;
                        break;
                    case "fairy":
                        val = 2;
                        break;
                    case "grass":
                        val = 2;
                        break;
                    case "rock":
                        val = 0.5;
                        break;
                    case "poison":
                        val = 0.5;
                        break;
                    case "ground":
                        val = 0.5;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "phychic":
                switch (tipoDefensor) {
                    case "steel":
                        val = 0.5;
                        break;
                    case "fighting":
                        val = 0.5;
                        break;
                    case "phychic":
                        val = 0.5;
                        break;
                    case "poison":
                        val = 0.5;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "rock":
                switch (tipoDefensor) {
                    case "steel":
                        val = 0.5;
                        break;
                    case "bug":
                        val = 2;
                        break;
                    case "ice":
                        val = 2;
                        break;
                    case "fighting":
                        val = 0.5;
                        break;
                    case "fire":
                        val = 0.5;
                        break;
                    case "flying":
                        val = 2;
                        break;
                    case "ground":
                        val = 0.5;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "steel":
                switch (tipoDefensor) {
                    case "steel":
                        val = 0.5;
                        break;
                    case "electric":
                        val = 0.5;
                        break;
                    case "water":
                        val = 0.5;
                        break;
                    case "fairy":
                        val = 2;
                        break;
                    case "fire":
                        val = 0.5;
                        break;
                    case "ice":
                        val = 2;
                        break;
                    case "rock":
                        val = 2;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            case "water":
                switch (tipoDefensor) {
                    case "water":
                        val = 0.5;
                        break;
                    case "dragon":
                        val = 0.5;
                        break;
                    case "fire":
                        val = 2;
                        break;
                    case "grass":
                        val = 0.5;
                        break;
                    case "rock":
                        val = 2;
                        break;
                    case "ground":
                        val = 2;
                        break;
                    default:
                        val = 1;
                        break;
                }
                break;
            default:
                val = 0;
        }
        return val;
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

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    public void setHP(int hp){
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "Cartas{" + "num=" + num + ", nombre=" + nombre + ", tipo1=" + tipo1 + ", tipo2=" + tipo2 + ", hp=" + hp + ", ataque=" + ataque + ", defensa=" + defensa + '}';
    }

}
