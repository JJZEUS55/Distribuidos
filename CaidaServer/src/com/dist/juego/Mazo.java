/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author geoge
 */
public class Mazo implements Serializable{
    private List<Carta> cincoCartas;

    public Mazo() {
        this.cincoCartas = new ArrayList<Carta>();
    }
    
    public void addCartasMazo(Carta c){
        cincoCartas.add(c);
    }
    
    public List<Carta> getCincoCartas(){
        return this.cincoCartas;
    }
    
    public int getTamano(){
        return cincoCartas.size();
    }
    
}
