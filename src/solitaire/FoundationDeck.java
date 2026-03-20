package solitaire;

import DeckOfCards.CartaInglesa;
import DeckOfCards.Palo;

public class FoundationDeck {
    Palo palo;
    Pila<CartaInglesa> cartas = new Pila<>(13);

    public FoundationDeck(Palo palo) {
        this.palo = palo;
    }

    public FoundationDeck(CartaInglesa carta) {
        palo = carta.getPalo();
        if (carta.getValorBajo() == 1) {
            cartas.push(carta);
        }
    }

    /**
     * Agrega una carta al montículo. Sólo la agrega si
     * la carta es del palo del montículo y el la siguiente
     * carta en la secuencia.
     *
     * @param carta que se intenta almancenar
     * @return true si se pudo guardar la carta, false si no
     */
    public boolean agregarCarta(CartaInglesa carta) {
        boolean agregado = false;
        if (carta.tieneElMismoPalo(palo)) {
            if (cartas.estaVacia()) {
                if (carta.getValorBajo() == 1) {
                    // si no hay cartas entonces la carta debe ser un A
                    cartas.push(carta);
                    agregado = true;
                }
            } else {
                // si hay cartas entonces debe haber secuencia
                CartaInglesa ultimaCarta = cartas.peek();
                if (ultimaCarta.getValorBajo() + 1 == carta.getValorBajo()) {
                    // agregar la carta si el la siguiente a la última
                    cartas.push(carta);
                    agregado = true;
                }
            }
        }
        return agregado;
    }

    /**
     * Remover la última carta del montículo.
     *
     * @return la carta que removió, null si estaba vacio
     */
    CartaInglesa removerUltimaCarta() {
        if (!cartas.estaVacia()) {
            return cartas.pop();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (cartas.estaVacia()) {
            builder.append("---");
        } else {
            for (int i = 0; i < cartas.size(); i++) {
                builder.append(cartas.get(i).toString());
            }
        }
        return builder.toString();
    }

    /**
     * Determina si hay cartas en el Foundation.
     * @return true hay al menos una carta, false no hay cartas
     */
    public boolean estaVacio() {
        return cartas.estaVacia();
    }

    /**
     * Obtiene la última carta del Foundation sin removerla.
     * @return última carta, null si no hay cartas
     */
    public CartaInglesa getUltimaCarta() {
        return cartas.peek();
    }

    /**
     * Obtiene el palo del FoundationDeck.
     * @return el palo del foundation
     */
    public Palo getPalo() {
        return palo;
    }
}
