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
    private List<Carta> listCartas;

    public Mazo() {
        this.listCartas = new ArrayList<Carta>();
    }
    
    public void addCartasMazo(Carta c){
        listCartas.add(c);
    }
    
    public List<Carta> getCartas(){
        return this.listCartas;
    }
    
    public void deleteCarta(int idCarta){
        listCartas.remove(idCarta);
    }
    
    public int getTamano(){
        return listCartas.size();
    }
    
}
