package PClienteJuego;

import com.dist.juego.Carta;

/**
 *
 * @author Alan
 */
public class PeleaPokemon {
    private Carta CartaSalvaje;
    private int ContadorMuertes;
    private int CantidadCartas;
    
    public PeleaPokemon(Carta c, int cantidad)
    {
        this.CartaSalvaje = c;
        this.ContadorMuertes = 0;
        this.CantidadCartas = cantidad;
    }
    
    public void Atacar(Carta CartaDelJugador)
    {
        int HP_Salvaje = CartaSalvaje.getHp();
        HP_Salvaje -= CartaDelJugador.getAtaque() * Multiplicador(CartaDelJugador.getTipo1(), CartaSalvaje.getTipo1());
        if (HP_Salvaje <= 0) 
        {
            System.out.println("Ganaste");
        }
        
    }
    
    public void defender(Carta CartaDelJugador)
    {
        int HP_jugador = CartaDelJugador.getHp();
        HP_jugador -= CartaSalvaje.getAtaque() * Multiplicador(CartaSalvaje.getTipo1(), CartaDelJugador.getTipo1());
        if (HP_jugador <= 0) {
            System.out.println("Murio tu pokemon");
            ContadorMuertes++;
        }
        if(ContadorMuertes >= CantidadCartas)
            System.out.println("GG");
    }
    
    private double Multiplicador(String tipoAtacante, String tipoDefensor)
    {
        double val = 1;
        switch (tipoAtacante) 
        {
            case "bug":
                switch(tipoDefensor)
                {
                    case "steel": val = 0.5; break;
                    case "fire": val = 0.5; break;
                    case "fairy": val = 0.5; break;
                    case "fighting": val = 0.5; break;
                    case "grass": val = 2; break;
                    case "phychic": val = 2; break;
                    case "poison": val = 0.5; break;
                    case "flying": val = 2; break;
                    default: val = 1; break;
                }
                break;
            case "dragon":
                switch(tipoDefensor)
                {
                    case "steel": val = 0.5; break;
                    case "fairy": val = 0; break;
                    case "dragon": val = 2; break;
                    default: val = 1; break;
                }
                break;
            case "electric":
                switch(tipoDefensor)
                {
                    case "water": val = 2; break;
                    case "fire": val = 0.5; break;
                    case "electric": val = 0.5; break;
                    case "ground": val = 0; break;
                    case "flying": val = 2; break;
                    default: val = 1; break;
                }
                break;
            case "fairy":
                switch(tipoDefensor)
                {
                    case "steel": val = 0.5; break;
                    case "fire": val = 0.5; break;
                    case "dragon": val = 2; break;
                    case "fighting": val = 2; break;
                    case "poison": val = 0.5; break;
                    default: val = 1; break;
                }
                break;
            case "fighting":
                switch(tipoDefensor)
                {
                    case "steel": val = 2; break;
                    case "bug": val = 0.5; break;
                    case "fairy": val = 0.5; break;
                    case "ice": val = 2; break;
                    case "grass": val = 2; break;
                    case "normal": val = 2; break;
                    case "phychic": val = 0.5; break;
                    case "rock": val = 2; break;
                    case "poison": val = 0.5; break;
                    case "flying": val = 0.0; break;
                    default: val = 1; break;
                }
                break;
            case "fire":
                switch(tipoDefensor)
                {
                    case "steel": val = 2; break;
                    case "bug": val = 2; break;
                    case "water": val = 2; break;
                    case "ice": val = 2; break;
                    case "grass": val = 2; break;
                    case "dragon": val = 0.5; break;
                    case "fire": val = 0.5; break;
                    case "rock": val = 0.5; break;
                    default: val = 1; break;
                }
                break;
            case "flying":
                switch(tipoDefensor)
                {
                    case "steel": val = 0.5; break;
                    case "bug": val = 2; break;
                    case "electric": val = 0.5; break;
                    case "fighting": val = 2; break;
                    case "grass": val = 2; break;
                    case "rock": val = 0.5; break;
                    default: val = 1; break;
                }
                break;
            case "grass":
                switch(tipoDefensor)
                {
                    case "steel": val = 0.5; break;
                    case "bug": val = 0.5; break;
                    case "water": val = 2; break;
                    case "dragon": val = 0.5; break;
                    case "fire": val = 0.5; break;
                    case "grass": val = 0.5; break;
                    case "phychic": val = 0.5; break;
                    case "rock": val = 2; break;
                    case "poison": val = 0.5; break;
                    case "flying": val = 0.5; break;
                    case "ground": val = 0.5; break;
                    default: val = 1; break;
                }
                break;
            case "ground":
                switch(tipoDefensor)
                {
                    case "steel": val = 2; break;
                    case "bug": val = 0.5; break;
                    case "electic": val = 2; break;
                    case "fire": val = 2; break;
                    case "grass": val = 0.5; break;
                    case "rock": val = 2; break;
                    case "poison": val = 2; break;
                    case "flying": val = 0; break;
                    case "ground": val = 0.5; break;
                    default: val = 1; break;
                }
                break;
            case "ice":
                switch(tipoDefensor)
                {
                    case "steel": val = 0.5; break;
                    case "ice": val = 0.5; break;
                    case "water": val = 0.5; break;
                    case "dragon": val = 2; break;
                    case "fire": val = 0.5; break;
                    case "grass": val = 2; break;
                    case "flying": val = 2; break;
                    case "ground": val = 2; break;
                    default: val = 1; break;
                }
                break;
            case "normal":
                switch(tipoDefensor)
                {
                    case "steel": val = 0.5; break;
                    case "rock": val = 0.5; break;
                    default: val = 1; break;
                }
                break;
            case "poison":
                switch(tipoDefensor)
                {
                    case "steel": val = 0; break;
                    case "fairy": val = 2; break;
                    case "grass": val = 2; break;
                    case "rock": val = 0.5; break;
                    case "poison": val = 0.5; break;
                    case "ground": val = 0.5; break;
                    default: val = 1; break;
                }
                break;
            case "phychic":
                switch(tipoDefensor)
                {
                    case "steel": val = 0.5; break;
                    case "fighting": val = 0.5; break;
                    case "phychic": val = 0.5; break;
                    case "poison": val = 0.5; break;
                    default: val = 1; break;
                }
                break;
            case "rock":
                switch(tipoDefensor)
                {
                    case "steel": val = 0.5; break;
                    case "bug": val = 2; break;
                    case "ice": val = 2; break;
                    case "fighting": val = 0.5; break;
                    case "fire": val = 0.5; break;
                    case "flying": val = 2; break;
                    case "ground": val = 0.5; break;
                    default: val = 1; break;
                }
                break;
            case "steel":
                switch(tipoDefensor)
                {
                    case "steel": val = 0.5; break;
                    case "electric": val = 0.5; break;
                    case "water": val = 0.5; break;
                    case "fairy": val = 2; break;
                    case "fire": val = 0.5; break;
                    case "ice": val = 2; break;
                    case "rock": val = 2; break;
                    default: val = 1; break;
                }
                break;
            case "water":
                switch(tipoDefensor)
                {
                    case "water": val = 0.5; break;
                    case "dragon": val = 0.5; break;
                    case "fire": val = 2; break;
                    case "grass": val = 0.5; break;
                    case "rock": val = 2; break;
                    case "ground": val = 2; break;
                    default: val = 1; break;
                }
                break;
            default:
                val = 0;
        }
        return val;
    }
}


