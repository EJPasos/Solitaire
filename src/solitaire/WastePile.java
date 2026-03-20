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
        if (!cartas.estaVacia()) {
            for (int i = 0; i < cartas.size(); i++) {
                pile.add(cartas.get(i));
            }
            cartas.clear();
        }
        return pile;
    }

    /**
     * Obtener la última carta sin removerla.
     * @return Carta que está encima. Si está vacía, es null.
     */
    public CartaInglesa verCarta() {
        return cartas.peek();
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
        if (cartas.estaVacia()) {
            stb.append("---");
        } else {
            CartaInglesa regresar = cartas.peek();
            regresar.makeFaceUp();
            stb.append(regresar.toString());
        }
        return stb.toString();
    }

    public boolean hayCartas() {
        return !cartas.estaVacia();
    }
}
