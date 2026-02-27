package GUI;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import solitaire.DrawPile;

public class DrawPileView extends StackPane {
    private DrawPile drawPile;
    private Rectangle card;
    private Text cardText;
    private static final double CARD_WIDTH = 80;
    private static final double CARD_HEIGHT = 120;

    public DrawPileView(DrawPile drawPile) {
        this.drawPile = drawPile;

        // Crear el rectángulo de la carta
        card = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
        card.setArcWidth(10);
        card.setArcHeight(10);
        card.setStroke(Color.BLACK);
        card.setStrokeWidth(2);

        // Crear el texto
        cardText = new Text();
        cardText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        getChildren().addAll(card, cardText);
        setAlignment(Pos.CENTER);

        update();

        // Hacer clickeable
        setOnMouseClicked(event -> handleClick());
    }

    /**
     * Actualiza la vista según el estado del DrawPile.
     */
    public void update() {
        if (drawPile.hayCartas()) {
            card.setFill(Color.DARKGREEN);
            cardText.setText("@");
            cardText.setFill(Color.WHITE);
        } else {
            card.setFill(Color.LIGHTGRAY);
            cardText.setText("-E-");
            cardText.setFill(Color.DARKGRAY);
        }
    }

    /**
     * Maneja el click en el DrawPile.
     */
    private void handleClick() {
        // Este método será implementado cuando se integre con el controlador
        System.out.println("DrawPile clicked");
    }
}
