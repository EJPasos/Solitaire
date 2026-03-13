package GUI;

import DeckOfCards.CartaInglesa;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import solitaire.TableauDeck;

import java.util.function.Consumer;

public class TableauView extends VBox {
    private TableauDeck mazo;
    private int indice;
    private static final double CARD_OVERLAP = 25; // Píxeles que se ve de cada carta
    private Consumer<Integer> onTableauClick;

    public TableauView(TableauDeck mazo, int indice) {
        this.mazo = mazo;
        this.indice = indice;
        this.setSpacing(-80); // Para solapar las cartas verticalmente (negativo para overlap)
        this.setMinHeight(400);
        refrescar();

        // Agregar funcionalidad de clic
        setOnMouseClicked(event -> handleClick());
    }

    public void refrescar() {
        getChildren().clear();

        // Si no hay cartas, mostrar espacio vacío
        if (mazo.getCards().isEmpty()) {
            Rectangle placeholder = new Rectangle(CartaView.CARD_WIDTH, CartaView.CARD_HEIGHT);
            placeholder.setArcWidth(10);
            placeholder.setArcHeight(10);
            placeholder.setFill(Color.TRANSPARENT);
            placeholder.setStroke(Color.DARKGRAY);
            placeholder.setStrokeWidth(2);
            getChildren().add(placeholder);
        } else {
            // Agregar todas las cartas
            for (CartaInglesa c : mazo.getCards()) {
                CartaView cartaView = new CartaView(c);
                getChildren().add(cartaView);
            }
        }
    }

    private void handleClick() {
        if (onTableauClick != null) {
            onTableauClick.accept(indice);
        }
    }

    public void setOnTableauClick(Consumer<Integer> onTableauClick) {
        this.onTableauClick = onTableauClick;
    }

    public TableauDeck getMazo() {
        return mazo;
    }

    public int getIndice() {
        return indice;
    }
}
