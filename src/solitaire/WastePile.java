package solitaire;

import DeckOfCards.CartaInglesa;

import java.util.ArrayList;
/**
 * Modela el montículo donde se colocan las cartas
 * que se extraen de Draw pile.
 *
 * @author (Cecilia Curlango Rosas)
 * @version (2025-2)
 */
public class WastePile {
    private Pila<CartaInglesa> cartas;

    public WastePile() {
        cartas = new Pila<>(52);
    }

    public void addCartas(ArrayList<CartaInglesa> nuevas) {
        for (CartaInglesa carta : nuevas) {
            cartas.push(carta);
        }
    }

    public ArrayList<CartaInglesa> emptyPile() {
        ArrayList<CartaInglesa> pile = new ArrayList<>();
        Pila<CartaInglesa> aux = new Pila<>(52);

        // Pasar de cartas -> aux (queda invertido)
        while (!cartas.estaVacia()) {
            aux.push(cartas.pop());
        }

        // De aux -> pile (queda en orden original de base a tope)
        while (!aux.estaVacia()) {
            pile.add(aux.pop());
        }

        return pile;
    }

    /**
     * Obtener la última carta sin removerla.
     * @return Carta que está encima. Si está vacía, es null.
     */
    public CartaInglesa verCarta() {
        if (cartas.estaVacia()) return null;
        CartaInglesa top = cartas.pop();
        cartas.push(top);
        return top;
    }
    public CartaInglesa getCarta() {
        if (!cartas.estaVacia()) {
            return cartas.pop();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        CartaInglesa regresar = verCarta();
        if (regresar == null) {
            stb.append("---");
        } else {
            regresar.makeFaceUp();
            stb.append(regresar.toString());
        }
        return stb.toString();
    }

    public boolean hayCartas() {
        return !cartas.estaVacia();
    }
}
