
package Reloj;
import java.time.Clock;
import java.util.Calendar;

public class reloj {
 
    private Calendar calendario;
    private int horas;
    private int minutos;
    private int segundos;

    public reloj(int horas, int minutos, int segundos) {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }
    
    public reloj(){
        getHoraActual();
    }
    
    public void getHoraActual(){
        Calendar calendario = Calendar.getInstance();
        horas = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);        
    }
    
    public void modificarHora(int hora, int minuto, int segundo){
        horas = hora;
        minutos = minuto;
        segundos = segundo;
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
        String ssegundos = (segundos < 10) ? "0"+ segundos : ""+ segundos;
        String sminutos = (minutos < 10) ? "0"+ minutos : "" + minutos;
        String shoras = (horas < 10) ? "0" + horas : "" + horas;
        
        return shoras + ":" + sminutos + ":" + ssegundos;        
    }

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }
    
    
    
}
