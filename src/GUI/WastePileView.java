package GUI;

import DeckOfCards.CartaInglesa;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import solitaire.WastePile;

public class WastePileView extends StackPane {
    private WastePile wastePile;
    private Rectangle card;
    private Text cardText;
    private static final double CARD_WIDTH = 80;
    private static final double CARD_HEIGHT = 120;

    public WastePileView(WastePile wastePile) {
        this.wastePile = wastePile;

        // Crear el rectángulo de la carta
        card = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
        card.setArcWidth(10);
        card.setArcHeight(10);
        card.setStroke(Color.BLACK);
        card.setStrokeWidth(2);

        // Crear el texto
        cardText = new Text();
        cardText.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        getChildren().addAll(card, cardText);
        setAlignment(Pos.CENTER);

        update();

        // Hacer clickeable
        setOnMouseClicked(event -> handleClick());
    }

    /**
     * Actualiza la vista según el estado del WastePile.
     */
    public void update() {
        CartaInglesa topCard = wastePile.verCarta();

        if (topCard == null) {
            // Pile vacío
            card.setFill(Color.LIGHTGRAY);
            cardText.setText("---");
            cardText.setFill(Color.DARKGRAY);
        } else {
            // Mostrar la carta superior
            card.setFill(Color.WHITE);
            cardText.setText(topCard.toString());

            // Color rojo para corazones y diamantes, negro para picas y tréboles
            String palo = String.valueOf(topCard.getPalo());
            if (palo.equals("♥") || palo.equals("♦")) {
                cardText.setFill(Color.RED);
            } else {
                cardText.setFill(Color.BLACK);
            }
        }
    }

    /**
     * Maneja el click en el WastePile.
     */
    private void handleClick() {
        // Este método será implementado cuando se integre con el controlador
        System.out.println("WastePile clicked");
    }
}
