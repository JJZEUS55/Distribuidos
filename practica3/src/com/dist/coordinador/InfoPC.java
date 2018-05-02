/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.coordinador;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class InfoPC {
    private DataInputStream Entrada;
    private DataOutputStream Salida;
    private int numero;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public DataInputStream getEntrada() {
        return Entrada;
    }

    public void setEntrada(DataInputStream Entrada) {
        this.Entrada = Entrada;
    }

    public DataOutputStream getSalida() {
        return Salida;
    }

    public void setSalida(DataOutputStream Salida) {
        this.Salida = Salida;
    }
    
  
    public InfoPC()
    {
        
    }
    
}
