package solitaire;

import DeckOfCards.CartaInglesa;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

// En esta clase se guardara un Snapshot de la partida.
public class GameSnapshot {
    // Se guardan copias de todas las estructuras
    public final ArrayList<ArrayList<CartaInglesa>> tableauState = new ArrayList<>();
    public final ArrayList<ArrayList<CartaInglesa>> foundationState = new ArrayList<>();
    public final ArrayList<CartaInglesa> drawPileState = new ArrayList<>();
    public final ArrayList<CartaInglesa> wastePileState = new ArrayList<>();

    public GameSnapshot(SolitaireGame game) {
        // Copiar Tableaux
        for (TableauDeck td : game.getTableau()) {
            tableauState.add(copyCards(td.getCards()));
        }

        // Copiar Foundations (sin asumir acceso directo a campos)
        for (FoundationDeck fd : game.getFoundation()) {
            foundationState.add(extractCards(fd));
        }

        // Copiar DrawPile y WastePile
        drawPileState.addAll(extractCards(game.getDrawPile()));
        wastePileState.addAll(extractCards(game.getWastePile()));
    }

    private ArrayList<CartaInglesa> copyCards(Collection<CartaInglesa> source) {
        return new ArrayList<>(source); // copia del contenedor (snapshot estructural)
    }

    @SuppressWarnings("unchecked")
    private ArrayList<CartaInglesa> extractCards(Object source) {
        ArrayList<CartaInglesa> result = new ArrayList<>();
        if (source == null) return result;

        // Caso 1: colección directa
        if (source instanceof Collection<?>) {
            for (Object o : (Collection<?>) source) {
                if (o instanceof CartaInglesa) result.add((CartaInglesa) o);
            }
            return result;
        }

        // Caso 2: Pila<CartaInglesa>
        if (source instanceof Pila<?>) {
            try {
                Field cartasField = source.getClass().getDeclaredField("cartas");
                cartasField.setAccessible(true);
                Object cartasObj = cartasField.get(source);
                if (cartasObj instanceof Object[]) {
                    for (Object o : (Object[]) cartasObj) {
                        if (o instanceof CartaInglesa) result.add((CartaInglesa) o);
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException ignored) {
            }
            return result;
        }

        // Caso 3: fallback por reflexión (si la estructura está encapsulada)
        for (Field f : source.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                Object value = f.get(source);
                if (value == null) continue;

                if (value instanceof Collection<?> || value instanceof Pila<?>) {
                    result.addAll(extractCards(value));
                    if (!result.isEmpty()) return result;
                } else if (value instanceof CartaInglesa) {
                    result.add((CartaInglesa) value);
                }
            } catch (IllegalAccessException ignored) {
            }
        }

        return result;
    }
}
