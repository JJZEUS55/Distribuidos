/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dist.DTO;

/**
 *
 * @author geoge
 */
public enum ServidoresNom {
    
    SERVIDOR1("192.168.0.3"),
    SERVIDOR2("192.168.0.14"),
    SERVIDOR3("192.168.0.16");
    
    private String host;
    
    ServidoresNom(String host){
        this.host = host;
    }
    
    public String getHost(){
        return this.host;
    }
}
