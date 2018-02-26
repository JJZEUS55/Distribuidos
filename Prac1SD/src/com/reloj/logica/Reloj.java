/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reloj.logica;

import java.time.Clock;
import java.util.Calendar;

/**
 *
 * @author geoge
 */
public class Reloj {
    
    private Calendar calendario;
    private int horas;
    private int minutos;
    private int segundos;
    
    private String shoras;
    private String sminutos;
    private String ssegundos;

    public Reloj(int horas, int minutos, int segundos) {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }
    
    public Reloj(){
        getHoraActual();
    }
    
    public void getHoraActual(){
        calendario = Calendar.getInstance();
        horas = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);        
    }
    
    public void modificarHora(int hora, int minuto, int segundo){
        calendario.set(Calendar.HOUR_OF_DAY, hora);
        calendario.set(Calendar.MINUTE, minuto);
        calendario.set(Calendar.SECOND, hora);
        
        horas = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
    }
    
    public void pasarTiempo(){
        segundos++;
        if(segundos > 59){
            segundos = 0;
            minutos++;
            if(minutos > 59){
                minutos = 0;
                horas++;
                if(horas > 24){
                    horas = 0;
                }
            }
        }    
    }    
    
    public String imprimeHora(){
        ssegundos = (segundos < 10) ? "0"+ segundos : ""+ segundos;
        sminutos = (minutos < 10) ? "0"+ minutos : "" + minutos;
        shoras = (horas < 10) ? "0" + horas : "" + horas;
        
        return shoras + " : " + sminutos + " : " + ssegundos;        
    }
    
}
