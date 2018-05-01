/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.cliente;

import com.dist.coordinador.Cartas;
import com.dist.coordinador.Mazo;
import java.util.List;

/**
 *
 * @author geoge
 */
public class Jugador {
    private Mazo mazoCartas;
    int token = 0;
    
    public void setMazoCartas(Mazo m){
        this.mazoCartas = m;
    }
    
    public Mazo getMazoCartas(){
        return this.mazoCartas;
    }
    
    
    
}
