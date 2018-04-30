/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.coordinador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author geoge
 */
public class Mazo implements Serializable{
    private List<Cartas> cincoCartas;

    public Mazo() {
        this.cincoCartas = new ArrayList<Cartas>();
    }
    
    public void addCartasMazo(Cartas c){
        cincoCartas.add(c);
    }
    
    public List<Cartas> getCincoCartas(){
        return this.cincoCartas;
    }
    
}
